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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.app.manager.theme.GenerateImage
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppText
import com.example.domain.model.BasicInformation
import com.example.domain.model.Profile

/**
 * Created by Phạm Sơn at 10:29/16/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
internal fun BasicInformationSection(list: List<BasicInformation>) {
    LazyColumn(modifier = Modifier.wrapContentHeight()) {
        item {
            AppText(
                text = "Thông tin hồ sơ",
                style = ProductXTheme.typography.SemiBold.Title.X_Large
            )
        }
        item { Spacer(Modifier.height(12.dp)) }
        items(list.size) {
            BasicInformationItem(Profile.AttachmentProfile(emptyList())) {

            }
        }
    }
}


@Composable
internal fun BasicInformationItem(profile: Profile, onNavigateToProfile: (Profile) -> Unit) {
    var title = ""
    var icon = 0
    when (profile) {
        is Profile.PreferenceProfile -> {
            icon = AppIcons.attachmentIcon
            title = "Tiêu chí tìm việc"
        }

        is Profile.BasicProfile -> {
            icon = AppIcons.basicInformationIcon
            title = "Thông tin liên hệ"
        }

        is Profile.ExperienceProfile -> {
            icon = AppIcons.experienceIcon
            title = "Tiêu chí tìm việc"
        }

        is Profile.CharacteristicProfile -> {
            icon = AppIcons.attachmentIcon
            title = "Kinh nghiệm làm việc"
        }

        is Profile.EducationProfile -> {
            icon = AppIcons.educationIcon
            title = "Trình độ học vấn"
        }

        is Profile.CertificationProfile -> {
            icon = AppIcons.certificationIcon
            title = "Chứng chỉ"
        }


        is Profile.AttachmentProfile -> {
            icon = AppIcons.cvMarkIcon
            title = "CV đính kèm"
        }

        is Profile.LanguageProfile -> {
            icon = AppIcons.languageIcon
            title = "Ngoại ngữ"
        }

        is Profile.ExternalDocProfile -> {
            icon = AppIcons.linkIcon
            title = "Đường link dự án cá nhân"
        }

        is Profile.ReferenceProfile -> {
            icon = AppIcons.referenceIcon
            title = "Người tham chiếu"
        }

        is Profile.HobbyProfile -> {
            icon = AppIcons.attachmentIcon
            title = "Sở thích / tính cách"
        }

        is Profile.ExtraCurricularProfile -> {
            icon = AppIcons.attachmentIcon
            title = "Hoạt động ngoại khoa"
        }
    }
    Row(
        Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .clip(shape = RoundedCornerShape(16.dp))
            .height(72.dp)
            .testTag("basicInformationItem")
            .clickable {
                onNavigateToProfile.invoke(profile)
            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .padding(paddingValues = PaddingValues(12.dp))
                .background(colorResource(R.color.cosmic_latte), shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            icon.GenerateImage(Modifier.size(48.dp).padding(7.dp))
        }
        AppText(
            text = title,
            textAlign = TextAlign.Start,
            color = Color.Black,
            style = ProductXTheme.typography.Regular.Label.Large,
            maxLines = 1,
            modifier = Modifier
                .padding(start = 5.dp)
                .weight(2.7f)
        )

        Box(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxSize()
                .clip(CircleShape)
                .clickable {
                    onNavigateToProfile.invoke(profile)
                }
        ) {
            AppIcons.arrowRightIcon.GenerateImage(
                modifier = Modifier
                    .padding(end = 12.dp)
//                    .size(16.dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}

@Preview(name = "Mobile Light Mode", group = "Mobile")
@Preview(name = "Mobile Dark Mode", group = "Mobile", uiMode = Configuration.UI_MODE_NIGHT_YES)
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