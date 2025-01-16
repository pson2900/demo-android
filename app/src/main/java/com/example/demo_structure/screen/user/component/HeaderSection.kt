package com.example.demo_structure.screen.user.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.demo_structure.R
import com.example.demo_structure.core.component.ProductXPreviewWrapper
import com.example.demo_structure.core.component.ProductXSurface
import com.example.demo_structure.screen.user.CircularProgressBar
import com.example.demo_structure.theme.AppIcons.IconAdvancement
import com.example.demo_structure.theme.AppIcons.IconArrowRight
import com.example.demo_structure.theme.IconImage

/**
 * Created by Phạm Sơn at 13:20/14/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun ProfileHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressBar(percentage = 0.8f, number = 100)
            Image(
                painter = painterResource(id = R.drawable.ic_urgent),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Hiếu Minh Nguyễn", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Text(text = "Bạn đang theo đuổi", fontSize = 12.sp, color = Color.Gray)
            Spacer(modifier = Modifier.width(4.dp))
        }
        Text(text = "Product Designer, Ux research", fontSize = 14.sp)
    }

}

@Composable
fun HeaderSection(title: String) {

    ConstraintLayout(
        Modifier
            .background(Color.Blue)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (BorderLayout, AvataLayout) = createRefs()
        ProductXSurface(
            shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp),
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(BorderLayout) {
                    top.linkTo(AvataLayout.top, margin = 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }
        ) {
            Column(modifier = Modifier

                .background(color = colorResource(R.color.anti_flash_white)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                content = {
                    Spacer(
                        Modifier
                            .size(60.dp)
                            .background(color = colorResource(R.color.anti_flash_white)))

                                Text (modifier = Modifier, text = title, style = MaterialTheme.typography.headlineMedium, color = Color.Black
                    )

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp, 24.dp, 10.dp, 10.dp),
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                        shadowElevation = 5.dp,
                        border = BorderStroke(2.dp, Color.White)
                    ) {
                        val InternalRowModifier = Modifier
                        Row(
                            modifier = InternalRowModifier,
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconImage(InternalRowModifier.weight(1.5f), imageResource = IconAdvancement, contentDescription = "IconAdvancement")
                            Column(
                                InternalRowModifier
                                    .weight(7f)
                                    .padding(8.dp)
                            ) {
                                Text(modifier = Modifier, text = "Bạn đang theo đuổi", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
                                Text(modifier = Modifier, text = "Product Designer, Ux research", style = MaterialTheme.typography.labelLarge, color = Color.Black)
                            }
                            IconImage(InternalRowModifier.weight(1.5f), imageResource = IconArrowRight, contentDescription = "IconArrowRight")
                        }
                    }

                })


        }

        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .shadow(5.dp)
                .background(Color.DarkGray)
                .constrainAs(AvataLayout) {
                    top.linkTo(parent.top, margin = 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_urgent),
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .padding(20.dp),
                contentDescription = null
            )
        }
    }


}


@Preview("Light Mode", showBackground = true)
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun HeaderSectionPreview() {
    ProductXPreviewWrapper {
        HeaderSection(
            "Hiếu Minh Nguyễn"
        )
    }
}