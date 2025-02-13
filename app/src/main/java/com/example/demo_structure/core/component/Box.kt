package com.example.demo_structure.core.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * Created by Phạm Sơn at 10:49/21/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Composable
fun AppBox(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    shape: Shape = RectangleShape,
    border: BorderStroke? = null,
    contentAlignment: Alignment = Alignment.CenterStart,
    content: @Composable
    BoxScope.() ->
    Unit
) {
    Box(
        modifier = modifier
            .shadow(elevation = 0.dp, shape = shape)
            .clip(shape)
            .then(if (border != null) modifier.border(border, shape) else Modifier)
            .background(
                color = backgroundColor,
                shape = shape
            ),
        contentAlignment = contentAlignment,
        propagateMinConstraints = false,
        content = content
    )
}

@Composable
@ThemePreviews
fun AppBoxPreview() {
    AppPreviewWrapper {
        AppBox(backgroundColor = Color.White) {
            Text("OnClick Item")
        }
    }
}