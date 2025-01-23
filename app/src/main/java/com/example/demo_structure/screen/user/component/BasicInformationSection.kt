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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.AppIcons
import com.example.demo_structure.app.manager.theme.IconImage
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.domain.model.BasicInformation
import com.example.domain.model.Profile

/**
 * Created by Phạm Sơn at 10:29/16/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun BasicInformationSection(list: List<BasicInformation>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item { Text("Thông tin hồ sơ", color = colorResource(R.color.black), style = MaterialTheme.typography.titleLarge) }
        item { Spacer(Modifier.height(12.dp)) }
        items(list.size) {
            BasicInformationItem(Profile.AttachmentProfile(emptyList())) {

            }
        }
    }
}



@Composable
fun BasicInformationItem(profile: Profile, onActionClick: (Profile) -> Unit) {
    var title = ""
    var icon = 0
    when (profile) {
        is Profile.PreferenceProfile -> {
            icon = AppIcons.attachmentIcon
            title = "Tiêu chí tìm việc"
            onActionClick.invoke(profile)
        }

        is Profile.BasicProfile -> {
            icon = AppIcons.basicInformationIcon
            title = "Thông tin liên hệ"
            onActionClick.invoke(profile)
        }

        is Profile.ExperienceProfile -> {
            icon = AppIcons.experienceIcon
            title = "Tiêu chí tìm việc"
            onActionClick.invoke(profile)
        }

        is Profile.CharacteristicProfile -> {
            icon = AppIcons.attachmentIcon
            title = "Kinh nghiệm làm việc"
            onActionClick.invoke(profile)
        }

        is Profile.EducationProfile -> {
            icon = AppIcons.educationIcon
            title = "Trình độ học vấn"
            onActionClick.invoke(profile)
        }

        is Profile.CertificationProfile -> {
            icon = AppIcons.certificationIcon
            title = "Chứng chỉ"
            onActionClick.invoke(profile)
        }


        is Profile.AttachmentProfile -> {
            icon = AppIcons.cvMarkIcon
            title = "CV đính kèm"
            onActionClick.invoke(profile)
        }

        is Profile.LanguageProfile -> {
            icon = AppIcons.languageIcon
            title = "Ngoại ngữ"
            onActionClick.invoke(profile)
        }

        is Profile.ExternalDocProfile -> {
            icon = AppIcons.linkIcon
            title = "Đường link dự án cá nhân"
            onActionClick.invoke(profile)
        }

        is Profile.ReferenceProfile -> {
            icon = AppIcons.referenceIcon
            title = "Người tham chiếu"
            onActionClick.invoke(profile)
        }

        is Profile.HobbyProfile -> {
            icon = AppIcons.attachmentIcon
            title = "Sở thích / tính cách"
            onActionClick.invoke(profile)
        }

        is Profile.ExtraCurricularProfile -> {
            icon = AppIcons.attachmentIcon
            title = "Hoạt động ngoại khoa"
            onActionClick.invoke(profile)
        }
    }
    Row(
        Modifier
            .padding(10.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .clip(shape = RoundedCornerShape(10.dp))
            .height(72.dp)
            .testTag("basicInformationItem")
            .clickable {

            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .padding(paddingValues = PaddingValues(8.dp))
                .background(colorResource(R.color.cosmic_latte), shape = RoundedCornerShape(5.dp)),
            contentAlignment = Alignment.Center
        ) {
            IconImage(imageResource = icon, contentDescription = null)
        }
        Text(
            text = title,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelLarge,
            maxLines = 1,
            modifier = Modifier
                .padding(start = 5.dp)
                .weight(2.7f)
        )

        Box(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxSize()
                .clickable { }
        ) {
            IconImage(
                imageResource = R.drawable.ic_arrow_right,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
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
    BasicInformationItem(Profile.AttachmentProfile(emptyList())) {

    }

}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BasicInformationSectionPreview() {
    AppPreviewWrapper {
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