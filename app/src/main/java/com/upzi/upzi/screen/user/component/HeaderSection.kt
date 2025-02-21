package com.upzi.upzi.screen.user.component

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.upzi.upzi.R
import com.upzi.upzi.app.manager.theme.AppIcons
import com.upzi.upzi.app.manager.theme.ProductXTheme
import com.upzi.upzi.app.manager.theme.GenerateImage
import com.upzi.upzi.app.manager.theme.hexToColor
import com.upzi.upzi.core.component.AppCard
import com.upzi.upzi.core.component.AppPreviewWrapper
import com.upzi.upzi.core.component.AppText

@Composable
fun HeaderSection(title: String, avatar: String) {
    ConstraintLayout(
        Modifier
            .background(colorResource(R.color.violets_are_blue))
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (BorderLayout, IconEdit, AvataLayout, Setting) = createRefs()
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
        val iconMargin = 50.dp
        AppCard(
            modifier = Modifier

                .background(color = colorResource(R.color.anti_flash_white), shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp))
                .fillMaxWidth()
                .constrainAs(BorderLayout) {
                    top.linkTo(AvataLayout.top, margin = iconMargin)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)
        ) {
            Column(modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.White, colorResource(R.color.anti_flash_white))
                    )
                )
                .padding(start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                content = {
                    Spacer(
                        Modifier
                            .height(iconMargin)
                            .background(color = colorResource(R.color.anti_flash_white))
                    )
                    AppText(
                        modifier = Modifier, text = title,
                        style = ProductXTheme.typography.SemiBold.Heading.Medium,
                        color = Color.Black
                    )
                    Spacer(
                        Modifier
                            .height(24.dp)
                            .background(color = colorResource(R.color.anti_flash_white))
                    )
                    Box(
                        modifier = Modifier
                            .background(colorResource(R.color.white), shape = RoundedCornerShape(16.dp))
                            .clip(shape = RoundedCornerShape(16.dp))
                            .testTag("HeaderSection_Job")
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier    .padding(12.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(hexToColor("#EEEBFF"), RoundedCornerShape(8.dp))
                                    .height(48.dp)
                                    .width(48.dp)
                                    .weight(1.5f),
                                contentAlignment = Alignment.Center
                            ) {
                                AppIcons.advancementIcon.GenerateImage(modifier = Modifier)
                            }

                            Spacer(Modifier.width(12.dp))
                            Column(
                                Modifier
                                    .weight(7.5f)
                            ) {
                                AppText(
                                    modifier = Modifier, text = "Bạn đang theo đuổi",
                                    style = ProductXTheme.typography.Regular.Body.Medium, color = Color.Gray
                                )
                                AppText(
                                    modifier = Modifier, text = "Product Designer, Ux research Product Designer, Ux research",
                                    maxLines = 1,
                                    style = ProductXTheme.typography.SemiBold.Body.Large, color = Color.Black
                                )
                            }
                            AppIcons.arrowRightIcon.GenerateImage(modifier = Modifier.weight(1f))
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
                .background(hexToColor("#FDE0A0"))
                .constrainAs(AvataLayout) {
                    top.linkTo(parent.top, margin = 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.img_avata),
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .padding(20.dp),
                contentDescription = null
            )
        }
        AppCard(
            modifier = Modifier
                .size(24.dp)
                .constrainAs(IconEdit) {
                    bottom.linkTo(AvataLayout.bottom)
                    end.linkTo(AvataLayout.end)

                },
            shape = RoundedCornerShape(8.dp)
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_edit),
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .padding(4.dp),
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