package com.example.demo_structure.screen.onboarding

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OnboardingScreenPreview() {

}

@Composable
fun OnboardingScreen(onNavigateToHome: () -> Unit) {

}