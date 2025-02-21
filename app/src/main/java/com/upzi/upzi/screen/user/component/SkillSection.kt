package com.upzi.upzi.screen.user.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ContextualFlowRow
import androidx.compose.foundation.layout.ContextualFlowRowOverflow
import androidx.compose.foundation.layout.ContextualFlowRowOverflowScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.upzi.upzi.app.manager.theme.ProductXTheme
import com.upzi.upzi.app.manager.theme.hexToColor
import com.upzi.upzi.core.component.AppPreviewWrapper
import com.upzi.upzi.core.component.AppText
import com.upzi.upzi.util.extension.truncate

/**
 * Created by Phạm Sơn at 09:30/16/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun SkillSection(skills: List<String>) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        AppText(text = "Kỹ năng", style = ProductXTheme.typography.SemiBold.Title.Large, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        DynamicSkillFlowRow(skills = skills)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DynamicSkillFlowRow(skills: List<String>) {
    val totalCount = skills.size
    val moreOrCollapseIndicator = @Composable { scope: ContextualFlowRowOverflowScope ->
        val remainingItems = totalCount - scope.shownItemCount
        SkillChip(if (remainingItems == 0) "Less" else "+$remainingItems")
    }

    ContextualFlowRow(
        modifier = Modifier
            .fillMaxWidth(),
        overflow = ContextualFlowRowOverflow.expandOrCollapseIndicator(
            minRowsToShowCollapse = 4,
            expandIndicator = moreOrCollapseIndicator,
            collapseIndicator = moreOrCollapseIndicator
        ),
        maxLines = 1,
        horizontalArrangement = Arrangement.Start,
        itemCount = totalCount,
        verticalArrangement = Arrangement.Top,
    ) {
        SkillChip(skills[it])
    }
}

@Composable
fun SkillChip(skill: String) {
    if (skill.isEmpty()) return
    Box(
        modifier = Modifier.padding(end = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        AppText(
            Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White, RoundedCornerShape(16.dp))
                .border(1.dp, hexToColor("#E2E8F0"), RoundedCornerShape(16.dp))
                .padding(end = 8.dp, start = 8.dp, top = 4.dp, bottom = 4.dp)
                .clickable {

                },
            maxLines = 1, overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            text = skill.truncate(), color = Color.Black,
            style = ProductXTheme.typography.Medium.Label.Large
        )
    }

    /* Button(
         onClick = {},
         modifier = Modifier
             .padding(end = 3.dp, start = 3.dp)
 //            .clip(RoundedCornerShape(20.dp))
 //            .background(Color.White, RoundedCornerShape(20.dp))
 //            .border(1.dp, hexToColor("#E2E8F0"), RoundedCornerShape(20.dp)),
                 ,
         colors = ButtonDefaults.buttonColors(
             containerColor = Color.White,
             contentColor = Color.LightGray
         ),
         contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
         elevation = ButtonDefaults.buttonElevation(0.dp)
     ) {
         AppText(
             maxLines = 1, overflow = TextOverflow.Ellipsis,
             text = skill.truncate(), color = Color.Black,
             style = ProductXTheme.typography.Regular.Label.Large
         )
     }*/
}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SkillSectionPreview() {
    AppPreviewWrapper {
        SkillSection(
            skills = listOf(
                "IT", "Highly skilled Software Engineer with 5 years of experience", "Skill 3",
                "Skill 4", "Skill 5", "Skill 6",
                "Skill 7", "Skill 8", "Skill 9"
            )
        )
    }
}