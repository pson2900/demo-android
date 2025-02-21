package com.upzi.upzi.util

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable

@Composable
fun AnimatedVisibilityContent(
    visibilityFilter: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visibilityFilter,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> -fullHeight/ 4 },
            animationSpec = tween(
                durationMillis = 300,
                easing = EaseOut
            )
        ) + fadeIn(animationSpec = tween(300)),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> -fullHeight / 4},
            animationSpec = tween(
                durationMillis = 300,
                easing = EaseIn
            )
        ) + fadeOut(animationSpec = tween(300))
    ) {
        content()
    }
}
