package com.example.demo_structure.screen.opportunity

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppBox
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppScaffold
import com.example.demo_structure.core.component.AppSnackBar
import com.example.demo_structure.core.component.ThemePreviews
import com.example.demo_structure.core.navigation.rememberAppState
import com.example.demo_structure.jobList
import com.example.demo_structure.screen.opportunity.component.FilterSection
import com.example.demo_structure.screen.opportunity.component.JobCardSection
import com.example.demo_structure.screen.opportunity.component.SearchBarSection
import com.example.demo_structure.screen.opportunity.component.SuggestionSection

/**
 * Created by Phạm Sơn at 23:25/8/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Composable
fun OpportunityScreen(viewModel: OpportunityViewModel) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val appState = rememberAppState()
    OpportunityContent(onBackClick = appState::upPress, onTextChange = viewModel::onTextChange, onSearch = viewModel::onSearch, onSearchWithCV = viewModel::onSearchWithCV)
}

@Composable
fun OpportunityContent(onBackClick: () -> Unit, onTextChange: (String) -> Unit, onSearch: () -> Unit, onSearchWithCV: () -> Unit, isFilter: Boolean = false, isSuggestion: Boolean = false) {
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
                JobResultSection()
                Column(Modifier.background(Color.White)) {
                    SearchBarSection(
                        focusRequester, onTextChange, onSearch, isFilter = { isFilter ->
                            visibilityFilter = isFilter

                        }, isSuggestion = { isSuggestion -> visibilitySuggestion = isSuggestion }
                    )
//                    FilterJobSection(visibilityFilter)
//                    SuggestionJobSection(visibilitySuggestion)
                }
            }


        })
}

@Composable
fun FilterJobSection(visibilityFilter: Boolean) {
    AnimatedVisibility(visibilityFilter) {
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

@Composable
fun JobResultSection() {
    val focusManager = LocalFocusManager.current
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
        JobList()
    }
}

@Composable
fun JobList() {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(jobList) { index, item ->
            JobCardSection(item)
        }
    }
}


@ThemePreviews
@Composable
fun OpportunityScreenPreview() {
    AppPreviewWrapper {
        OpportunityContent(onBackClick = {}, onTextChange = {}, onSearch = {}, onSearchWithCV = {})
    }
}
