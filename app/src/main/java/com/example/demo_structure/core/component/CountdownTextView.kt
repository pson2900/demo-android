package com.example.demo_structure.core.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
private fun CustomProgressDialogPreview() {
    CountdownTextView(Modifier.padding(16.dp), minutesState = 0.5f, onCountdownFinished = {

    })
}

@Composable
fun CountdownTextView(modifier: Modifier, style: TextStyle = LocalTextStyle.current, minutesState: Float, onCountdownFinished: () -> Unit = {}) {
    var remainingTime by remember(minutesState) { mutableIntStateOf((minutesState * 60).toInt()) }

    LaunchedEffect(key1 = minutesState) { // Add minutesState as a key to the LaunchedEffect
        while (remainingTime > 0) {
            delay(1000)
            remainingTime--
        }
        onCountdownFinished()
    }

    Text(modifier = modifier, style = style, text = formatTime(remainingTime))
}

fun formatTime(totalSeconds: Int): String {
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}