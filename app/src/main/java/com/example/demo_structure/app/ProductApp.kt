package com.example.demo_structure.app

import android.app.Application
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.SnackbarDuration.Indefinite
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.demo_structure.app.manager.theme.AppTypography
import com.example.demo_structure.app.manager.theme.ApplicationTheme
import com.example.demo_structure.app.manager.theme.LocalAppTypography
import com.example.demo_structure.app.manager.theme.LocalNavAnimatedVisibilityScope
import com.example.demo_structure.app.manager.theme.LocalSharedTransitionScope
import com.example.demo_structure.app.manager.theme.LocalWindowAdaptiveInfo
import com.example.demo_structure.core.component.AppBackground
import com.example.demo_structure.core.navigation.AppNavHost
import com.example.demo_structure.core.navigation.rememberAppState
import com.example.demo_structure.screen.main.ThemeSettings

/**
 * Created by Phạm Sơn at 11:24/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

class ProductApp : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinSetup.init(this)
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun InitializeApp(
    modifier: Modifier = Modifier,
    themeSettings: ThemeSettings,
    appTypography: AppTypography = AppTypography(),
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    ApplicationTheme(
        darkTheme = themeSettings.darkTheme,
        disableDynamicTheming = themeSettings.disableDynamicTheming,
    ) {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                CompositionLocalProvider(
                    LocalAppTypography provides appTypography,
                    LocalSharedTransitionScope provides this@SharedTransitionLayout,
                    LocalNavAnimatedVisibilityScope provides this,
                    LocalWindowAdaptiveInfo provides windowAdaptiveInfo
                ) {

                    val snackbarHostState = remember { SnackbarHostState() }
                    val appState = rememberAppState()
                    val isOffline by appState.isOffline.collectAsStateWithLifecycle()

                    // If user is not connected to the internet show a snack bar to inform them.
                    val notConnectedMessage = "notConnectedMessage"
                    LaunchedEffect(isOffline) {
                        if (isOffline) {
                            snackbarHostState.showSnackbar(
                                message = notConnectedMessage,
                                duration = Indefinite,
                            )
                        }
                    }
                    AppBackground(modifier = modifier) {
                        AppNavHost(
                            modifier = modifier, appState = appState,
                        )
                    }
                }
            }
        }
    }
}