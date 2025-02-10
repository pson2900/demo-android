package com.example.demo_structure.core.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.ProductXTheme

/**
 * Created by Phạm Sơn at 10:33/11/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    color: Color = ProductXTheme.colorScheme.surface,
    contentColor: Color = ProductXTheme.colorScheme.onSurface,
    border: BorderStroke? = null,
    elevation: Dp = 4.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    
    Card(
        modifier = modifier,
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = color,
            contentColor = contentColor
        ),
        border = border,
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        content = content
    )
}

@ThemePreviews
@Composable
private fun CardPreview() {
    AppPreviewWrapper {
        AppSurface(Modifier, shape = CircleShape) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_setting),
                contentDescription = "ic_setting",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
