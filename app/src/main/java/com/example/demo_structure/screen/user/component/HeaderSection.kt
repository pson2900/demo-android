package com.example.demo_structure.screen.user.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.AppIcons
import com.example.demo_structure.app.manager.theme.IconImage
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppSurface
import com.example.demo_structure.core.component.AppText

@Composable
fun HeaderSection(title: String, avatar: String) {
    ConstraintLayout(
        Modifier
            .background(colorResource(R.color.violets_are_blue))
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (BorderLayout, AvataLayout, Setting) = createRefs()
        Box(
            Modifier
                .size(48.dp)
                .clip(CircleShape) // Clip to circle
                .background(Color.Black.copy(alpha = 0.2f), CircleShape)
                .constrainAs(Setting) {
                    top.linkTo(parent.top, 40.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                }
                .clickable { },

            contentAlignment = Alignment.Center
        ) {
            Image(ImageVector.vectorResource(R.drawable.ic_setting), contentDescription = null)
        }
        AppSurface(
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
                            .background(color = colorResource(R.color.anti_flash_white))
                    )
                    ProductXTheme.cardTheme
                    AppText(
                        modifier = Modifier, text = title,
                        style = ProductXTheme.typography.SemiBoldHeadingMedium,
                        color = Color.Black
                    )

                    AppSurface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .padding(10.dp, 24.dp, 10.dp, 10.dp)
                            .clickable {  }
                            .height(72.dp),
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(2.dp, Color.White),
                    ) {
                        Row(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconImage(Modifier.weight(1.5f), imageResource = AppIcons.advancementIcon, contentDescription = "IconAdvancement")
                            Column(
                                Modifier
                                    .weight(7f)
                                    .padding(8.dp)
                            ) {
                                AppText(modifier = Modifier, text = "Bạn đang theo đuổi",
                                    style = ProductXTheme.typography.RegularBodyMedium, color = Color.Gray)
                                AppText(modifier = Modifier, text = "Product Designer, Ux research",
                                    style = ProductXTheme.typography.SemiBoldBodyLarge, color = Color.Black)
                            }
                            IconImage(Modifier.weight(1.5f), imageResource = AppIcons.arrowRightIcon, contentDescription = "IconArrowRight")
                        }
                    }
                })
        }

        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .shadow(5.dp)
                .border(4.dp, Color.White, CircleShape)
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
    AppPreviewWrapper {
        HeaderSection(
            "Hiếu Minh Nguyễn", ""
        )
    }
}