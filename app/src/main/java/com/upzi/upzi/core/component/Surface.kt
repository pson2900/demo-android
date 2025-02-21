package com.upzi.upzi.core.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.upzi.upzi.app.manager.theme.ProductXTheme
import kotlin.math.ln

/**
 * Created by Phạm Sơn at 15:58/10/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun AppSurface(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(0.dp),
    backgroundColor: Color,
    contentColor: Color = ProductXTheme.colorScheme.onSurface,
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    onClickAction: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier
            .shadow(elevation = elevation, shape = shape, clip = false)
            .zIndex(elevation.value)
            .clip(shape)
            .background(
                color = getBackgroundColorForElevation(backgroundColor, elevation),
                shape = shape
            )
            .then(if (border != null) modifier.border(border, shape) else Modifier)
            .then(if (onClickAction != null) modifier.clickable(onClick = onClickAction) else Modifier)
        ,
        contentColor = contentColor,
        content = content
    )
}

@ThemePreviews
@Composable
fun AppSurfacePreviews() {
    AppPreviewWrapper {
        AppSurface(
            backgroundColor = ProductXTheme.colorScheme.background_1,
        ) {
            Text(text = "AppSurface")
        }
    }
}


@Composable
private fun getBackgroundColorForElevation(color: Color, elevation: Dp): Color {
    return if (elevation > 0.dp) {
        color.withElevation(elevation)
    } else {
        color
    }
}

private fun Color.withElevation(elevation: Dp): Color {
    val foreground = calculateForeground(elevation)
    return foreground.compositeOver(this)
}

private fun calculateForeground(elevation: Dp): Color {
    val alpha = ((4.5f * ln(elevation.value + 1)) + 2f) / 100f
    return Color.White.copy(alpha = alpha)
}
