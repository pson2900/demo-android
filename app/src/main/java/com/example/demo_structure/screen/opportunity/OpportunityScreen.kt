package com.example.demo_structure.screen.opportunity

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.demo_structure.app.manager.theme.ApplicationTheme
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppScaffold
import com.example.demo_structure.core.component.AppSnackBar
import com.example.demo_structure.core.component.AppSurface
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

/**
 * Created by Phạm Sơn at 23:25/8/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Composable
fun OpportunityScreen(viewModel: OpportunityViewModel, onTopicClick: (String) -> Unit) {
    val modifier: Modifier = Modifier.fillMaxSize()
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    ApplicationTheme {
        AppScaffold(
            modifier = modifier,
            contentWindowInsets = WindowInsets.systemBars,
            snackbarHost = {
                SnackbarHost(
                    hostState = it,
                    modifier = Modifier,
                    snackbar = { snackbarData -> AppSnackBar(snackbarData) }
                )
            },
            snackBarHostState = snackbarHostState,
            content = { padding ->
                AppSurface(
                    modifier = modifier
                ) {
                    Box(
                        modifier = modifier
                            .background(Color.White)
                            .clickable {
                                coroutineScope.launch {
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
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("OpportunityScreen")
                    }
                }
            })
    }
}


@Preview
@Composable
fun OpportunityScreenPreview() {
    AppPreviewWrapper {

        OpportunityScreen(viewModel = koinViewModel(), onTopicClick = { _ -> })
    }
}
