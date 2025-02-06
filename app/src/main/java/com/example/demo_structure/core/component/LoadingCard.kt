package com.example.demo_structure.core.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
private fun LoadingCardPreview() {
    LoadingCard(Modifier.padding(16.dp))
}

@Composable
fun LoadingCard(modifier: Modifier, size: Dp = 30.dp) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .width(size)
                .height(size),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Gray
            ),
            shape = RoundedCornerShape(
                topStart = size,
                topEnd = size,
                bottomStart = size,
                bottomEnd = size
            ), elevation = CardDefaults.cardElevation(4.dp)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            )
        }
    }
}