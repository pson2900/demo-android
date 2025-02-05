package com.example.demo_structure.screen.login

import android.content.res.Configuration
import androidx.annotation.VisibleForTesting
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.demo_structure.R
import com.example.demo_structure.core.component.AppLoadingWheel
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppScaffold
import com.example.demo_structure.app.manager.theme.ApplicationTheme
import com.example.demo_structure.core.component.AppBarIcon
import com.example.demo_structure.core.component.TopAppBar
import com.example.demo_structure.core.component.otp.PassCodeTextField
import com.example.demo_structure.screen.home.LoadingState
import org.koin.androidx.compose.koinViewModel

/**
 * Created by Phạm Sơn at 15:17/3/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
internal fun LoginRoute(
    onNavigateLogin: (String) -> Unit,
) {
    val loginViewModel: LoginViewModel = koinViewModel()
    val loginState by loginViewModel.menuUiState.collectAsStateWithLifecycle()
    LoginScreen(
        modifier = Modifier.fillMaxSize(),
        state = loginState
    )
}

/**
 * Displays the user's bookmarked articles. Includes support for loading and empty states.
 */
@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
@Composable
internal fun LoginScreen(
    state: LoginState,
    modifier: Modifier = Modifier,
) {

    val rememberHostState = remember { SnackbarHostState() }

    when (state) {
        LoginState.Loading -> LoadingState(modifier)
        LoginState.Success -> {

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
                LoginContent(modifier = modifier)
            }
        }
    }
}

@Composable
fun LoginContent(modifier: Modifier = Modifier) {
    var otpError by remember { mutableStateOf("") }
    var otpValue by remember { mutableStateOf("") }
    val maxLength = 6
    ConstraintLayout(
        modifier = modifier
            .background(Color.White)
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding() // Add imePadding here
    ) {
        val (logo, textView, passCode, columnBottom) = createRefs()
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
                .constrainAs(passCode) {
                    top.linkTo(textView.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onValueChange = {
                otpError = ""
                otpValue = it
                if(otpValue.length == maxLength){
                    if(otpValue == "123456"){

                    }else{
                        otpError = "error"
                    }
                }
            },
            maxLength, errorMessage = otpError
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(columnBottom) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }.padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ForgotPasswordText()
        }
    }
}

@Composable
fun ColumnScope.ForgotPasswordText() {
    Text(
        text = "Quên mật khẩu",
        modifier = Modifier
            .clickable {
                // Handle forgot password click
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
        LoginContent(modifier)
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

