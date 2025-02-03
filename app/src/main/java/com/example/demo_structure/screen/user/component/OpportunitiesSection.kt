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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.IconImage
import com.example.demo_structure.core.component.AppPreviewWrapper

/**
 * Created by Phạm Sơn at 13:20/14/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun OpportunitiesSection(modifier: Modifier = Modifier) {
    Column(modifier.padding(10.dp, 0.dp, 10.dp, 0.dp)) {
        Text(text = "Cơ hội", color = colorResource(R.color.black), style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        Row(modifier) {
            OpportunitiesItem(
                modifier = modifier
                    .background(colorResource(R.color.white), shape = RoundedCornerShape(5.dp))
                    .fillMaxWidth()
                    .clickable {  }
                    .testTag("Opportunity_MatchJob")
                    .height(150.dp)
                    .weight(1f),
                icon = R.drawable.ic_my_profile_opprotunities_crow,
                colorBorder = R.color.cosmic_latte,
                title = "Việc phù hợp\nvới bạn",
                subtitle = "32"
            )
            Spacer(Modifier.size(12.dp))
            OpportunitiesItem(
                modifier = modifier
                    .background(colorResource(R.color.white), shape = RoundedCornerShape(5.dp))
                    .fillMaxWidth()
                    .testTag("Opportunity_SaveJob")
                    .height(150.dp)
                    .clickable {  }
                    .weight(1f),
                icon = R.drawable.ic_my_profile_opprotunities_heart,
                colorBorder = R.color.lavender_blush,
                title = "Việc\nđã lưu",
                subtitle = "0"
            )
            Spacer(Modifier.size(12.dp))
            OpportunitiesItem(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable {  }
                    .testTag("Opportunity_ApplyJob")
                    .background(colorResource(R.color.white), shape = RoundedCornerShape(5.dp))
                    .height(150.dp)
                    .weight(1f),
                icon = R.drawable.ic_my_profile_opprotunities_bag,
                colorBorder = R.color.nyanza,
                title = "Việc\nđã nộp",
                subtitle = "2"
            )
        }
    }
}

@Composable
fun OpportunitiesItem(modifier: Modifier = Modifier, icon: Int, colorBorder: Int, title: String, subtitle: String) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        ConstraintLayout(Modifier.fillMaxSize()) {
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
                IconImage(imageResource = icon, contentDescription = null)
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues = PaddingValues(8.dp))
                    .constrainAs(titleRef) {
                        top.linkTo(iconRef.bottom)
                        start.linkTo(parent.start)
                        bottom.linkTo(subtitleRef.top)
                    }, text = title, color = colorResource(R.color.slate_gray), style = MaterialTheme.typography.labelLarge
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues = PaddingValues(8.dp))
                    .constrainAs(subtitleRef) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }, text = subtitle, color = colorResource(R.color.black), style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}


@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun OpportunitiesSectionPreview() {
    AppPreviewWrapper {
        OpportunitiesSection(Modifier)
    }
}