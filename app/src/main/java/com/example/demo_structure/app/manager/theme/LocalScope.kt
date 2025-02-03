package com.example.demo_structure.app.manager.theme

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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
val LocalBackgroundTheme = staticCompositionLocalOf { BackgroundTheme(color = hexToColor("#F1F5F9")) }

/**
 * A composition local for [AppTypography].
 */
val LocalAppTypography = staticCompositionLocalOf { AppTypography() }

/**
 * A composition local for [CardTheme].
 */
val LocalCardTheme = staticCompositionLocalOf {
    CardTheme(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(10.dp, Color.Unspecified),
        background = hexToColor("#FFFFFF"), text = hexToColor("#0F172A")
    )
}

/**
 * A composition local for [BackgroundTheme].
 */
val LocalColorTheme = staticCompositionLocalOf<ColorScheme> {
    error("No ColorPalette provided")
}

