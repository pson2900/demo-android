package com.example.demo_structure.core.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.demo_structure.theme.ProductXTheme

/**
 * Created by Phạm Sơn at 10:33/11/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun JobDetailCard(
    modifier: Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    color: Color = ProductXTheme.colors.background,
//    contentColor: Color = ProductXTheme.colors.textSecondary,
    contentColor: Color = ProductXTheme.colors.textSecondary,
    border: BorderStroke? = null,
    elevation: Dp = 4.dp,
    content: @Composable () -> Unit
) {
    ProductXSurface(
        modifier = modifier,
        shape = shape,
        color = color,
        contentColor = contentColor,
        elevation = elevation,
        border = border,
        content = content
    )
}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
private fun CardPreview() {
    ProductXPreviewWrapper {
        JobDetailCard(Modifier) {
            Text(text = "Demo", modifier = Modifier.padding(16.dp))
        }
    }
}
