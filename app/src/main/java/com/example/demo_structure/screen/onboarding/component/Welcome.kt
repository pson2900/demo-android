package com.example.demo_structure.screen.onboarding.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.demo_structure.R
import com.example.demo_structure.core.component.AppPreviewWrapper


@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WelcomePreview() {
    AppPreviewWrapper { modifier ->
        Welcome(modifier
            .background(colorResource(R.color.moon_raker))
            .padding(16.dp))
    }
}

@Composable
fun Welcome(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.moon_raker))
    ) {
        val (logoView, button) = createRefs()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(logoView) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_sticker_hello),
                contentDescription = "Hello Sticker",
                modifier = Modifier.size(290.dp)
            )

            Text(
                text = "Chào \"đằng ấy\"\nMình cùng nhau đi khám phá\ncon đường sự nghiệp của bạn nhé.",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

        }

        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.royal_blue)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(1f)
                .height(56.dp)
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(text = "Bắt đầu", fontSize = 16.sp, color = Color.White)
        }
    }

}