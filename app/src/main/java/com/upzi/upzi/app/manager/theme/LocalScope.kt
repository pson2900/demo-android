package com.upzi.upzi.app.manager.theme

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.upzi.upzi.util.monitor.DeviceWindowAdaptive

/**
 * Created by Phạm Sơn at 13:40/21/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
val LocalNavAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> { null }
val LocalNavAnimatedContentScope = compositionLocalOf<AnimatedContentScope?> { null }

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }

val LocalWindowAdaptiveInfo = staticCompositionLocalOf<DeviceWindowAdaptive?> { null }

/**
 * A composition local for [AppTypography].
 */
internal val LocalAppTypography = staticCompositionLocalOf { AppTypography() }

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
val LocalColorTheme = staticCompositionLocalOf {
    defaultColorScheme.toColorTheme(background_1 = hexToColor("#FFFFFF"), background_2 = hexToColor("#F1F5F9"), false)
}

val LocalPadding = staticCompositionLocalOf {
    Padding()
}
val LocalMargin = staticCompositionLocalOf {
    Margin()
}
val LocalCorner = staticCompositionLocalOf {
    Corner()
}
val LocalTonalElevation = staticCompositionLocalOf {
    TonalElevation()
}
val LocalShapes = staticCompositionLocalOf {
    defaultShapes
}




