package com.example.demo_structure.screen.opportunity

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.demo_structure.ITEMS
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppBox
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppScaffold
import com.example.demo_structure.core.component.AppSnackBar
import com.example.demo_structure.core.component.ThemePreviews
import com.example.demo_structure.core.navigation.rememberAppState
import com.example.demo_structure.jobList
import com.example.demo_structure.screen.job_detail.JobDetailScreen1
import com.example.demo_structure.screen.job_detail.MyScreen
import com.example.demo_structure.screen.opportunity.component.FilterSection
import com.example.demo_structure.screen.opportunity.component.JobItemSection
import com.example.demo_structure.screen.opportunity.component.SearchBarSection
import com.example.demo_structure.screen.opportunity.component.SuggestionSection
import com.example.demo_structure.util.AnimatedVisibilitySlide
import com.example.domain.model.JobDetail
import kotlinx.coroutines.flow.Flow

/**
 * Created by Phạm Sơn at 23:25/8/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Composable
fun OpportunityScreen(viewModel: OpportunityViewModel, onJobClick: (JobDetail) -> Unit) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val lazyItem = viewModel.items.collectAsLazyPagingItems()
    val appState = rememberAppState()
    OpportunityContent(
        lazyItem = lazyItem,
        onBackClick = appState::upPress,
        onTextChange = viewModel::onTextChange,
        onSearch = viewModel::onSearch,
        onSearchWithCV = viewModel::onSearchWithCV,
        onJobClick = onJobClick
    )
}

@Composable
fun OpportunityContent(
    lazyItem: LazyPagingItems<JobDetail>,
    onBackClick: () -> Unit,
    onTextChange: (String) -> Unit,
    onSearch: () -> Unit,
    onSearchWithCV: () -> Unit,
    onJobClick: (JobDetail) -> Unit,
    isFilter: Boolean = false,
    isSuggestion: Boolean = false
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var visibilityFilter by remember { mutableStateOf(isFilter) }
    var visibilitySuggestion by remember { mutableStateOf(isSuggestion) }

    AppScaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = ProductXTheme.colorScheme.background_2,
        snackbarHost = {
            SnackbarHost(
                hostState = it,
                modifier = Modifier.navigationBarsPadding(),
                snackbar = { snackbarData -> AppSnackBar(snackbarData) }
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .pointerInput(Unit) { // Add pointerInput to detect outside clicks
                        detectTapGestures {
                            Log.d("QQQ", "outside")
                            focusManager.clearFocus(true)
                        }
                    }
            ) {
                 Column(Modifier.background(Color.White)) {
                     SearchBarSection(
                         focusRequester, onTextChange, onSearch,
                         isFilter = { isFilter -> visibilityFilter = isFilter },
                         isSuggestion = { isSuggestion -> visibilitySuggestion = isSuggestion }
                     )
                     FilterJobSection(visibilityFilter)
                     JobResultSection(lazyItem = lazyItem, onJobClick)
 //                    SuggestionJobSection(visibilitySuggestion)
                 }
            }


        })
}

@Composable
fun FilterJobSection(visibilityFilter: Boolean) {
    AnimatedVisibilitySlide(visibilityFilter) {
        FilterSection()
    }
}

@Composable
fun SuggestionJobSection(visibilitySuggestion: Boolean) {
    AnimatedVisibility(visibilitySuggestion) {
        SuggestionSection(
            list = listOf(
                "Product Designer", "Product Designer 1", "Product Designer 2",
                "Product Designer 3", "Product Designer 4", "Product Designer 5", "Product Designer 6"
            )
        )
    }
}


@Composable
fun JobRecommendSection() {
    AppBox(
        modifier = Modifier

            .fillMaxSize(),
        contentAlignment = Alignment.Center,
        backgroundColor = ProductXTheme.colorScheme.background_2,
    ) {

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun JobResultSection(lazyItem: LazyPagingItems<JobDetail>, onJobClick: (JobDetail) -> Unit) {
    val focusManager = LocalFocusManager.current
    var selectedJob: JobDetail? by remember { mutableStateOf(null) }

    AppBox(
        modifier = Modifier
            .pointerInput(Unit) { // Add pointerInput to detect outside clicks
                detectTapGestures {
                    Log.d("QQQ", "outside")
                    focusManager.clearFocus(true)
                }
            }
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
        backgroundColor = ProductXTheme.colorScheme.background_2,
    ) {
        AnimatedContent(
            targetState = selectedJob,
            transitionSpec = {
                val enter = slideInVertically(animationSpec = tween(durationMillis = 500)) { height -> height } + fadeIn(animationSpec = tween(durationMillis = 500))
                val exit = slideOutVertically(animationSpec = tween(durationMillis = 500)) { height -> -height } + fadeOut(animationSpec = tween(durationMillis = 500))
                if (targetState == null) {
                    enter with exit
                } else {
                    enter with exit
                }.using(SizeTransform(clip = false))
            }, label = ""
        ){ targetPhoto ->
            if (targetPhoto == null) {
                JobList(
                    lazyPagingItems = lazyItem,
                    selectedJob = null,
                    onJobClick = {
                        selectedJob = it
//                            onJobClick.invoke(it)
                    },
                    onBackClick = {

                    }
                )
            } else {
                JobDetailScreen1 (job = targetPhoto, onBack = {
                    selectedJob = null
                })
            }
        }

    }
}
sealed class NavigationType {
    object Detail : NavigationType()
    object Listing : NavigationType()
}
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun JobList(lazyPagingItems: LazyPagingItems<JobDetail>, selectedJob: JobDetail?, onJobClick: (JobDetail) -> Unit, onBackClick: () -> Unit) {

    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(jobList, key = { job -> job.jobId }) { job ->
            JobItemSection (job = job, onClick = onJobClick)
        }
    }
}


@Composable
fun SwipeToDismiss(
    modifier: Modifier = Modifier,
    visibleState: MutableTransitionState<Boolean>,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visibleState = visibleState,
        enter = scaleIn(animationSpec = tween(500, easing = FastOutSlowInEasing)) + fadeIn(animationSpec = tween(500, easing = FastOutSlowInEasing)),
        exit = scaleOut(animationSpec = tween(500, easing = FastOutSlowInEasing)) + fadeOut(animationSpec = tween(500, easing = FastOutSlowInEasing)),
    ) {
        content()
    }
}

@Composable
fun initJobList(lazyPagingItems: LazyPagingItems<JobDetail>, onJobClick: (JobDetail) -> Unit) {
    val lazyListState = rememberLazyListState()

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleItemIndex ->
                if (lastVisibleItemIndex != null &&
                    lastVisibleItemIndex >= lazyPagingItems.itemCount - 5 // Preload threshold
                ) {
                    lazyPagingItems.loadState.append
                    // You can also check if append loading state is not Loading to prevent multiple load calls.
                }
            }
    }
    LazyColumn(
        state = lazyListState,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            count = lazyPagingItems.itemCount,
            key = { index ->
                lazyPagingItems[index]?.jobId ?: index // Use a key if your data has unique id
            }
        ) { index ->
            val job = lazyPagingItems[index]
            job?.let {
                JobItemSection(it, onJobClick)
            }

        }
    }
}


@ThemePreviews
@Composable
fun OpportunityScreenPreview() {
    AppPreviewWrapper {
        val items: Flow<PagingData<JobDetail>> = Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2, enablePlaceholders = false),
            pagingSourceFactory = { JobPagingSource(ITEMS, 20) }
        ).flow.cachedIn(rememberCoroutineScope())

        OpportunityContent(lazyItem = items.collectAsLazyPagingItems(), onBackClick = {}, onTextChange = {}, onSearch = {}, onSearchWithCV = {}, onJobClick = {})

    }
}
