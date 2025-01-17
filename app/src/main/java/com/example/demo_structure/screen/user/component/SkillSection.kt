package com.example.demo_structure.screen.user.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo_structure.core.component.ProductXPreviewWrapper

/**
 * Created by Phạm Sơn at 09:30/16/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun SkillSection(skills: List<String>) {
    Column(
        modifier = Modifier
            .padding(10.dp, 0.dp, 10.dp, 0.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Kỹ năng",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        SkillList(skills = skills)

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillList(skills: List<String>) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalArrangement = Arrangement.Top,
    ) {
        if (skills.size > 3) {
            skills.take(2).forEach { skill ->
                SkillChip(skill = skill)
            }
            SkillChip("+${skills.size - 2}")
        } else {
            skills.take(3).forEach { skill ->
                SkillChip(skill = skill)
            }
        }
    }
}

@Composable
fun SkillChip(skill: String) {
    Button(
        onClick = {},
        modifier = Modifier
            .padding(end = 4.dp, bottom = 4.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.LightGray,

        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
        elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {
        Text(text = skill, color = Color.Black, style = MaterialTheme.typography.labelSmall)
    }
}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SkillSectionPreview() {
    ProductXPreviewWrapper {
        SkillSection(listOf("Design Systems", "Typography", "Typography", "Typography"))
    }
}