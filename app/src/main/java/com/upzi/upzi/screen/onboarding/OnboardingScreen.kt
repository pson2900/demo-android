package com.upzi.upzi.screen.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.upzi.upzi.app.manager.theme.ProductXTheme
import com.upzi.upzi.core.component.AppPreviewWrapper
import com.upzi.upzi.core.component.AppScaffold
import com.upzi.upzi.screen.onboarding.component.Activities
import com.upzi.upzi.screen.onboarding.component.OnboardingAppbar


@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OnboardingScreenPreview() {
    AppPreviewWrapper { modifier ->

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(onNavigateToHome: () -> Unit) {
    val rememberHostState = remember { SnackbarHostState() }

    AppScaffold(
        modifier = Modifier.fillMaxSize(),
        snackBarHostState = rememberHostState,
        backgroundColor = ProductXTheme.colorScheme.background_1,
        topBar = {
            OnboardingAppbar(progress = 5, maxProgress = 10, onBackClick = {

            })
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Activities(modifier = Modifier.padding(16.dp))
        }
    }
}