package com.example.demo_structure.app.manager.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by Phạm Sơn at 13:17/6/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Immutable
class Padding(
    smallScreen: Dp = 8.dp,
    mediumScreen: Dp = 16.dp,
    largeScreen: Dp = 24.dp
)

@Immutable
class Corner(
    smallScreen: Dp = 8.dp,
    mediumScreen: Dp = 16.dp,
    largeScreen: Dp = 24.dp
)

@Immutable
class Margin(
    smallScreen: Dp = 8.dp,
    mediumScreen: Dp = 16.dp,
    largeScreen: Dp = 24.dp
)

@Immutable
class TonalElevation(
    smallScreen: Dp = 2.dp,
    mediumScreen: Dp = 3.dp,
    largeScreen: Dp = 4.dp
)