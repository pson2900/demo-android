package com.upzi.upzi.core.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.upzi.upzi.app.manager.theme.hexToColor

/**
 * Created by Phạm Sơn at 17:24/20/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun ShimmerListItem(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isLoading) {
        Row(modifier = modifier) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .shimmerEffect()
                )
                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .height(20.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .shimmerEffect()
                )
            }
        }
    } else {
        contentAfterLoading()
    }
}

@Composable
fun Modifier.shimmerEffect(): Modifier = composed {
    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = listOf(
            hexToColor("#E2E8F0"),
            hexToColor("#CDD6E2"),
            hexToColor("#E2E8F0"),
        ),
        start = Offset(0f, 0f),
        end = Offset(translateAnim.value, 0f)
    )

    background(brush = brush)
}

@Preview(showBackground = true)
@Composable
fun ShimmerListItemPreview() {
    ShimmerListItem(isLoading = true, contentAfterLoading = { Text("Done Loading") })
}