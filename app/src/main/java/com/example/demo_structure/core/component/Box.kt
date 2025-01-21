package com.example.demo_structure.core.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Created by Phạm Sơn at 10:49/21/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Composable
fun AppBox(modifier: Modifier = Modifier, contentAlignment: Alignment = Alignment.CenterStart, content: (@Composable () -> Unit)? = null) {
    Box(
        modifier = modifier,

        contentAlignment = contentAlignment,
        propagateMinConstraints = false,
        content = {})
}