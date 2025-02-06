package com.example.demo_structure.util

import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

/**
 * Created by Phạm Sơn at 13:01/6/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Stable
data class DeviceWindowAdaptive(
    val windowWidthSizeClass: WindowWidthSizeClass,
    val windowHeightSizeClass: WindowHeightSizeClass,
    val windowSizeDp: DpSize
){
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun rememberWindowAdaptiveInfo(): DeviceWindowAdaptive {
    val configuration = LocalConfiguration.current
    val windowSizeClass = calculateWindowSizeClass(activity = LocalContext.current.findActivity())
    return remember(windowSizeClass, configuration) {
        DeviceWindowAdaptive(
            windowWidthSizeClass = windowSizeClass.widthSizeClass,
            windowHeightSizeClass = windowSizeClass.heightSizeClass,
            windowSizeDp = DpSize(configuration.screenWidthDp.dp, configuration.screenHeightDp.dp)
        )
    }
}

@Composable
fun deviceCurrentWindowAdaptive(): DeviceWindowAdaptive = rememberWindowAdaptiveInfo()