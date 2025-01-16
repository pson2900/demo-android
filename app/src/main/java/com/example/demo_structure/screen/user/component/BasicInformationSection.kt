package com.example.demo_structure.screen.user.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_structure.R
import com.example.demo_structure.core.component.ProductXPreviewWrapper
import com.example.demo_structure.theme.IconImage
import com.example.domain.model.BasicInformation

/**
 * Created by Phạm Sơn at 10:29/16/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun BasicInformationSection(list: List<BasicInformation>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item { Text("Thông tin hồ sơ") }
        item { Spacer(Modifier.height(12.dp)) }
        items(list.size) {
            BasicInformationItem(list[it])
        }
    }
}


@Composable
fun BasicInformationItem(basicInformation: BasicInformation) {
    Row(
        Modifier
            .padding(10.dp, 10.dp, 10.dp, 0.dp)
            .shadow(0.dp, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(48.dp)
                .background(colorResource(R.color.cosmic_latte), shape = RoundedCornerShape(5.dp)),
            contentAlignment = Alignment.Center
        ) {
            IconImage(imageResource = basicInformation.icon, contentDescription = null)
        }
        Text(
            text = basicInformation.title, modifier = Modifier
                .padding(5.dp)
                .weight(2f)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .clickable { }
                .padding(16.dp)
        ) {
            IconImage(

                imageResource = R.drawable.ic_arrow_right,
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BasicInformationItemPreview() {
    BasicInformationItem(BasicInformation(R.drawable.ic_my_profile_opprotunities_crow, "Kinh nghiệm làm việc", 0))

}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BasicInformationSectionPreview() {
    ProductXPreviewWrapper {
        BasicInformationSection(
            listOf(
                BasicInformation(R.drawable.ic_my_profile_opprotunities_crow, "Kinh nghiệm làm việc", 0),
                BasicInformation(R.drawable.ic_my_profile_opprotunities_crow, "Kinh nghiệm làm việc", 0),
                BasicInformation(R.drawable.ic_my_profile_opprotunities_crow, "Kinh nghiệm làm việc", 0),
                BasicInformation(R.drawable.ic_my_profile_opprotunities_crow, "Kinh nghiệm làm việc", 0),
                BasicInformation(R.drawable.ic_my_profile_opprotunities_crow, "Kinh nghiệm làm việc", 0)
            )
        )
    }
}