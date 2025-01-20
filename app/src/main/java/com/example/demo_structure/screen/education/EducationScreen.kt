package com.example.demo_structure.screen.education

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
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
import com.example.demo_structure.app.manager.theme.ProductXApplicationTheme
import com.example.demo_structure.core.component.ProductXPreviewWrapper
import com.example.demo_structure.core.component.ProductXScaffold
import com.example.demo_structure.core.component.ProductXSnackBar
import com.example.demo_structure.core.component.ProductXSurface
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

/**
 * Created by Phạm Sơn at 23:25/8/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Composable
fun EducationScreen(viewModel: EducationResultViewModel, onTopicClick: (String) -> Unit) {
    val modifier: Modifier = Modifier.fillMaxSize()
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    ProductXApplicationTheme {
        ProductXScaffold(
            modifier = modifier,
            contentWindowInsets = WindowInsets.systemBars,
            snackbarHost = {
                SnackbarHost(
                    hostState = it,
                    modifier = Modifier.systemBarsPadding(),
                    snackbar = { snackbarData -> ProductXSnackBar(snackbarData) }
                )
            },

            snackBarHostState = snackbarHostState,
            content = { padding ->
                ProductXSurface(
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
                        Text("EducationScreen")
                    }
                }

            }
        )
    }
}


@Preview
@Composable
fun SearchResultScreenPreview() {
    ProductXPreviewWrapper {

        EducationScreen(viewModel = koinViewModel(), onTopicClick = { _ -> })
    }
}

@Composable
fun HotKeyWord(modifier: Modifier) {
    Box(modifier = modifier) {

    }
}