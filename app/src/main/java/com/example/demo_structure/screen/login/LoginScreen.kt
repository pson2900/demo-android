package com.example.demo_structure.screen.login

import android.content.res.Configuration
import androidx.annotation.VisibleForTesting
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.vectorResource
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
@OptIn(ExperimentalSharedTransitionApi::class)
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
            LoginContent(modifier = modifier)
        }
    }
}

@Composable
fun LoginContent(modifier: Modifier) {
    ConstraintLayout(
        modifier = modifier.background(Color.White)
    ) {


        @Composable
        fun CircularProgressBar(
            percentage: Float, number: Int, fontSize: TextUnit = 28.sp,
            radius: Dp = 50.dp, color: Color = Color.Green, strokeWidth: Dp = 5.dp, animDuration: Int = 1000, animDelay: Int = 0
        ) {
            var animationPlayed by remember { mutableStateOf(false) }
            val curPercentage = animateFloatAsState(
                targetValue = if (animationPlayed) percentage else 0f,
                animationSpec = tween(
                    durationMillis = animDuration,
                    delayMillis = animDelay
                )
            )
            LaunchedEffect(key1 = true) {
                animationPlayed = true
            }

            Box(
                modifier = Modifier.size(radius * 2f),
                contentAlignment = Alignment.Center,
            ) {
                Canvas(Modifier.size(radius * 2f)) {
                    drawArc(
                        color = color,
                        startAngle = -90f,
                        sweepAngle = 360 * curPercentage.value,
                        useCenter = false,
                        style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                    )
                }
            }
        }
    }
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

