package com.upzi.upzi.screen.onboarding.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.upzi.upzi.R
import com.upzi.upzi.core.component.AppPreviewWrapper


@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OnboardingHeaderPreview() {
    AppPreviewWrapper { modifier ->
        QuestionHeader(modifier.padding(16.dp),
            question = "Bạn học trường nào? ")
    }
}

@Composable
fun QuestionHeader(modifier: Modifier = Modifier, question: String) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_stickers_question), // Replace with actual resource
            contentDescription = "Hello Sticker",
            modifier = Modifier
                .size(71.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        ConstraintLayout(modifier = Modifier.weight(1f)) {
            val (imageView, boxView) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.ic_bubbletail), // Replace with actual resource
                contentDescription = "bubble",
                modifier = Modifier
                    .rotate(-120f)
                    .constrainAs(imageView) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                    .size(20.dp)
            )
            Box(
                modifier = Modifier
                    .constrainAs(boxView) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                    .background(
                        color = colorResource(R.color.selago),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = question,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            }
        }
    }
}