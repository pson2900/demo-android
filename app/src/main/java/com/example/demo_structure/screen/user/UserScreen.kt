package com.example.demo_structure.screen.user

import androidx.annotation.VisibleForTesting
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.demo_structure.R
import com.example.demo_structure.core.component.AppLoadingWheel
import com.example.demo_structure.core.component.ProductXPreviewWrapper
import com.example.demo_structure.core.component.ProductXScaffold
import com.example.demo_structure.theme.ProductXApplicationTheme
import org.koin.androidx.compose.koinViewModel

/**
 * Created by Phạm Sơn at 15:17/3/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
internal fun UserRoute(
    modifier: Modifier = Modifier,
    onTopicClick: (String) -> Unit,
) {
    val userViewModel: UserViewModel = koinViewModel()
    val userState by userViewModel.menuUiState.collectAsStateWithLifecycle()
    UserScreen(
        modifier = modifier,
        state = userState,
        onTopicClick = onTopicClick,
        userViewModel = userViewModel
    )
}

/**
 * Displays the user's bookmarked articles. Includes support for loading and empty states.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
@Composable
internal fun UserScreen(
    state: UserState,
    onTopicClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    clearUndoState: () -> Unit = {},
    userViewModel: UserViewModel
) {

    val rememberHostState = remember { SnackbarHostState() }
    LifecycleEventEffect(Lifecycle.Event.ON_STOP) {
        clearUndoState()
    }

    when (state) {
        UserState.Loading -> LoadingState(modifier)
        UserState.Success -> {

        }
    }

    ProductXApplicationTheme {
        ProductXScaffold(
            modifier = modifier,
            snackBarHostState = rememberHostState
        ) {
            UserContent(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it)
            )
        }
    }


}

@Composable
fun UserContent(modifier: Modifier) {
    ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        val (borderUser, cardUser, progress) = createRefs()


        Box(
            modifier = Modifier
                .border(25.dp, Color.White, shape = CircleShape)
                .background(Color.White)
                .fillMaxSize()
                .constrainAs(cardUser) {
                    top.linkTo(borderUser.top, margin = 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    verticalBias = 0.5f
                },
            contentAlignment = Alignment.Center
        ) {
            Text("hello world")
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .padding(1.dp)
                .clip(CircleShape)
                .background(Color.DarkGray)
                .constrainAs(borderUser) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentAlignment = Alignment.Center
        ) {
            CircularProgressBar(percentage = 0.8f, number = 100)
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_urgent),
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .padding(20.dp),
                contentDescription = null
            )
        }

    }
}

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

@Preview
@Composable
fun UserContentPreview() {
    ProductXPreviewWrapper { modifier ->
        UserContent(modifier)
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

