package com.example.demo_structure.screen.education

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.data.proto.DataStoreManager
import com.example.demo_structure.app.manager.theme.ApplicationTheme
import com.example.demo_structure.core.component.AppBox
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppScaffold
import com.example.demo_structure.core.component.AppSnackBar
import com.example.demo_structure.core.component.ThemePreviews
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.rememberAppState
import com.example.demo_structure.screen.otp.OTPType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

/**
 * Created by Phạm Sơn at 23:25/8/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Composable
fun EducationScreen(
    viewModel: EducationViewModel,
    onNavigateToVerifyEmail: () -> Unit,
    onTopicClick: (String) -> Unit
) {
    val modifier: Modifier = Modifier.fillMaxSize()

    val authUiState by viewModel.authUiState.collectAsStateWithLifecycle()
    var isLogin by remember { mutableStateOf(false) }

    val state = viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val appState = rememberAppState()
    val time = appState.currentTimeZone.collectAsStateWithLifecycle()
    val currentTime by appState.currentTime.collectAsState(initial = "")

    LaunchedEffect(viewModel) {
       viewModel.getAuth()
    }

    LaunchedEffect(key1 = authUiState) {
        when (val state = authUiState) {
            is EducationState.Loading -> {

            }

            is EducationState.AuthSuccess -> {
                isLogin = true
            }

            is EducationState.AuthError -> {
                isLogin = false
            }

            else -> Unit
        }
    }

    ApplicationTheme {
        AppScaffold(
            modifier = modifier,
            contentWindowInsets = WindowInsets.systemBars,
            snackbarHost = {
                SnackbarHost(
                    hostState = it,
                    modifier = Modifier.systemBarsPadding(),
                    snackbar = { snackbarData -> AppSnackBar(snackbarData) }
                )
            },

            snackBarHostState = snackbarHostState,
            content = { padding ->
                AppBox(
                    modifier = modifier
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
                    Column {
                        Text("EducationScreen: ${time.value}\ncurrentTime: $currentTime")
                        val textContent = if (!isLogin) "Login" else "Logout"
                        Button(onClick = {
                           // onNavigateToVerifyEmail.invoke()
                            if (!isLogin) {
                                onNavigateToVerifyEmail.invoke()
                            } else {
                                isLogin = false
                                viewModel.logout()
                            }
                        }) {
                            Text(modifier = Modifier, text = textContent)
                        }
                    }
                }

            }
        )
    }
}


@ThemePreviews
@Composable
fun SearchResultScreenPreview() {
    AppPreviewWrapper {
        EducationScreen(
            viewModel = EducationViewModel(DataStoreManager(LocalContext.current),SavedStateHandle()),

            onNavigateToVerifyEmail = {},
            onTopicClick = {})
    }
}

