package com.upzi.myapplication.ui.screens.job_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Created by Phạm Sơn at 09:49/11/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun SwipeDismissItem(
    modifier: Modifier = Modifier,
    enter: EnterTransition = expandVertically(),
    exit: ExitTransition = shrinkVertically(),
    background: @Composable (progress: Float) -> Unit,
    content: @Composable (isDismissed: Boolean) -> Unit,
) {
    // Hold the current state from the Swipe to Dismiss composable
    val dismissState = rememberSwipeToDismissBoxState()
    // Boolean value used for hiding the item if the current state is dismissed
    val isDismissed = dismissState.currentValue == SwipeToDismissBoxValue.EndToStart

    AnimatedVisibility(
        modifier = modifier,
        visible = !isDismissed,
        enter = enter,
        exit = exit
    ) {
        SwipeToDismissBox(
            modifier = modifier,
            state = dismissState,
            enableDismissFromStartToEnd = false,
            backgroundContent = { background(dismissState.progress) },
            content = { content(isDismissed) }
        )
    }
}