package com.example.demo_structure.core.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.example.demo_structure.app.manager.theme.ProductXTheme
import kotlin.math.ln

/**
 * Created by Phạm Sơn at 15:58/10/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun AppSurface(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(5.dp),
    color: Color = ProductXTheme.colors.background,
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier
            .shadow(elevation = elevation, shape = shape, clip = false)
            .zIndex(elevation.value)
            .clip(shape)
            .then(if (border != null) modifier.border(border, shape) else Modifier)
            .background(
                color = getBackgroundColorForElevation(color, elevation),
                shape = shape
            ),
        content = content
    )
}

@ThemePreviews
@Composable
fun AppSurfacePreviews() {
    AppPreviewWrapper {
        AppSurface {
            Text(text = "AppSurface")
        }
    }
}


@Composable
private fun getBackgroundColorForElevation(color: Color, elevation: Dp): Color {
    return if (elevation > 0.dp // && https://issuetracker.google.com/issues/161429530
    // JetsnackTheme.colors.isDark //&&
    // color == JetsnackTheme.colors.uiBackground
    ) {
        color.withElevation(elevation)
    } else {
        color
    }
}

/**
 * Applies a [Color.White] overlay to this color based on the [elevation]. This increases visibility
 * of elevation for surfaces in a dark theme.
 *
 * TODO: Remove when public https://issuetracker.google.com/155181601
 */
private fun Color.withElevation(elevation: Dp): Color {
    val foreground = calculateForeground(elevation)
    return foreground.compositeOver(this)
}

/**
 * @return the alpha-modified [Color.White] to overlay on top of the surface color to produce
 * the resultant color.
 */
private fun calculateForeground(elevation: Dp): Color {
    val alpha = ((4.5f * ln(elevation.value + 1)) + 2f) / 100f
    return Color.White.copy(alpha = alpha)
}
