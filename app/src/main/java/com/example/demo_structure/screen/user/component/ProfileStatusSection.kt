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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.AppIcons
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppBox
import com.example.demo_structure.core.component.AppButton
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppSurface
import com.example.demo_structure.core.component.AppText

/**
 * Created by Phạm Sơn at 13:20/14/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun ProfileStatusSection(modifier: Modifier = Modifier, onClick: () -> Unit) {
    AppSurface(
        modifier = modifier.padding(start = 16.dp, end = 16.dp),
        border = BorderStroke(2.dp, colorResource(R.color.pale_violet)),
        shape = RoundedCornerShape(10.dp),

    ) {
        Column {
            ProfileProgressContent( 5, 30)
            ProfileProgressAction( onClick)
        }
    }
}

@Composable
fun ProfileProgressContent(progress: Int, max: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.violets_are_blue))
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            AppText(
                text = "Hoàn thiện hồ sơ - thu hút nhà tuyển dụng!",
                style = ProductXTheme.typography.SemiBoldTitleMedium,
                color = Color.White
            )
            Spacer(Modifier.height(8.dp))
            ProfileProgressBar(progress, max)
        }
    }

}

@Composable
fun ProfileProgressAction(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(colorResource(R.color.white)),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Row {
                Image(modifier = Modifier.weight(2f), painter = painterResource(AppIcons.shoppingForSportsEquipmentIcon), contentDescription = "IconShoppingForSportsEquipment")
                Column(
                    Modifier
                        .weight(8f)
                        .padding(5.dp)
                ) {
                    AppText(
                        text = "+1 điểm hoàn thiện hồ sơ",
                        style = ProductXTheme.typography.SemiBoldTitleSmall,
                        color = colorResource(R.color.persian_green)
                    )
                    AppText(
                        text = "Bạn đã tham gia những hoạt động ngoại khoá nào?",
                        style = ProductXTheme.typography.SemiBoldTitleMedium,
                        color = colorResource(R.color.black)
                    )
                }
            }
            AppButton(modifier = Modifier.fillMaxWidth(),
                background = colorResource(R.color.alice_blue),
                colorEffect = colorResource(R.color.violets_are_blue),
                onClick = onClick) {
                Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        imageVector = ImageVector.vectorResource(AppIcons.addIcon),
                        modifier = Modifier.padding(3.dp),
                        colorFilter = ColorFilter.tint(colorResource(R.color.violets_are_blue), BlendMode.SrcIn), contentDescription = "IconAdd"
                    )
                    AppText(
                        text = "Thêm ngay",
                        style = ProductXTheme.typography.SemiBoldTitleMedium,
                        color = colorResource(R.color.violets_are_blue)
                    )
                }
            }
        }
    }

}

@Composable
fun ProfileProgressBar(progress: Int, max: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp)
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
    ) {
        AppBox(
            modifier = Modifier
                .padding(4.dp)
                .background(
                    color = colorResource(R.color.persian_green), shape = RoundedCornerShape(10.dp)
                )
                .fillMaxWidth(progress.toFloat() / max)
                .fillMaxHeight()
        ) {

        }
        AppText(
            text = "$progress/$max",
            style = ProductXTheme.typography.RegularLabelSmall, modifier = Modifier.align(Alignment.Center), color = Color.Black
        )
    }
}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun StatusSectionPreview() {
    AppPreviewWrapper {
        ProfileStatusSection(Modifier.wrapContentHeight()) {

        }
    }
}