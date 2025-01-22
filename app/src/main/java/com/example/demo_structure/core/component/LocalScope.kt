package com.example.demo_structure.core.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.demo_structure.app.manager.theme.BackgroundTheme
import com.example.demo_structure.app.manager.theme.CardTheme
import com.example.demo_structure.app.manager.theme.GradientColors

/**
 * Created by Phạm Sơn at 13:40/21/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
val LocalNavAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> { null }

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }

val LocalWindowAdaptiveInfo = staticCompositionLocalOf<WindowAdaptiveInfo?> { null }
/**
 * A composition local for [GradientColors].
 */
val LocalGradientColors = staticCompositionLocalOf { GradientColors() }

/**
 * A composition local for [BackgroundTheme].
 */
val LocalBackgroundTheme = staticCompositionLocalOf { BackgroundTheme() }

/**
 * A composition local for [CardTheme].
 */
val LocalCardTheme = staticCompositionLocalOf { CardTheme() }
/**
 * A composition local for [BackgroundTheme].
 */
val LocalColorTheme = staticCompositionLocalOf<ColorScheme> {
    error("No ColorPalette provided")
}

