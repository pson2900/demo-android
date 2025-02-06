package com.example.demo_structure.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.demo_structure.app.manager.theme.ProductXTheme

/**
 * Created by Phạm Sơn at 10:49/21/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Composable
fun AppBox(modifier: Modifier = Modifier, contentAlignment: Alignment = Alignment.CenterStart, content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = modifier,
        contentAlignment = contentAlignment,
        propagateMinConstraints = false,
        content = content
    )
}

@Composable
@ThemePreviews
fun AppBoxPreview() {
    AppPreviewWrapper {
        AppBox {
            Text("OnClick Item")
        }
    }
}