package com.example.demo_structure.screen.user.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_structure.R
import com.example.demo_structure.core.component.ProductXPreviewWrapper
import com.example.demo_structure.theme.AppIcons.IconAdd
import com.example.demo_structure.theme.AppIcons.IconShoppingForSportsEquipment
import com.example.demo_structure.theme.IconImage

/**
 * Created by Phạm Sơn at 13:20/14/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun ProfileStatusSection(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.padding(10.dp, 0.dp, 10.dp, 0.dp),
        border = BorderStroke(2.dp, colorResource(R.color.pale_violet)),
        shape = RoundedCornerShape(10.dp),
        /*.background(Color.White)
        .border(
            border = BorderStroke(2.dp, colorResource(R.color.pale_violet)),
            shape = RoundedCornerShape(10.dp)
        ),*/
    ) {
        Column(modifier = Modifier) {
            ProfileProgressContent(Modifier, 5, 30)
            ProfileProgressAction(Modifier)
        }
    }
}

@Composable
fun ProfileProgressContent(modifier: Modifier = Modifier, progress: Int, max: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.violets_are_blue))
    ) {
        Column(
            modifier = modifier.padding(10.dp)
        ) {
            Text("Hoàn thiện hồ sơ - thu hút nhà tuyển dụng!")
            Text("Điểm hoàn thiện hồ sơ: Thấp")
            ProfileProgressBar(progress, max)
        }
    }

}

@Composable
fun ProfileProgressAction(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(colorResource(R.color.white)),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Row {
                Image(painter = painterResource(IconShoppingForSportsEquipment), contentDescription = "IconShoppingForSportsEquipment")
                Column(Modifier.padding(5.dp)) {
                    Text(text = "+1 điểm hoàn thiện hồ sơ")
                    Text(text = "Bạn đã tham gia những hoạt động ngoại khoá nào?")
                }
            }
            Button(modifier = Modifier.fillMaxWidth(), onClick = { }) {
                Row() {
                    IconImage(imageResource = IconAdd, contentDescription = "IconAdd")
                    Text(text = "Thêm ngay")
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
            .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp))
    ) {
        Box(
            modifier = Modifier
                .padding(4.dp)
                .background(
                    color = colorResource(R.color.persian_green), shape = RoundedCornerShape(10.dp)
                )
                .fillMaxWidth(progress.toFloat() / max)
                .fillMaxHeight()
        )
        Text(text = "$progress/$max", modifier = Modifier.align(Alignment.Center), color = Color.Black)
    }
}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun StatusSectionPreview() {
    ProductXPreviewWrapper {
        ProfileStatusSection(Modifier.wrapContentHeight())
    }
}