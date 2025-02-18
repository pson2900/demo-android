package com.example.demo_structure.screen.opportunity

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.demo_structure.ITEMS
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.LocalSharedTransitionScope
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppBox
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.ThemePreviews
import com.example.demo_structure.core.navigation.rememberAppState
import com.example.demo_structure.jobList
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun OpportunityScreen(viewModel: OpportunityViewModel, onJobClick: (JobDetail) -> Unit, animationContentScope: AnimatedContentScope) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val lazyItem = viewModel.items.collectAsLazyPagingItems()
    val appState = rememberAppState()
    val sharedTransitionScope = LocalSharedTransitionScope.current
    val navController = rememberNavController()
    sharedTransitionScope?.apply {
         OpportunityContent(
                 animatedVisibilityScope = animationContentScope,
                 lazyItem = lazyItem,
                 onBackClick = appState::upPress,
                 onTextChange = viewModel::onTextChange,
                 onSearch = viewModel::onSearch,
                 onSearchWithCV = viewModel::onSearchWithCV,
                 onJobClick = onJobClick
             )

       /* NavHost(
            navController = navController,
            startDestination = "list"
        ) {
            composable("list") {
                JobList(
                    animatedVisibilityScope = this,
                    lazyPagingItems = lazyItem,
                    selectedJob = null,
                    onJobClick = {
                        val jobDetailJson = Uri.encode(Gson().toJson(it))
                        navController.navigate("detail/${jobDetailJson}")
                    },
                    onBackClick = {

                    })
            }
            composable(
                route = "detail/{jobDetail}",
                arguments = listOf(
                    navArgument("jobDetail") {
                        type = NavType.StringType
                    },
                )
            ) { navBackStackEntry ->
                val arguments = requireNotNull(navBackStackEntry.arguments)
                val jobDetailJson = arguments.getString("jobDetail")
                val jobDetail = remember { Gson().fromJson(jobDetailJson, JobDetail::class.java) }
                jobDetail?.let { job ->
                    JobDetailScreen1(
                        animatedVisibilityScope = this,
                        job = job,
                        onBackClick = {
                        })
                }
            }
        }*/
    }
    /* OpportunityContent(
         animatedVisibilityScope = animationContentScope,
         lazyItem = lazyItem,
         onBackClick = appState::upPress,
         onTextChange = viewModel::onTextChange,
         onSearch = viewModel::onSearch,
         onSearchWithCV = viewModel::onSearchWithCV,
         onJobClick = onJobClick
     )*/
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun OpportunityContent(
    lazyItem: LazyPagingItems<JobDetail>,
    onBackClick: () -> Unit,
    onTextChange: (String) -> Unit,
    onSearch: () -> Unit,
    onSearchWithCV: () -> Unit,
    onJobClick: (JobDetail) -> Unit,
    isFilter: Boolean = false,
    isSuggestion: Boolean = false,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var visibilityFilter by remember { mutableStateOf(isFilter) }
    var visibilitySuggestion by remember { mutableStateOf(isSuggestion) }

    Column(Modifier.safeDrawingPadding()) {

        SearchBarSection(
            focusRequester, onTextChange, onSearch,
            isFilter = { filter -> visibilityFilter = filter },
            isSuggestion = { filter -> visibilitySuggestion = filter }
        )
        FilterJobSection(visibilityFilter)
        JobResultSection(
            animatedVisibilityScope = animatedVisibilityScope,
            lazyItem = lazyItem,
            onJobClick = onJobClick
        )

    }

    /*AppScaffold(
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
                    JobResultSection(
                        animatedVisibilityScope = animatedVisibilityScope,
                        lazyItem = lazyItem,
                        onJobClick = onJobClick
                    )
                    //                    SuggestionJobSection(visibilitySuggestion)
                }
            }


        })*/
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

@OptIn(ExperimentalAnimationApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun JobResultSection(lazyItem: LazyPagingItems<JobDetail>, onJobClick: (JobDetail) -> Unit, animatedVisibilityScope: AnimatedVisibilityScope) {
    val focusManager = LocalFocusManager.current
    val sharedTransitionScope = LocalSharedTransitionScope.current
    val navController = rememberNavController()
    sharedTransitionScope?.apply {

        /*    NavHost(
                navController = navController,
                startDestination = "list"
            ) {
                composable("list") {
                    JobList(
                        animatedVisibilityScope = this,
                        lazyPagingItems = lazyItem,
                        selectedJob = null,
                        onJobClick = {
                            val jobDetailJson = Uri.encode(Gson().toJson(it))
                            navController.navigate("detail/${jobDetailJson}")
                        },
                        onBackClick = {

                        })
                }
                composable(
                    route = "detail/{jobDetail}",
                    arguments = listOf(
                        navArgument("jobDetail") {
                            type = NavType.StringType
                        },
                    )
                ) { navBackStackEntry ->
                    val arguments = requireNotNull(navBackStackEntry.arguments)
                    val jobDetailJson = arguments.getString("jobDetail")
                    val jobDetail = remember { Gson().fromJson(jobDetailJson, JobDetail::class.java) }
                    jobDetail?.let { job ->
                        JobDetailScreen1(
                            animatedVisibilityScope = this,
                            job = job,
                            onBackClick = {
                            })
                    }
                }
            }*/

        JobList(
            lazyPagingItems = lazyItem,
            selectedJob = null,
            onJobClick = onJobClick,
            onBackClick = {

            },
            animatedVisibilityScope = animatedVisibilityScope
        )
        /* AppBox(
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

         }*/

    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun JobDetailScreen1(animatedVisibilityScope: AnimatedVisibilityScope, job: JobDetail, onBackClick: () -> Unit) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
    sharedTransitionScope?.apply {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier
                    .aspectRatio(16 / 9f)
                    .sharedElement(
                        state = rememberSharedContentState(key = "image/${job.companyLogo}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000)
                        }
                    ),
                painter = painterResource(id = R.drawable.company_logo), // Replace with your image resource ID
                contentDescription = null,
            )
            Column {
                Text(
                    modifier = Modifier.sharedElement(
                        state = rememberSharedContentState(key = "title/${job.jobTitle}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000)
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
                            tween(durationMillis = 1000)
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
                            tween(durationMillis = 1000)
                        }
                    ),
                    text = job.companyTitle,
                    fontSize = 14.sp,
                    color = Color.Gray // Adjust color
                )
            }

        }
    }

}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun JobList(
    lazyPagingItems: LazyPagingItems<JobDetail>,
    selectedJob: JobDetail?, onJobClick: (JobDetail) -> Unit, onBackClick: () -> Unit, animatedVisibilityScope: AnimatedVisibilityScope
) {

    LazyColumn(
//        contentPadding = PaddingValues(16.dp)
    ) {
        items(jobList, key = { job -> job.jobId }) { job ->
            JobItemSection(animatedVisibilityScope = animatedVisibilityScope, job = job, onClick = onJobClick)
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
//                JobItemSection(it, onJobClick)
            }

        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@ThemePreviews
@Composable
fun OpportunityScreenPreview() {
    AppPreviewWrapper {
        val items: Flow<PagingData<JobDetail>> = Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2, enablePlaceholders = false),
            pagingSourceFactory = { JobPagingSource(ITEMS, 20) }
        ).flow.cachedIn(rememberCoroutineScope())
        SharedTransitionScope {
            /* OpportunityContent(
                 lazyItem = items.collectAsLazyPagingItems(),
                 onBackClick = {},
                 onTextChange = {},
                 onSearch = {},
                 onSearchWithCV = {},
                 onJobClick = {},
                 animationContentScope = this
             )*/
        }


    }
}
