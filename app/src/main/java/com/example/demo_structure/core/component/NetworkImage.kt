package com.example.demo_structure.core.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.Placeholder
import com.bumptech.glide.integration.compose.placeholder
import com.example.demo_structure.R


@OptIn(ExperimentalGlideComposeApi::class)
@Preview(showBackground = true)
@Composable
private fun NetworkImagePreview() {
    NetworkImage(
        imageUrl = "https://i.pinimg.com/originals/40/90/e6/4090e6607e8bea2c9845b12630a927fd.jpg",
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 0.dp)
            .width(120.dp)
            .height(120.dp),
        contentScale = ContentScale.Fit,
        contentDescription = "My Image",
        placeholder = placeholder(R.drawable.ic_launcher_background),
        error = placeholder(R.drawable.ic_launcher_background),
        radius = 6.dp,
        elevation = 4.dp
    )
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NetworkImage(
    imageUrl: String,
    modifier: Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    placeholder: Placeholder? = null,
    error: Placeholder? = null,
    radius: Dp = 0.dp,
    elevation: Dp = 4.dp
) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(elevation),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(
            topStart = radius,
            topEnd = radius,
            bottomStart = radius,
            bottomEnd = radius
        ), elevation = CardDefaults.cardElevation(elevation)
    ) {
        GlideImage(
            model = imageUrl,
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale,
            loading = placeholder,
            failure = error
        )
    }

}