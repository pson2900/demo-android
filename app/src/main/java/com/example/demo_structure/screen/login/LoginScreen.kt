package com.example.demo_structure.screen.login

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.demo_structure.R
import com.example.demo_structure.core.component.AppLoadingWheel
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppScaffold
import com.example.demo_structure.app.manager.theme.ApplicationTheme
import com.example.demo_structure.core.component.AppBarIcon
import com.example.demo_structure.core.component.TopAppBar
import com.example.demo_structure.core.component.otp.PassCodeTextField

/**
 * Created by Phạm Sơn at 15:17/3/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

/**
 * Displays the user's bookmarked articles. Includes support for loading and empty states.
 */
@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
@Composable
internal fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(),
    email: String,
    onNavigateForgotPasswordOtp: (String) -> Unit
) {

    val loginState by viewModel.loginUiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }


    val rememberHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = loginState) {
        when (val state = loginState) {
            is LoginState.Loading -> {
                if (state.isLoading) {
                    isLoading = true
                }
            }

            is LoginState.LoginSuccess -> {
                errorMessage = ""
                isLoading = false
                if (state.authentication != null) {
                    viewModel.saveAuth(state.authentication)
                    Toast.makeText(context, "login success", Toast.LENGTH_SHORT).show()
                } else {
                    errorMessage = "Mã không đúng. Thử lại nhé!"
                }
            }

            is LoginState.Error -> {
                errorMessage = "Mã không đúng. Thử lại nhé!"
                isLoading = false
            }

            else -> Unit
        }
    }

    ApplicationTheme {
        AppScaffold(
            modifier = modifier,
            snackBarHostState = rememberHostState
        ) {
            Column {
                TopAppBar(title = {
                    Text("")
                },
                    navigationIcon = {
                        AppBarIcon(
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            imageResource = R.drawable.ic_back_arrow
                        )
                    })
                LoginContent(
                    modifier = modifier,
                    isLoading = isLoading,
                    errorMessage = errorMessage,
                    onChangeError = { errorMessage = "" },
                    onLogin = {
                        viewModel.login(email, it)
                    }, onForgotPassword = {
                        onNavigateForgotPasswordOtp(email)
                    }
                )
            }
        }
    }
}

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    errorMessage: String,
    onChangeError: (String) -> Unit,
    onLogin: (String) -> Unit,
    onForgotPassword: () -> Unit
) {
    var passCode by remember { mutableStateOf("") }
    val maxLength = 6


    ConstraintLayout(
        modifier = modifier
            .fillMaxHeight()
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding() // Add imePadding here
    ) {
        val (logo, textView, passCodeTextField, columnBottom, loadingView) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.ic_rondy_stickers),
            contentDescription = "Image",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 24.dp)
                .height(110.dp)
                .constrainAs(logo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = "Nhập mã pin",
            modifier = Modifier
                .constrainAs(textView) {
                    top.linkTo(logo.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(start = 24.dp, end = 24.dp, bottom = 8.dp, top = 24.dp),
            style = TextStyle(fontSize = 24.sp),
            textAlign = TextAlign.Center
        )

        PassCodeTextField(
            modifier = Modifier
                .constrainAs(passCodeTextField) {
                    top.linkTo(textView.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onValueChange = {
                onChangeError("")
                passCode = it
                if (passCode.length == maxLength) {
                    onLogin.invoke(passCode)
                }
            },
            maxLength, errorMessage = errorMessage
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(columnBottom) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ForgotPasswordText {
                onForgotPassword.invoke()
            }
        }

        if (isLoading) {
            LoadingState(Modifier.constrainAs(loadingView) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        }
    }
}

@Composable
fun ColumnScope.ForgotPasswordText(onClick: () -> Unit) {
    Text(
        text = "Quên mật khẩu",
        modifier = Modifier
            .clickable {
                onClick.invoke()
            }
            .padding(vertical = 16.dp), // Add padding for better touch target
        style = TextStyle(
            color = colorResource(R.color.violets_are_blue),
            fontSize = 16.sp
        )
    )
}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun UserContentPreview() {
    AppPreviewWrapper { modifier ->
        LoginContent(modifier, isLoading = false, errorMessage = "", onChangeError = {}, onLogin = {

        }, onForgotPassword = {})
    }
}


@Composable
private fun LoadingState(modifier: Modifier = Modifier) {
    AppLoadingWheel(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize()
            .testTag("forYou:loading"),
        contentDesc = "forYou:loading",
    )
}

