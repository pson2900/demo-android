package com.example.demo_structure.screen.job_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.AppIcons
import com.example.demo_structure.app.manager.theme.Generate
import com.example.demo_structure.app.manager.theme.LocalNavAnimatedVisibilityScope
import com.example.demo_structure.app.manager.theme.LocalSharedTransitionScope
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppBox
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppScaffold
import com.example.demo_structure.jobList
import com.example.demo_structure.util.AnimatedVisibilitySlide
import com.example.demo_structure.util.SharedElementKey
import com.example.demo_structure.util.SharedElementType
import com.example.demo_structure.util.durationChange
import com.example.domain.model.JobDetail
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun JobDetailScreen1(animatedVisibilityScope: AnimatedVisibilityScope, job: JobDetail, onBackClick: () -> Unit, onLogin: (String) -> Unit) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
    sharedTransitionScope?.apply {
        Column(
            modifier = Modifier
                .safeDrawingPadding()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AppIcons.arrowLeftIcon.Generate(
                    modifier = Modifier
                        .size(32.dp)
                        .padding(8.dp)
                        .clickable {
                            // Handle back navigation here
                        },
                    color = Color.Black
                )


            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        onLogin.invoke("pson2900@gmail.com")
                    },
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Column {
                    Text(
                        modifier = Modifier.sharedElement(
                            state = rememberSharedContentState(key = "title/${job.jobTitle}"),


                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = durationChange)
                            }
                        ),
                        text = job.jobTitle,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black // Adjust color
                    )
                    Text(
                        modifier = Modifier.sharedElement(
                            state = rememberSharedContentState(key = "description/${job.description}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = durationChange)
                            }
                        ),
                        text = job.description,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black // Adjust color
                    )
                    Text(
                        modifier = Modifier.sharedElement(
                            state = rememberSharedContentState(key = "company/${job.companyTitle}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = durationChange)
                            }
                        ),
                        text = job.companyTitle,
                        fontSize = 14.sp,
                        color = Color.Gray // Adjust color
                    )
                }
                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .sharedElement(
                            state = rememberSharedContentState(key = "image/${job.companyLogo}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = durationChange)
                            }
                        ),
                    painter = painterResource(id = R.drawable.company_logo), // Replace with your image resource ID
                    contentDescription = "Grab Logo",
                )
            }
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
                AppBox(
                    Modifier
//                        .padding(padding)
                        .clip(RoundedCornerShape(roundedCornerAnim))
                        .sharedBounds(
                            rememberSharedContentState(
                                key = SharedElementKey(
                                    jobId = jobId,
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
fun JobDetailPreview() {
    AppPreviewWrapper {
       SharedTransitionLayout {
           AnimatedVisibility(true) {
               JobDetailScreen1(this,
                   jobList[0], {}, {})
           }
       }

    }
}