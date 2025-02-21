package com.upzi.upzi.screen.job_detail

import android.app.Activity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.view.WindowCompat
import com.upzi.upzi.R
import com.upzi.upzi.app.manager.theme.AppIcons
import com.upzi.upzi.app.manager.theme.GenerateImage
import com.upzi.upzi.app.manager.theme.LocalNavAnimatedVisibilityScope
import com.upzi.upzi.app.manager.theme.LocalSharedTransitionScope
import com.upzi.upzi.app.manager.theme.ProductXTheme
import com.upzi.upzi.app.manager.theme.hexToColor
import com.upzi.upzi.core.component.AppPreviewWrapper
import com.upzi.upzi.core.component.AppText
import com.upzi.upzi.jobList
import com.upzi.upzi.util.JobCardSharedElementKey
import com.upzi.upzi.util.JobCardSharedElementKey.Companion.getShareKey
import com.upzi.upzi.util.SharedElementType
import com.upzi.upzi.util.durationChange
import com.upzi.domain.ifNotNull
import com.upzi.domain.model.JobDetail


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun JobDetailScreen(job: JobDetail, onBackClick: () -> Unit, onLogin: (String) -> Unit) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
    val dispatcherOwner = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    animatedVisibilityScope.ifNotNull {

    }

    val colorScheme = ProductXTheme.colorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = colorScheme.darkTheme
        }
    }
    val onBackPressedCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                isEnabled = false
                onBackClick.invoke()
            }
        }
    }

    DisposableEffect(dispatcherOwner) {
        dispatcherOwner?.addCallback(onBackPressedCallback)
        onDispose {
            onBackPressedCallback.remove()
        }
    }

    ifNotNull(sharedTransitionScope, animatedVisibilityScope) { result ->
        val animatedVisibility: AnimatedVisibilityScope = result.find { it is AnimatedVisibilityScope } as AnimatedVisibilityScope
        val sharedTransition: SharedTransitionScope = result.find { it is SharedTransitionScope } as SharedTransitionScope

        JobDetailContent(sharedTransition, animatedVisibility, job, onBackClick, onLogin)
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun JobDetailContent(
    sharedTransition: SharedTransitionScope, animatedVisibility: AnimatedVisibilityScope,
    job: JobDetail, onBackClick: () -> Unit, onLogin: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .safeDrawingPadding()
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(Color.Black.copy(0.1f))
                .fillMaxSize()
        ) {
            val (topBar, space, jobDetailCard, jobMatching) = createRefs()
            TopActions(
                modifier = Modifier
                    .constrainAs(topBar) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .background(ProductXTheme.colorScheme.primary)
                    .padding(start = 8.dp, end = 8.dp)
                    .fillMaxWidth(),
                onBackClick = onBackClick)

            Box(Modifier
                .fillMaxWidth()
                .height(70.dp)
                .constrainAs(space) {
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .background(ProductXTheme.colorScheme.primary, shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            )

            JobDetailContent(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .constrainAs(jobDetailCard) {
                        top.linkTo(topBar.bottom, margin = 16.dp)
                        start.linkTo(parent.start, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                    }
                    .fillMaxWidth(),

                sharedTransitionScope = sharedTransition,
                animatedVisibilityScope = animatedVisibility,
                job = job,
                onLogin = onLogin
            )
        }

        JobSuitableContent(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(0.1f))
                .padding(start = 16.dp, end = 16.dp)
        )

        JobMatchingContent(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(0.1f))
                .padding(start = 16.dp, end = 16.dp)
        )
    }
}

@Composable
fun TopActions(modifier: Modifier, onBackClick: () -> Unit) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppIcons.arrowLeft.GenerateImage(
            modifier = Modifier
                .background(color = Color.Black.copy(alpha = 0.2f), shape = CircleShape)
                .clip(shape = CircleShape)
                .clickable {
                    onBackClick.invoke()
                }
                .size(40.dp)
                .padding(8.dp),
            color = Color.White
        )
        Spacer(modifier = Modifier.weight(1f))
        AppIcons.bookMark.GenerateImage(
            modifier = Modifier
                .background(color = Color.Black.copy(alpha = 0.2f), shape = CircleShape)
                .clip(shape = CircleShape)
                .clickable {

                }
                .size(40.dp)
                .padding(8.dp),
            color = Color.White
        )
        AppIcons.share.GenerateImage(
            modifier = Modifier
                .background(color = Color.Black.copy(alpha = 0.2f), shape = CircleShape)
                .clip(shape = CircleShape)
                .clickable {
                }
                .size(40.dp)
                .padding(8.dp),
            color = Color.White
        )
    }
}

@Composable
fun JobSuitableContent(modifier: Modifier) {

    Column(modifier = modifier) {
        Text(text = "Bạn có phù hợp với công việc này?", modifier = Modifier.padding(top = 12.dp, bottom = 6.dp))
        Row(
            modifier = Modifier.padding(top = 6.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .border(1.dp, hexToColor("#F7F7F7"), RoundedCornerShape(16.dp))
                    .background(Color.Transparent, RoundedCornerShape(16.dp))
                    .padding(16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                AppIcons.JobDetailPercent.GenerateImage()
                Text(text = "Độ phù hợp với công việc")
                Text(text = "50% - Trung bình")
            }
            Column(
                modifier = Modifier
                    .border(1.dp, hexToColor("#F7F7F7"), RoundedCornerShape(16.dp))
                    .background(Color.Transparent, RoundedCornerShape(16.dp))
                    .padding(16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                AppIcons.JobDetailRank.GenerateImage()
                Text(text = "Độ phù hợp với công việc")
                Text(text = "50% - Trung bình")
            }
        }
    }
}

@Composable
fun JobMatchingContent(modifier: Modifier) {
    /* Column(modifier) {
         Text("Bạn đáp ứng 2/5 yêu cầu công việc")

         Text("Điểm cộng")
         *//* LazyColumn {
             items(2) {
                 Text(text = "Điểm cộng $it")
             }
         }*//*
    }*/
    Column(
        modifier = modifier
    ) {
        Text("Bạn đáp ứng 2/5 yêu cầu công việc")
        Column {
            for (i in 0..5) {
                Text(text = "Yêu cầu $i")
            }
        }
        Text("Điểm cộng")
        Column {
            for (i in 0..2) {
                Text(text = "Điểm cộng $i")
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun JobDetailContent(
    modifier: Modifier, sharedTransitionScope: SharedTransitionScope, animatedVisibilityScope: AnimatedVisibilityScope, job: JobDetail, onLogin: (String) -> Unit
) {
    val jobCardSharedElementKey = JobCardSharedElementKey(
        jobId = job.jobId,
        jobTitle = job.jobTitle,
        companyTitle = job.companyTitle,
        imageCompany = job.companyLogo,
        jobDescription = job.description,
        salary = job.salary,
        location = job.location
    )
    sharedTransitionScope.apply {
        Column(
            modifier = modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier

                    /* .sharedElement(
                         state = rememberSharedContentState(key = jobCardSharedElementKey.getShareKey(SharedElementType.Bounds)),
                         animatedVisibilityScope = animatedVisibilityScope,
                         boundsTransform = { _, _ -> tween(durationMillis = durationChange) }
                     )*/
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .clickable {
                        onLogin.invoke("pson2900@gmail.com")
                    },
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.75f)
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = jobCardSharedElementKey.getShareKey(SharedElementType.Title)),
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
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = jobCardSharedElementKey.getShareKey(SharedElementType.Description)),
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
                            state = rememberSharedContentState(key = jobCardSharedElementKey.getShareKey(SharedElementType.Company)),
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
                        .weight(0.25f)
                        .size(50.dp)
                        .sharedElement(
                            state = rememberSharedContentState(key = jobCardSharedElementKey.getShareKey(SharedElementType.Image)),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = durationChange)
                            }
                        ),
                    painter = painterResource(id = R.drawable.company_logo), // Replace with your image resource ID
                    contentDescription = "Grab Logo",
                )
            }

            Box(
                Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(hexToColor("#E2E8F0"))
            )

            Column(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .sharedElement(
                            state = rememberSharedContentState(key = jobCardSharedElementKey.getShareKey(SharedElementType.Salary)),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = durationChange)
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AppIcons.salary.GenerateImage()
                    AppText(text = job.salary)
                }
                Row(
                    modifier = Modifier
                        /* .sharedElement(
                             state = rememberSharedContentState(key = jobCardSharedElementKey.getShareKey(SharedElementType.TimeWork)),
                             animatedVisibilityScope = animatedVisibilityScope,
                             boundsTransform = { _, _ ->
                                 tween(durationMillis = durationChange)
                             }
                         )*/
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AppIcons.caseBusiness.GenerateImage()
                    AppText(text = "Thực tập 4 - 6 tháng")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .sharedElement(
                            state = rememberSharedContentState(key = jobCardSharedElementKey.getShareKey(SharedElementType.Location)),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = durationChange)
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AppIcons.location.GenerateImage()
                    AppText(text = "Quận 7, Hồ Chí Minh")
                }


            }
        }

    }

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
                JobDetailContent(
                    sharedTransition = this@SharedTransitionLayout,
                    animatedVisibility = this@AnimatedVisibility,
                    jobList[0], {}, {})
            }
        }

    }
}