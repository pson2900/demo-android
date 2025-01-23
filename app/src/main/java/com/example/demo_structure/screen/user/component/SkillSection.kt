package com.example.demo_structure.screen.user.component

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ContextualFlowRow
import androidx.compose.foundation.layout.ContextualFlowRowOverflow
import androidx.compose.foundation.layout.ContextualFlowRowOverflowScope
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.demo_structure.core.component.AppPreviewWrapper

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
            text = "Kỹ năng", style = MaterialTheme.typography.titleLarge, color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        DynamicSkillFlowRow(skills = skills)

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillList(skills: List<String>) {
    var showOverflow by remember { mutableStateOf(false) }
    var parentSize by remember { mutableStateOf(IntSize(0, 0)) }
    val layoutWidthPx = with(LocalDensity.current) {
        parentSize.width.toDp()
    }
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .onPlaced { coordinates ->
                parentSize = coordinates.size
                showOverflow = false
                Log.d("QQQ", "showOverflow: $showOverflow")
                Log.d("QQQ", "parentSize: $parentSize")
            },
        horizontalArrangement = Arrangement.Start,
        verticalArrangement = Arrangement.Top,
        maxLines = 1,
    ) {
        Log.d("QQQ", "enter content")
        Log.d("QQQ", "------------")
        var itemsWidth = 0.dp
        var lastSkillIndex = -1

        skills.forEachIndexed { index, skill ->
            val chipWidth = with(LocalDensity.current) {
                measureSkillChip(skill).toDp()
            }
            if (itemsWidth + chipWidth <= layoutWidthPx) {
                itemsWidth += chipWidth
                lastSkillIndex = index
                SkillChip(skill)
                Log.d("QQQ", "itemsWidth: ${itemsWidth + chipWidth}")
                Log.d("QQQ", "layoutWidthPx: ${layoutWidthPx}")
            } else {
                if (!showOverflow) {
                    showOverflow = true
                    if (lastSkillIndex != -1) {
                        SkillChip("+${skills.size - lastSkillIndex}")
                        return@forEachIndexed
                    } else {
                        SkillChip("+${skills.size}")
                        return@forEachIndexed
                    }
                }
            }
        }
        if (showOverflow && lastSkillIndex == -1) {
            SkillChip("+${skills.size}")
        }

        /*  skills.forEachIndexed { index, skill ->

              if (showOverflow) {
                  SkillChip("+${skills.size - index}")
              } else {
                  SkillChip(skill)
              }

          }*/

        /*  if (skills.size > 3) {
              skills.take(2).forEach { skill ->
                  SkillChip(skill = skill)
              }
              SkillChip("+${skills.size - 2}")
          } else {
              skills.take(3).forEach { skill ->
                  SkillChip(skill = skill)
              }
          }*/

        /*
                var itemsWidth = 0.dp;
                skills.forEachIndexed{ index,skill ->
                    val chipWidth =  with(LocalDensity.current){
                        measureSkillChip(skill).toDp()
                    }
                    if (itemsWidth + chipWidth <= layoutWidthPx) {
                        itemsWidth += chipWidth
                        SkillChip(skill = skill)
                    }
                    else{
                        if(!showOverflow){
                            showOverflow = true
                            SkillChip("+${skills.size - index}")
                        }
                    }
                }*/
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
fun measureSkillChip(skill: String): Int {
    var textWidth = 0;
    androidx.compose.ui.layout.Layout(content = {
        SkillChip(skill)
    }) { measurables, constraints ->
        val textMeasurable = measurables.first()
        val placeable = textMeasurable.measure(constraints)
        textWidth = placeable.width
        layout(
            placeable.width,
            placeable.height
        ) {
            placeable.place(x = 0, y = 0)
        }
    }
    return textWidth
}

@Composable
fun SkillChip(skill: String) {
    if (skill.isEmpty()) return
    Button(
        onClick = {}, modifier = Modifier.padding(end = 4.dp, bottom = 4.dp), shape = RoundedCornerShape(20.dp), colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.LightGray,

            ), contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp), elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {
        Text(text = skill, color = Color.Black, style = MaterialTheme.typography.labelSmall)
    }
}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SkillSectionPreview() {
    AppPreviewWrapper {
        SkillSection(
            skills = listOf("Skill 1", "Skill 2", "Skill 3", "Skill 4", "Skill 5", "Skill 6", "Skill 7", "Skill 8", "Skill 9")
        )
        /* DynamicSkillFlowRow(
             skills = listOf("Skill 1", "Skill 2", "Skill 3", "Skill 4", "Skill 5", "Skill 6", "Skill 7", "Skill 8", "Skill 9")
 //            skills = listOf("Skill 1", "Skill 2", "Skill 3", "Skill 4", "Skill 5", "Skill 8", "Skill 9")
         )*/

    }
}