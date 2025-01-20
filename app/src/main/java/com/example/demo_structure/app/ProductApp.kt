package com.example.demo_structure.app

import android.app.Application
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import com.example.demo_structure.app.di.dataModule
import com.example.demo_structure.app.di.domainModule
import com.example.demo_structure.app.di.networkModule
import com.example.demo_structure.app.di.presentationModule
import com.example.demo_structure.app.manager.theme.ProductXApplicationTheme
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.screen.main.ThemeSettings
import com.example.demo_structure.core.navigation.AppNavHost
import org.koin.compose.KoinApplication

/**
 * Created by Phạm Sơn at 11:24/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

val LocalNavAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> { null }

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }

class ProductApp : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinSetup.init(this)

    }
}

@Composable
fun App() {
    KoinApplication(application = {
        modules(
            listOf(networkModule, dataModule, domainModule, presentationModule)
        )
    }) {

    }
}
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun InitializeApp(
    appState: AppState,
    modifier: Modifier = Modifier,
    themeSettings: ThemeSettings,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    ProductXApplicationTheme(
        darkTheme = themeSettings.darkTheme,
        androidTheme = themeSettings.androidTheme,
        disableDynamicTheming = themeSettings.disableDynamicTheming,
    ) {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                CompositionLocalProvider(
                    LocalSharedTransitionScope provides this@SharedTransitionLayout,
                    LocalNavAnimatedVisibilityScope provides this
                ) {
                    AppNavHost(
                        modifier = modifier, appState = appState,
                    )
                }
            }

        }
    }
}