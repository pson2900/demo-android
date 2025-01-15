package com.example.demo_structure.screen.user.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_structure.core.component.ProductXPreviewWrapper
import com.example.demo_structure.theme.AppIcons.IconAdvancement
import com.example.demo_structure.theme.AppIcons.IconArrowRight
import com.example.demo_structure.theme.IconImage

/**
 * Created by Phạm Sơn at 13:20/14/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun HeaderSection(modifier: Modifier = Modifier, title: String) {
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        content = {
            Text(modifier = Modifier, text = title, style = MaterialTheme.typography.headlineMedium, color = Color.Black)

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 24.dp, 0.dp, 0.dp),
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
                            .padding(8.dp)) {
                        Text(modifier = Modifier, text = "Bạn đang theo đuổi", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
                        Text(modifier = Modifier, text = "Product Designer, Ux research", style = MaterialTheme.typography.labelLarge, color = Color.Black)
                    }
                    IconImage(InternalRowModifier.weight(1.5f), imageResource = IconArrowRight, contentDescription = "IconArrowRight")
                }
            }
        })

}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HeaderSectionPreview() {
    ProductXPreviewWrapper {
        HeaderSection(
            Modifier
                .padding(12.dp, 12.dp, 12.dp, 12.dp), "Hiếu Minh Nguyễn"
        )
    }
}