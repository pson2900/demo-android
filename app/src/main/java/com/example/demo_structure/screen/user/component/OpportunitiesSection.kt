package com.example.demo_structure.screen.user.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.AppIcons
import com.example.demo_structure.app.manager.theme.ToImage
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.app.manager.theme.generate
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppText
import com.example.demo_structure.core.component.ThemePreviews

/**
 * Created by Phạm Sơn at 13:20/14/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun OpportunitiesSection(modifier: Modifier = Modifier) {
    Column(modifier
        .background(ProductXTheme.colorScheme.background)
        .padding(start = 16.dp, end = 16.dp)) {
        AppText(
            text = "Cơ hội",
            color = ProductXTheme.colorScheme.onPrimary,
            style = ProductXTheme.typography.SemiBold.Title.Large
        )
        Spacer(Modifier.height(12.dp))
        Row(modifier) {
            OpportunitiesItem(
                modifier = Modifier
                    .background(ProductXTheme.colorScheme.surface, shape = RoundedCornerShape(5.dp))
                    .clip(shape = RoundedCornerShape(5.dp))
                    .fillMaxWidth()
                    .clickable { }
                    .testTag("Opportunity_MatchJob")
                    .height(150.dp)
                    .weight(1f),
                icon = R.drawable.ic_my_profile_opprotunities_crow,
                title = "Việc phù hợp\nvới bạn",
                subtitle = "32"
            )
            Spacer(Modifier.size(12.dp))
            OpportunitiesItem(
                modifier = Modifier
                    .background(ProductXTheme.colorScheme.surface, shape = RoundedCornerShape(5.dp))
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(5.dp))
                    .testTag("Opportunity_SaveJob")
                    .height(150.dp)
                    .clickable { }
                    .weight(1f),
                icon = R.drawable.ic_my_profile_opprotunities_heart,
                title = "Việc\nđã lưu",
                subtitle = "0"
            )
            Spacer(Modifier.size(12.dp))
            OpportunitiesItem(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(5.dp))
                    .clickable { }
                    .testTag("Opportunity_ApplyJob")
                    .background(ProductXTheme.colorScheme.surface, shape = RoundedCornerShape(5.dp))
                    .height(150.dp)
                    .weight(1f),
                icon = R.drawable.ic_my_profile_opprotunities_bag,
                title = "Việc\nđã nộp",
                subtitle = "2"
            )
        }
    }
}

@Composable
fun OpportunitiesItem(modifier: Modifier = Modifier, icon: Int, title: String, subtitle: String) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        ConstraintLayout(modifier.fillMaxSize()) {
            val (iconRef, titleRef, subtitleRef) = createRefs()
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(paddingValues = PaddingValues(8.dp))
                    .constrainAs(iconRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            ) {
                icon.generate()
            }
            AppText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues = PaddingValues(8.dp))
                    .constrainAs(titleRef) {
                        top.linkTo(iconRef.bottom)
                        start.linkTo(parent.start)
                        bottom.linkTo(subtitleRef.top)
                    }, text = title,
                color = colorResource(R.color.slate_gray),
                style = ProductXTheme.typography.Regular.Label.Large
            )

            AppText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues = PaddingValues(8.dp))
                    .constrainAs(subtitleRef) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }, text = subtitle, color = colorResource(R.color.black),
                style = ProductXTheme.typography.SemiBold.Heading.Small
            )
        }
    }
}


@ThemePreviews
@Composable
fun OpportunitiesSectionPreview() {
    AppPreviewWrapper {
        OpportunitiesSection(Modifier.wrapContentSize())
    }
}