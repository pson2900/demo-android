/*
 * Copyright 2022 The Android Open Source Project
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

package com.example.demo_structure.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.NetworkRequest.Builder
import androidx.compose.ui.util.trace
import androidx.core.content.getSystemService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import org.koin.core.component.KoinComponent

class AlwaysOnlineNetworkMonitor constructor() : NetworkMonitor {
    override val isOnline: Flow<Boolean> = flowOf(true)
}

interface NetworkMonitor {
    val isOnline: Flow<Boolean>
}


internal class ConnectivityManagerNetworkMonitor(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher
) : KoinComponent, NetworkMonitor {
    override val isOnline: Flow<Boolean> = callbackFlow {
        trace("NetworkMonitor.callbackFlow") {
            val connectivityManager = context.getSystemService<ConnectivityManager>()
            if (connectivityManager == null) {
                channel.trySend(false)
                channel.close()
                return@callbackFlow
            }

            /**
             * The callback's methods are invoked on changes to *any* network matching the [NetworkRequest],
             * not just the active network. So we can simply track the presence (or absence) of such [Network].
             */
            val callback = object : NetworkCallback() {

                private val networks = mutableSetOf<Network>()

                override fun onAvailable(network: Network) {
                    networks += network
                    channel.trySend(true)
                }

                override fun onLost(network: Network) {
                    networks -= network
                    channel.trySend(networks.isNotEmpty())
                }
            }

            trace("NetworkMonitor.registerNetworkCallback") {
                val request = Builder()
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .build()
                connectivityManager.registerNetworkCallback(request, callback)
            }

            /**
             * Sends the latest connectivity status to the underlying channel.
             */
            channel.trySend(connectivityManager.isCurrentlyConnected())

            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }
    }
        .flowOn(ioDispatcher)
        .conflate()

    @Suppress("DEPRECATION")
    private fun ConnectivityManager.isCurrentlyConnected() = when {
        true ->
            activeNetwork
                ?.let(::getNetworkCapabilities)
                ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

        else -> activeNetworkInfo?.isConnected
    } ?: false
}
