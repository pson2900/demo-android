package com.example.demo_structure.screen.onboarding.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.demo_structure.R
import com.example.demo_structure.core.component.AppPreviewWrapper


@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CareerSelectionPreview() {
    AppPreviewWrapper { modifier ->
        CareerSelection(
            modifier
                .padding(16.dp),
            onCvAvailable = {},
            cocoQuestion = {}
        )
    }
}

@Composable
fun CareerSelection(
    modifier: Modifier = Modifier,
    onCvAvailable: () -> Unit,
    cocoQuestion: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_stickers_hand), // Replace with actual resource
            contentDescription = "Hello Sticker",
            modifier = Modifier
                .width(173.dp)
                .height(110.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        ConstraintLayout {
            val (imageView, boxView) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.ic_bubbletail), // Replace with actual resource
                contentDescription = "bubble",
                modifier = Modifier
                    .constrainAs(imageView) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .size(20.dp)
            )
            Box(
                modifier = Modifier
                    .constrainAs(boxView) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(top = 12.dp)
                    .background(
                        color = colorResource(R.color.selago),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = "Coco cần tìm hiểu về bạn để giúp bạn chọn con đường sự nghiệp phù hợp.\nBạn chọn 1 trong 2 cách thức sau nhé",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        CareerOption(
            icon = painterResource(id = R.drawable.ic_cv), // Replace with actual resource
            title = "Dùng CV có sẵn",
            subtitle = "Tiết kiệm 90% thời gian",
            label = "Khuyên dùng",
            onClick = onCvAvailable
        )

        Spacer(modifier = Modifier.height(12.dp))

        CareerOption(
            icon = painterResource(id = R.drawable.ic_questions), // Replace with actual resource
            title = "Trả lời câu hỏi từ CoCo",
            subtitle = "",
            label = "",
            onClick = cocoQuestion
        )
    }
}

@Composable
fun CareerOption(
    icon: androidx.compose.ui.graphics.painter.Painter,
    title: String,
    subtitle: String,
    label: String,
    onClick: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth(1f)
            .clip(RoundedCornerShape(12.dp))
            .background(color = colorResource(R.color.white))
            .border(1.dp, colorResource(R.color.alto), RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
    ) {
        val (contentView, labelView) = createRefs()
        Row(
            modifier = Modifier
                .constrainAs(contentView) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(color = colorResource(R.color.white))
                .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
                .clickable(onClick = onClick)
                .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = icon,
                contentDescription = title,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                if (subtitle.isNotEmpty()) {
                    Text(text = subtitle, fontSize = 14.sp, color = Color.Gray)
                }
            }
        }

        if (label.isNotEmpty()) {
            RecommendedLabel(modifier = Modifier.constrainAs(labelView) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }, label = label)
        }
    }
}

@Composable
fun RecommendedLabel(modifier: Modifier = Modifier, label: String) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(bottomStart = 11.dp, topEnd = 11.dp))
            .background(colorResource(R.color.carousel_pink)) // Light purple background
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = colorResource(R.color.royal_blue) // Purple text color
        )
    }
}
