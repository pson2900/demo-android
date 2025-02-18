package com.example.demo_structure.screen.onboarding.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.R

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OnboardingHeaderPreview() {
    AppPreviewWrapper { modifier ->
        OnboardingAppbar(progress = 5, maxProgress = 10, onBackClick = {

        })
    }
}


@Composable
fun OnboardingAppbar(
    progress: Int, // Current progress
    maxProgress: Int, // Maximum progress
    onBackClick: () -> Unit
) {
    val progressFraction = remember(progress, maxProgress) {
        if (maxProgress == 0) 0f else progress.toFloat() / maxProgress.toFloat()
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_back_black),
                contentDescription = "Back"
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        LinearProgressIndicator(
            progress = progressFraction,
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .padding(end = 16.dp)
        )
    }
}