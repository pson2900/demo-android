package com.example.demo_structure.screen.job_detail

import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_structure.core.component.AppScaffold
import com.example.demo_structure.app.manager.theme.ApplicationTheme
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.app.manager.theme.LocalNavAnimatedVisibilityScope
import com.example.demo_structure.app.manager.theme.LocalSharedTransitionScope
import com.example.demo_structure.util.SharedElementKey
import com.example.demo_structure.util.SharedElementType

/**
 * Created by Phạm Sơn at 23:48/8/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun JobDetailScreen(
    jobId: Int,
    origin: String,
    onBackClick: () -> Unit,
    viewModel: JobDetailViewModel) {

    val rememberHostState = remember { SnackbarHostState() }
    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No Scope found")
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
        ?: throw IllegalStateException("No Scope found")
    val roundedCornerAnim by animatedVisibilityScope.transition
        .animateDp(label = "rounded corner") { enterExit: EnterExitState ->
            when (enterExit) {
                EnterExitState.PreEnter -> 20.dp
                EnterExitState.Visible -> 0.dp
                EnterExitState.PostExit -> 20.dp
            }
        }
    with(sharedTransitionScope) {
        ApplicationTheme {
            AppScaffold(
                snackBarHostState = rememberHostState,
                content = { padding ->
                    Box(
                        Modifier
                            .padding(padding)
                            .clip(RoundedCornerShape(roundedCornerAnim))
                            .sharedBounds(
                                rememberSharedContentState(
                                    key = SharedElementKey(
                                        jobId = jobId,
                                        origin = origin,
                                        type = SharedElementType.Bounds
                                    )
                                ),
                                animatedVisibilityScope,
                                clipInOverlayDuringTransition =
                                OverlayClip(RoundedCornerShape(roundedCornerAnim)),
                                boundsTransform = snackDetailBoundsTransform,
                                exit = fadeOut(nonSpatialExpressiveSpring()),
                                enter = fadeIn(nonSpatialExpressiveSpring()),
                            )
                            .fillMaxSize()
                            .background(color = ProductXTheme.colors.background)
                    ) {
                        Text("JobDetailScreen")
                    }
                }
            )
        }
    }

}

@Composable
fun JobDetailContent() {
}

@OptIn(ExperimentalSharedTransitionApi::class)
val snackDetailBoundsTransform = BoundsTransform { _, _ ->
    spatialExpressiveSpring()
}


fun <T> spatialExpressiveSpring() = spring<T>(
    dampingRatio = 0.8f,
    stiffness = 380f
)

fun <T> nonSpatialExpressiveSpring() = spring<T>(
    dampingRatio = 1f,
    stiffness = 1600f
)

@Preview
@Composable
fun JobDetailPreview() {
    /*    JobDetailScreen(Modifier, jobId = 10, origin = "origin", onTopicClick = {

        }, onBackClick = {})*/
    ApplicationTheme {
        AppScaffold(snackBarHostState = SnackbarHostState(),
            content = { padding ->
                Box(
                    Modifier
                        .padding(padding)
//                        .clip(RoundedCornerShape(roundedCornerAnim))
//                        .sharedBounds(
//                            rememberSharedContentState(
//                                key = SharedElementKey(
//                                    jobId = jobId,
//                                    origin = origin,
//                                    type = SharedElementType.Bounds
//                                )
//                            ),
//                            animatedVisibilityScope,
//                            clipInOverlayDuringTransition =
//                            OverlayClip(RoundedCornerShape(roundedCornerAnim)),
//                            boundsTransform = snackDetailBoundsTransform,
//                            exit = fadeOut(nonSpatialExpressiveSpring()),
//                            enter = fadeIn(nonSpatialExpressiveSpring()),
//                        )
                        .fillMaxSize()
                        .background(color = ProductXTheme.colors.background)
                ) {
                    Text("JobDetailScreen")
                }
            }
        )
    }
}