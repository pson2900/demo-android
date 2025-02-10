package com.example.demo_structure.core.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.example.demo_structure.app.manager.theme.ProductXTheme

/**
 * Created by Phạm Sơn at 13:03/22/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun LinearProgress(modifier: Modifier = Modifier) {
    var progress by remember { mutableFloatStateOf(0.7F) }
    val progressAnimDuration = 1_500
    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing),
    )
    LinearProgressIndicator(
        color = ProductXTheme.colorScheme.primary,
        trackColor = ProductXTheme.colorScheme.onSurface,
        strokeCap = StrokeCap.Round,
        progress = { progressAnimation },
        modifier = Modifier
            .fillMaxWidth()
//            .clip(RoundedCornerShape(8.dp)),
    )
 /*   LaunchedEffect(lifecycleOwner) {
        progress = indicatorProgress
    }*/
}

@Composable
fun CircularProgress() {
    var progress by remember { mutableFloatStateOf(0.7F) }
    val progressAnimDuration = 1_500
    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing),
    )
    Column {
        CircularProgressIndicator(
//            color = ProductXTheme.colors.primary,
//            trackColor = ProductXTheme.colors.onSurface,
            strokeWidth = 5.dp,
            modifier = Modifier.size(80.dp), progress = { progress }
        )

    }
}

@Composable
@ThemePreviews
fun CircularProgressPreview() {
    CircularProgress()
}

@Composable
@ThemePreviews
fun LinearProgressPreview() {
    LinearProgress()
}
