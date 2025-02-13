package com.example.demo_structure.screen.community

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
fun CommunityScreen(viewModel: CommunityViewModel, onTopicClick: (String) -> Unit) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    AppScaffold(
        modifier = Modifier
            .fillMaxSize(),
//        contentWindowInsets = WindowInsets.systemBars,
        snackbarHost = {
            SnackbarHost(
                hostState = it,
                modifier = Modifier.navigationBarsPadding(),
                snackbar = { snackbarData -> AppSnackBar(snackbarData) }
            )
        },
        snackBarHostState = snackbarHostState,
        backgroundColor = Color.Transparent
    ){
        AppSurface(
            modifier = it.fillMaxSize(),
            backgroundColor = Color.White
        ) {
            Text(text = "CommunityScreen",
                modifier = Modifier.clickable {
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
                })
        }
    }
}


@Preview
@Composable
fun CommunityScreenPreview() {
    AppPreviewWrapper {

        CommunityScreen(viewModel = koinViewModel(), onTopicClick = { _ -> })
    }
}

