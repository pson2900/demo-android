package com.example.demo_structure.app

import android.app.Application
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.demo_structure.app.manager.theme.ApplicationTheme
import com.example.demo_structure.app.manager.theme.LocalNavAnimatedVisibilityScope
import com.example.demo_structure.app.manager.theme.LocalSharedTransitionScope
import com.example.demo_structure.core.component.AppBackground
import com.example.demo_structure.core.navigation.AppNavHost
import com.example.demo_structure.core.navigation.rememberAppState
import com.example.demo_structure.screen.main.ThemeSettings
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

/**
 * Created by Phạm Sơn at 11:24/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

class ProductApp : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        KoinSetup.init(this)

      /*  val workerFactory: KoinWorkerFactory by inject()
        val config = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

        WorkManager.initialize(this, config)*/
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun InitializeApp(
    modifier: Modifier = Modifier,
    themeSettings: ThemeSettings,
) {
    ApplicationTheme(
        darkTheme = isSystemInDarkTheme(),
        dynamicColor = themeSettings.disableDynamicTheming,
    ) {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                CompositionLocalProvider(
                    LocalSharedTransitionScope provides this@SharedTransitionLayout,
                    LocalNavAnimatedVisibilityScope provides this,
                ) {
                    val appState = rememberAppState()
                    val isOffline by appState.isOffline.collectAsState()
                    val showDialog = remember { mutableStateOf(false) }
                    // If user is not connected to the internet show a snack bar to inform them.
                    LaunchedEffect(isOffline) {
                        showDialog.value = isOffline
                    }
                    AppBackground(modifier = modifier) {
//                        OfflineAlertDialog(showDialog, onDismiss = { showDialog.value = isOffline })
                        AppNavHost(modifier = modifier, appState = appState)
                    }
                }
            }
        }
    }
}

@Composable
fun OfflineAlertDialog(showDialog: MutableState<Boolean>, onDismiss: () -> Unit) {
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                onDismiss() // or showDialog.value = false
            },
            title = {
                Text(text = "No Internet Connection")
            },
            text = {
                Text(text = "Please check your internet connection and try again.")
            },
            confirmButton = {
                Button(onClick = {
                    onDismiss()
                }) {
                    Text("Dismiss")
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )

    }
}