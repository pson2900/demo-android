package com.example.demo_structure.screen.login

import android.content.res.Configuration
import androidx.annotation.VisibleForTesting
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.remote.UIState
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppBarIcon
import com.example.demo_structure.core.component.AppLoadingWheel
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppScaffold
import com.example.demo_structure.core.component.AppTopBar
import com.example.demo_structure.core.component.otp.PassCodeTextField
import com.example.demo_structure.util.extension.isJsonObjectRegex
import com.example.domain.model.UserProfile
import kotlinx.coroutines.delay
import org.json.JSONObject

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
    viewModel: LoginViewModel = viewModel(),
    email: String,
    onNavigateForgotPasswordOtp: (String) -> Unit,
    onNavigateHomeScreen: () -> Unit,
    onNavigateOnBoarding: () -> Unit,
    onBack: () -> Unit
) {

    val loginState by viewModel.loginUiState.collectAsStateWithLifecycle()
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val rememberHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = loginState) {
        when (val state = loginState) {
            is UIState.Loading -> isLoading = true
            is UIState.Success -> {
                errorMessage = ""
                isLoading = false
                viewModel.saveAuth(state.data)
                viewModel.saveUserInfo(UserProfile(email = email, fullName = "Tung Be De"))
                // Toast.makeText(context, "login success", Toast.LENGTH_SHORT).show()
                delay(500)
                onNavigateOnBoarding.invoke()
            }

            is UIState.Error -> {
                val error =  state.appException.message
                error?.let {
                    if(isJsonObjectRegex(it)) {
                        val json = JSONObject(it)
                        errorMessage = json.getString("message") ?: ""
                    }else{
                        errorMessage = it
                    }
                }
                isLoading = false
            }

            else -> Unit
        }
    }

    AppScaffold(
        modifier = Modifier,
        topBar = {
            AppTopBar(modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                title = { Text("") },
                navigationIcon = {
                    AppBarIcon(
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        imageResource = R.drawable.ic_arrow,
                        clickable = {
                            onBack.invoke()
                        }
                    )
                })
        },
        snackBarHostState = rememberHostState,
        backgroundColor = ProductXTheme.colorScheme.background_1,
    ) {
        LoginContent(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
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
    val context = LocalContext.current

    ConstraintLayout(
        modifier = modifier
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
                }.testTag("TextFieldPassCode"),
            context = context,
            onValueChange = {
                onChangeError("")
                if (it != passCode) {
                    passCode = it
                    if (passCode.length == maxLength) {
                        onLogin.invoke(passCode)
                    }
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
            .padding(20.dp).testTag("textViewForgotPassword"), // Add padding for better touch target
        style = TextStyle(
            color = colorResource(R.color.violets_are_blue),
            fontSize = 16.sp
        )
    )
}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoginPreview() {
    AppPreviewWrapper { modifier ->
        LoginContent(modifier
            .fillMaxSize(), isLoading = true,
            errorMessage = "demo error",
            onChangeError = {},
            onLogin = {

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

