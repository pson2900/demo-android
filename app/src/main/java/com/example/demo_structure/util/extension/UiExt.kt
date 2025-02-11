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

package com.example.demo_structure.util.extension

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.core.util.Consumer
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Convenience wrapper for dark mode checking
 */
val Configuration.isSystemInDarkTheme
    get() = (uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

/**
 * Registers listener for configuration changes to retrieve whether system is in dark theme or not.
 * Immediately upon subscribing, it sends the current value and then registers listener for changes.
 */
fun ComponentActivity.isSystemInDarkTheme() = callbackFlow {
    channel.trySend(resources.configuration.isSystemInDarkTheme)

    val listener = Consumer<Configuration> {
        channel.trySend(it.isSystemInDarkTheme)
    }

    addOnConfigurationChangedListener(listener)

    awaitClose { removeOnConfigurationChangedListener(listener) }
}
    .distinctUntilChanged()
    .conflate()
private const val TAG = "NavigationLog"
fun logNavigation(navController: NavHostController) {
    navController.addOnDestinationChangedListener { controller, destination, arguments ->
        val startRoute = controller.graph.findStartDestination().route
        if(startRoute != destination.route ) {
            Log.d(TAG, "Navigated to: ${destination.route} - NavController ${controller}")
        }

    }
}

fun hideKeyboard(context: Context, focusManager: FocusManager) {
    val inputMethodManager =
        context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view: View? = (context as? Activity)?.currentFocus
    if (view != null) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    } else {
        focusManager.clearFocus()
    }
}

fun hideKeyboardAndClearFocus(
    context: Context,
    focusManager: FocusManager,
    keyboardController: SoftwareKeyboardController?
) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    var view: View? = null
    if (context is android.app.Activity) {
        view = context.currentFocus
    }
    if (view == null) {
        view = View(context)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
    focusManager.clearFocus()
    keyboardController?.hide()
}