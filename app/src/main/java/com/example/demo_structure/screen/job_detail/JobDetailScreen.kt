package com.example.demo_structure.screen.job_detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_structure.app.manager.theme.LocalNavAnimatedVisibilityScope
import com.example.demo_structure.app.manager.theme.LocalSharedTransitionScope
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppBox
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppScaffold
import com.example.demo_structure.util.SharedElementKey
import com.example.demo_structure.util.SharedElementType
import com.example.domain.model.JobDetail
import org.koin.androidx.compose.koinViewModel

/**
 * Created by Phạm Sơn at 23:48/8/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MyScreen() {
    var currentScreen by remember { mutableStateOf("ScreenA") }

    AnimatedContent(
        targetState = currentScreen,
        transitionSpec = {
            // Define your transition here.  This is a basic slide left/right transition.
            if (targetState > initialState) {
                slideInHorizontally(initialOffsetX = { width -> width }) + fadeIn() with
                        slideOutHorizontally(targetOffsetX = { width -> -width }) + fadeOut()
            } else {
                slideInHorizontally(initialOffsetX = { width -> -width }) + fadeIn() with
                        slideOutHorizontally(targetOffsetX = { width -> width }) + fadeOut()
            }.using(
                // Configure the animation duration.
                SizeTransform(clip = false)
            )
        }
    ) { screen ->
        when (screen) {
            "ScreenA" -> ScreenA { currentScreen = "ScreenB" }
            "ScreenB" -> ScreenB { currentScreen = "ScreenA" }
        }
    }
}

@Composable
fun ScreenA(onNavigate: () -> Unit) {
    Button(onClick = onNavigate) {
        Text("Go to Screen B")
    }
}

@Composable
fun ScreenB(onNavigate: () -> Unit) {
    Button(onClick = onNavigate) {
        Text("Go to Screen A")
    }
}
@Composable
fun JobDetailScreen1(job: JobDetail, onBack: () -> Unit) {
    Surface(modifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(12.dp))
        //Use for zooming
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, "Back")
            }
            Text(text = "Job Details for ${job.jobTitle}")
            Text(text = "Company: ${job.companyTitle}")
            Text(text = "Salary: ${job.salary}")
            Text(text = "Location: ${job.location}")
            Text(text = "Description: ${job.description}")
        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun JobDetailScreen(
    jobId: Int,
    onBackClick: () -> Unit,
    viewModel: JobDetailViewModel = koinViewModel()
) {

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
        AppScaffold(
            backgroundColor = ProductXTheme.colorScheme.background_2,
            snackBarHostState = rememberHostState,
//            content = { padding ->
                content = {
                AppBox (
                    Modifier
//                        .padding(padding)
                        .clip(RoundedCornerShape(roundedCornerAnim))
                        .sharedBounds(
                            rememberSharedContentState(
                                key = SharedElementKey(
                                    jobId = jobId,
                                    origin = "origin",
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
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center,
                    backgroundColor = ProductXTheme.colorScheme.background_2,
                ) {
                    Text("JobDetailScreen", modifier = Modifier.clickable {
                        onBackClick()
                    })
                }
            }
        )
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
    AppPreviewWrapper {
        Box(it) {
            Text("JobDetailScreen")
        }
    }
}