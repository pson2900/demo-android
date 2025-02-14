package com.example.demo_structure.screen.opportunity

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppBox
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppScaffold
import com.example.demo_structure.core.component.AppSnackBar
import com.example.demo_structure.core.component.ThemePreviews
import com.example.demo_structure.core.navigation.rememberAppState
import com.example.demo_structure.screen.opportunity.component.SearchBarSection

/**
 * Created by Phạm Sơn at 23:25/8/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Composable
fun OpportunityScreen(viewModel: OpportunityViewModel) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val appState = rememberAppState()
    AppScaffold(
        modifier = Modifier.fillMaxSize(),
//        contentWindowInsets = WindowInsets.systemBars,
        backgroundColor = ProductXTheme.colorScheme.background_2,
        snackbarHost = {
            SnackbarHost(
                hostState = it,
                modifier = Modifier.navigationBarsPadding(),
                snackbar = { snackbarData -> AppSnackBar(snackbarData) }
            )
        },
//        content = { padding ->

        content = {
        })
}

@Composable
fun OpportunityContent(onBackClick: () -> Unit, onTextChange: (String) -> Unit, onSearch: () -> Unit, isFilter: Boolean = false) {
    AppScaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SearchBarSection(onTextChange, onSearch, isFilter)
        },
        backgroundColor = ProductXTheme.colorScheme.background_2,
        snackbarHost = {
            SnackbarHost(
                hostState = it,
                modifier = Modifier.navigationBarsPadding(),
                snackbar = { snackbarData -> AppSnackBar(snackbarData) }
            )
        },
        content = {
            AppBox(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
                backgroundColor = ProductXTheme.colorScheme.background_2,
            ) {
                Text(
                    text = "OpportunityScreen",
                    modifier = Modifier.clickable {
                        /*coroutineScope.launch {
                            val result = snackbarHostState.showSnackbar(
                                message = "Message",
                                actionLabel = "Action",
                            )
                            when (result) {
                                SnackbarResult.ActionPerformed -> {
                                    println("Action clicked")
                                }

                                SnackbarResult.Dismissed -> {
                                    println("Dismissed")
                                }
                            }
                        }*/
                    })
            }
        })
}

@ThemePreviews
@Composable
fun OpportunityScreenPreview() {
    AppPreviewWrapper {
        OpportunityContent(onBackClick = {}, onTextChange = {}, onSearch = {})
    }
}
