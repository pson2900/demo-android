/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.demo_structure.util.monitor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.compose.ui.util.trace
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toKotlinTimeZone
import org.koin.core.component.KoinComponent
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

/**
 * Utility for reporting current timezone the device has set.
 * It always emits at least once with default setting and then for each TZ change.
 */
interface TimeZoneMonitor {
    val currentTimeZone: Flow<TimeZone>
    val currentTime: Flow<String>
}

class TimeZoneBroadcastMonitor(
    private val context: Context,
    private val appScope: CoroutineScope,
    private val ioDispatcher: CoroutineDispatcher
) : KoinComponent, TimeZoneMonitor {
    override val currentTime: Flow<String> = flow {
        val formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).withLocale(Locale.getDefault())
        while (true) {
            val currentDateTime = LocalDateTime.now()
            emit(currentDateTime.format(formatter))
            delay(1000) // Emit every 1 second
        }
    }.conflate()
        .flowOn(ioDispatcher)
        .shareIn(appScope, SharingStarted.WhileSubscribed(5_000), 1)

    override val currentTimeZone: Flow<TimeZone> =
        callbackFlow {
            // Send the default time zone first.
            trySend(TimeZone.currentSystemDefault())

            // Registers BroadcastReceiver for the TimeZone changes
            val receiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    if (intent.action != Intent.ACTION_TIMEZONE_CHANGED) return

                    val zoneIdFromIntent = if (VERSION.SDK_INT < VERSION_CODES.R) {
                        null
                    } else {
                        // Starting Android R we also get the new TimeZone.
                        intent.getStringExtra(Intent.EXTRA_TIMEZONE)?.let { timeZoneId ->
                            // We need to convert it from java.util.Timezone to java.time.ZoneId
                            val zoneId = ZoneId.of(timeZoneId, ZoneId.SHORT_IDS)
                            // Convert to kotlinx.datetime.TimeZone
                            zoneId.toKotlinTimeZone()
                        }
                    }

                    // If there isn't a zoneId in the intent, fallback to the systemDefault, which should also reflect the change
                    trySend(zoneIdFromIntent ?: TimeZone.currentSystemDefault())
                }
            }

            trace("TimeZoneBroadcastReceiver.register") {
                context.registerReceiver(receiver, IntentFilter(Intent.ACTION_TIMEZONE_CHANGED))
            }

            // Send here again, because registering the Broadcast Receiver can take up to several milliseconds.
            // This way, we can reduce the likelihood that a TZ change wouldn't be caught with the Broadcast Receiver.
            trySend(TimeZone.currentSystemDefault())

            awaitClose {
                context.unregisterReceiver(receiver)
            }
        }
            // We use to prevent multiple emissions of the same type, because we use trySend multiple times.
            .distinctUntilChanged()
            .conflate()
            .flowOn(ioDispatcher)

            // Sharing the callback to prevent multiple BroadcastReceivers being registered
            .shareIn(appScope, SharingStarted.WhileSubscribed(5_000), 1)
}
