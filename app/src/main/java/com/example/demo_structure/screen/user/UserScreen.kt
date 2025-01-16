package com.example.demo_structure.screen.user

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.demo_structure.R
import com.example.demo_structure.core.component.AppLoadingWheel
import com.example.demo_structure.core.component.ProductXPreviewWrapper
import com.example.demo_structure.core.component.ProductXScaffold
import com.example.demo_structure.screen.user.component.BasicInformationItem
import com.example.demo_structure.screen.user.component.HeaderSection
import com.example.demo_structure.screen.user.component.OpportunitiesSection
import com.example.demo_structure.screen.user.component.ProfileStatusSection
import com.example.demo_structure.screen.user.component.SkillSection
import com.example.demo_structure.theme.ProductXApplicationTheme
import com.example.domain.model.BasicInformation
import org.koin.androidx.compose.koinViewModel

/**
 * Displays the user's bookmarked articles. Includes support for loading and empty states.
 */
@Composable
internal fun UserScreen(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit,
    clearUndoState: () -> Unit = {},
    userViewModel: UserViewModel = koinViewModel()
) {
    val state by userViewModel.menuUiState.collectAsStateWithLifecycle()
    val rememberHostState = remember { SnackbarHostState() }
    LifecycleEventEffect(Lifecycle.Event.ON_STOP) {
        clearUndoState()
    }

    when (state) {
        UserState.Loading -> LoadingState(modifier)
        UserState.Success -> {

        }
    }
    UserContent(
        modifier = modifier.fillMaxSize(),
        onNavigateToLogin = onNavigateToLogin,
        rememberHostState
    )


}

@Composable
fun UserContent(modifier: Modifier = Modifier, onNavigateToLogin: () -> Unit, rememberHostState: SnackbarHostState) {
    ProductXApplicationTheme {
        ProductXScaffold(
            modifier = modifier,
            snackBarHostState = rememberHostState
        ) { paddingValue ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(R.color.anti_flash_white))
            ) {
                val result = listOf(
                    BasicInformation(R.drawable.ic_my_profile_opprotunities_crow, "Kinh nghiệm làm việc", 0),
                    BasicInformation(R.drawable.ic_my_profile_opprotunities_crow, "Kinh nghiệm làm việc", 0),
                    BasicInformation(R.drawable.ic_my_profile_opprotunities_crow, "Kinh nghiệm làm việc", 0),
                    BasicInformation(R.drawable.ic_my_profile_opprotunities_crow, "Kinh nghiệm làm việc", 0),
                    BasicInformation(R.drawable.ic_my_profile_opprotunities_crow, "Kinh nghiệm làm việc", 0)
                )
                LazyColumn(Modifier) {
                    item { HeaderSection(title = "Nguyen Minh Hieu") }
                    item { Spacer(Modifier.size(24.dp)) }
                    item { ProfileStatusSection() }
                    item { Spacer(Modifier.height(24.dp)) }
                    item { OpportunitiesSection() }
                    item { Spacer(Modifier.height(24.dp)) }
                    item { SkillSection(listOf("Design Systems", "Typography", "Typography", "Typography")) }
                    item { Spacer(Modifier.height(24.dp)) }
                    item { Text("Thông tin hồ sơ", Modifier.padding(10.dp, 0.dp, 10.dp, 0.dp)) }
                    items(result.size) {
                        BasicInformationItem(result[it])
                    }
                    item { Spacer(Modifier.height(12.dp)) }
                }
            }
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

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun UserContentPreview() {
    ProductXPreviewWrapper { modifier ->
        val hostState = remember { SnackbarHostState() }
        UserContent(onNavigateToLogin = {

        }, modifier = modifier, rememberHostState = hostState)
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

