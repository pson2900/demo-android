package com.example.demo_structure.screen.search_result

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.demo_structure.core.component.ProductXPreviewWrapper
import com.example.demo_structure.core.component.ProductXScaffold
import com.example.demo_structure.core.component.ProductXSnackBar
import com.example.demo_structure.core.component.TopSearchAppBar
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.rememberAppState
import com.example.demo_structure.theme.ProductXApplicationTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

/**
 * Created by Phạm Sơn at 23:25/8/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Composable
fun SearchResultScreen(
    nestedNavigation: AppState,
    onTopicClick: (String) -> Unit,
    modifier: Modifier
) {
    val viewModel: SearchResultViewModel = koinViewModel()
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
            topBar = {
                TopSearchAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    actions = {
                        onTopicClick.invoke("hehe")
                    },
                    content = "Hello World"
                )
            },
            snackBarHostState = snackbarHostState,
            content = { padding ->
                Box(
                    modifier = modifier
                        .background(Color.White)
                        .padding(padding)
                        .fillMaxSize()
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
                    contentAlignment = Alignment.TopStart
                ) {

//                    Text("SearchResultScreen")
                }
            }
        )
    }
}


@Preview
@Composable
fun SearchResultScreenPreview() {
    ProductXPreviewWrapper {

        SearchResultScreen(nestedNavigation = rememberAppState(koinInject()),onTopicClick = { _ -> }, it)
    }
}

@Composable
fun HotKeyWord(modifier: Modifier) {
    Box(modifier = modifier) {

    }
}