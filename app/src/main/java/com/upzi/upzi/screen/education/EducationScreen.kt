package com.upzi.upzi.screen.education

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.upzi.upzi.app.manager.theme.ProductXTheme
import com.upzi.upzi.core.component.AppBox
import com.upzi.upzi.core.component.AppPreviewWrapper
import com.upzi.upzi.core.component.AppScaffold
import com.upzi.upzi.core.component.AppSnackBar
import com.upzi.upzi.core.component.ThemePreviews
import com.upzi.upzi.core.navigation.rememberAppState

import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone

/**
 * Created by Phạm Sơn at 23:25/8/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Composable
fun EducationScreen(viewModel: EducationViewModel,
                    onNavigateToVerifyEmail: () -> Unit,
                    onNavigateToOnBoarding: () -> Unit,
                    onTopicClick: (String) -> Unit) {

    val authUiState by viewModel.authUiState.collectAsStateWithLifecycle()
    var (isLogin, setLogin) = remember { mutableStateOf(false) }

    val state = viewModel.state.collectAsStateWithLifecycle()
    val appState = rememberAppState()
    val time = appState.currentTimeZone.collectAsStateWithLifecycle()
    val currentTime by appState.currentTime.collectAsState(initial = "")

    EducationContent(time.value, currentTime.toString(), isLogin,
        onNavigateToOnBoarding = onNavigateToOnBoarding
        , onNavigateLogin = { result ->
            if (!isLogin) {
                onNavigateToVerifyEmail.invoke()
            } else {
                setLogin(false)
                viewModel.logout()
            }
        })
}


@Composable
fun EducationContent(time: TimeZone,
                     currentTime: String,
                     isLogin: Boolean,
                     onNavigateLogin: (Boolean) -> Unit,
                     onNavigateToOnBoarding: () -> Unit,) {
    val modifier: Modifier = Modifier.fillMaxSize()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    AppScaffold(
        modifier = Modifier,
//        contentWindowInsets = WindowInsets.systemBars,
        snackbarHost = {
            SnackbarHost(
                hostState = it,
                modifier = Modifier.systemBarsPadding(),
                snackbar = { snackbarData -> AppSnackBar(snackbarData) }
            )
        },
        backgroundColor = ProductXTheme.colorScheme.background_2,
        snackBarHostState = snackbarHostState,
//        content = { padding ->
        content = {
            AppBox(
                modifier = Modifier
                    .padding(it)
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
                backgroundColor = ProductXTheme.colorScheme.background_2,
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text("EducationScreen: ${time}\ncurrentTime: $currentTime")
                    val textContent = if (!isLogin) "Login" else "Logout"
                    Text("EducationScreen: ${time}\ncurrentTime: $currentTime")
                    Button(onClick = {
//                        onNavigateLogin.invoke(isLogin)
                        // onNavigateToVerifyEmail.invoke()
                        onNavigateToOnBoarding.invoke()

                    }) {
                        Text(modifier = Modifier, text = textContent)
                    }
                }
            }

        }
    )
}

@ThemePreviews
@Composable
fun SearchResultScreenPreview() {
    AppPreviewWrapper {
        EducationContent(time = TimeZone.currentSystemDefault(),
            currentTime = "hehe",
            isLogin = false,
            onNavigateLogin = {

            }) {

        }
    }
}

