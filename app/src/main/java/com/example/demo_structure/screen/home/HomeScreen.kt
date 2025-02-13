package com.example.demo_structure.screen.home

import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.animateDp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.demo_structure.JobDetail
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.LocalNavAnimatedVisibilityScope
import com.example.demo_structure.app.manager.theme.LocalSharedTransitionScope
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.app.manager.theme.hexToColor
import com.example.demo_structure.core.component.AppCard
import com.example.demo_structure.core.component.AppLoadingWheel
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppScaffold
import com.example.demo_structure.core.component.AppTopBar
import com.example.demo_structure.jobResult
import com.example.demo_structure.screen.job_detail.nonSpatialExpressiveSpring
import com.example.demo_structure.screen.job_detail.spatialExpressiveSpring
import org.koin.androidx.compose.koinViewModel

/**
 * Created by Phạm Sơn at 23:32/8/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
/**
 * Displays the user's bookmarked articles. Includes support for loading and empty states.
 */
private val HighlightCardWidth = 250.dp
private val HighlightCardPadding = 16.dp
private val Density.cardWidthWithPaddingPx
    get() = (HighlightCardWidth + HighlightCardPadding).toPx()


@Composable
internal fun LoadingState(modifier: Modifier = Modifier) {
    AppLoadingWheel(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize()
            .testTag("forYou:loading"),
        contentDesc = "forYou:loading",
    )
}

@Composable
internal fun HomeScreen(
    clearUndoState: () -> Unit = {},
    viewModel: HomeViewModel = koinViewModel(),
    onNavigateToJobDetail: (Int, String) -> Unit,
) {
    val state by viewModel.homeUiState.collectAsStateWithLifecycle()
    LifecycleEventEffect(Lifecycle.Event.ON_STOP) {
        clearUndoState()
    }

    when (state) {
        HomeState.Success -> {

        }

        is HomeState.Error -> {

        }

        is HomeState.Loading -> {
//            LoadingState(modifier)
        }
    }
    HomeContent { jobId, str ->
        onNavigateToJobDetail(jobId, str)
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(onItemSelected: (Int, String) -> Unit) {
    val rememberSnackbarHostState = remember { SnackbarHostState() }
    val columState = rememberLazyListState()
    val itemAnimationSpecFade = nonSpatialExpressiveSpring<Float>()
    val itemPlacementSpec = spatialExpressiveSpring<IntOffset>()
    AppScaffold(
        snackBarHostState = rememberSnackbarHostState,
        backgroundColor = ProductXTheme.colorScheme.background_2,
        topBar = {
            AppTopBar(title = { Text("Home") })
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it).navigationBarsPadding().fillMaxSize(),
            state = columState,
            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp, top = 10.dp, bottom = it.calculateBottomPadding())
//            contentPadding = it
        ) {
            itemsIndexed(jobResult) { index, item ->
                ItemResult(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(
                            fadeInSpec = itemAnimationSpecFade,
                            fadeOutSpec = itemAnimationSpecFade,
                            placementSpec = itemPlacementSpec
                        ), jobDetail = item,
                    onItemSelected = { jobId, str ->
                        onItemSelected.invoke(jobId, str)
                    }
                )
            }
        }
    }


}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true)
@Composable
fun ItemResultPreview() {
    AppPreviewWrapper {
        ItemResult(Modifier, jobResult[0], onItemSelected = { jobId, str ->

        })
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AppPreviewWrapper {
        Box(it) {
            HomeContent { jobId, str ->

            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ItemResult(modifier: Modifier = Modifier, jobDetail: JobDetail, onItemSelected: (Int, String) -> Unit) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No Scope found")
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
        ?: throw IllegalStateException("No Scope found")
    with(sharedTransitionScope) {
        val roundedCornerAnimation by animatedVisibilityScope.transition
            .animateDp(label = "rounded corner") { enterExit: EnterExitState ->
                when (enterExit) {
                    EnterExitState.PreEnter -> 0.dp
                    EnterExitState.Visible -> 20.dp
                    EnterExitState.PostExit -> 20.dp
                }
            }
        AppCard(
            elevation = 0.dp,
            shape = RoundedCornerShape(roundedCornerAnimation),
            modifier = Modifier
                .testTag("Tag: ${jobDetail.jobTitle}")
                .fillMaxWidth()
                .padding(bottom = 16.dp)

                .size(
                    width = HighlightCardWidth,
                    height = 150.dp
                )
                .clip(shape = RoundedCornerShape(roundedCornerAnimation))
                .border(
                    width = 1.dp,
                    color = ProductXTheme.colorScheme.outline,
                    shape = RoundedCornerShape(roundedCornerAnimation)
                )
                .clickable {
                    onItemSelected(jobDetail.jobId, "origin")
                }
        ) {
            BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
                val constraints = if (minWidth < 600.dp) {
                    dynamicConstraints(margin = 10.dp) // Portrait constraints
                } else {
                    dynamicConstraints(margin = 20.dp) // Landscape constraints
                }
                ConstraintLayout(
                    constraints, modifier = modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {

                    Image(
                        painter = painterResource(R.drawable.company_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .testTag("logoCompany")
                            .layoutId("image")
                            .size(24.dp)
                    )
                    Text(
                        text = jobDetail.jobTitle,
                        modifier = Modifier
                            .layoutId("title")
                            .testTag("jobTitle")
                    )
                    Image(
                        painter = painterResource(R.drawable.ic_urgent),
                        contentDescription = null,
                        modifier = Modifier
                            .layoutId("imgTitle")
                            .padding(0.dp, 0.dp, 5.dp, 0.dp)
                    )
                    Text(
                        text = jobDetail.company,
                        modifier = Modifier.layoutId("companyTitle")
                    )
                    Box(
                        modifier = Modifier
                            .layoutId("line")
                            .height(25.dp)
                            .width(1.dp)
                            .background(Color.Black)

                    )
                    Row(modifier = Modifier.layoutId("match")) {
                        Image(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            painter = painterResource(R.drawable.ic_matching),
                            contentDescription = null,
                        )
                        Text("Phu hop")
                    }
                    Text(
                        text = jobDetail.salary,
                        modifier = Modifier.layoutId("description")
                    )
                }
            }
        }
    }
}

private fun dynamicConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val title = createRefFor("title")
        val companyTitle = createRefFor("companyTitle")
        val imgTitle = createRefFor("imgTitle")
        val description = createRefFor("description")
        val line = createRefFor("line")
        val match = createRefFor("match")

        constrain(image) {
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start)
            absoluteLeft.linkTo(parent.absoluteLeft)
        }
        constrain(companyTitle) {
            end.linkTo(parent.end)
            top.linkTo(image.top)
            start.linkTo(image.end)
            bottom.linkTo(image.bottom)
        }
        constrain(title) {
            top.linkTo(image.bottom, margin = margin)
            start.linkTo(imgTitle.end)
            end.linkTo(line.start)
        }

        constrain(line) {
            top.linkTo(title.top)
            bottom.linkTo(title.bottom)
            start.linkTo(title.end, margin = 4.dp)
            end.linkTo(match.start, margin = 4.dp)
        }
        constrain(match) {
            top.linkTo(title.top)
            bottom.linkTo(title.bottom)
            start.linkTo(line.end)
        }
        constrain(imgTitle) {
            start.linkTo(parent.start)
            top.linkTo(title.top)
            bottom.linkTo(title.bottom)
        }
        constrain(description) {
            top.linkTo(title.bottom, margin = margin)
        }
    }
}