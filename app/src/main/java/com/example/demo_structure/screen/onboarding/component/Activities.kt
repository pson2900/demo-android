package com.example.demo_structure.screen.onboarding.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppText
import com.example.demo_structure.core.component.BaseBottomSheet
import com.example.demo_structure.screen.onboarding.component.bottomSheet.ExtracurricularActivities
import com.example.demo_structure.screen.onboarding.component.item.ItemActivity
import com.example.demo_structure.screen.onboarding.component.item.ItemGroupCard
import com.example.domain.model.ActivitiesItem

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ActivitiesPreview() {
    AppPreviewWrapper { modifier ->
        Activities(modifier = modifier.padding(16.dp))
    }
}

@Composable
fun Activities(modifier: Modifier = Modifier) {
    var showBottomSheet by remember { mutableStateOf(false) }

    Box(modifier = modifier){
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
            item{
                QuestionHeader(question = "Bạn làm công việc gì vậy?")
            }
            item {
                OutlinedButton(
                    onClick = {
                        showBottomSheet = true
                    },
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(1.dp, colorResource(R.color.mischka)),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = colorResource(R.color.royal_blue)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_add),
                        contentDescription = "Add",
                        tint = colorResource(R.color.royal_blue)
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    AppText(
                        text = "Thêm hoạt động",
                        color = colorResource(R.color.royal_blue),
                        style = ProductXTheme.typography.Regular.Body.Large,
                    )
                }
            }
            item {
                AppText(
                    modifier = Modifier.padding(top = 12.dp),
                    text = "Các hoạt động thường được tham gia",
                    color = colorResource(R.color.text_jumbo),
                    style = ProductXTheme.typography.Regular.Body.Large
                )
            }
            item {
                val activities =
                    listOf("Tiếp Sức Mùa Thi", "Olympic Toán - Lý - Hóa", "CLB Truyền Thông")
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(activities) { activity ->
                        ItemGroupCard(activity, onAddClick = {
                            println("Add clicked for: $activity")
                        })
                    }
                }
            }


            val data = listOf(
                ActivitiesItem(
                    "Tiếp Sức Mùa Thi Olympic Toán - Lý - Tin Tiếp Sức Mùa Thi",
                    "Trưởng / Phó ban",
                    listOf(
                        "Giao tiếp",
                        "Quản lý thời gian",
                        "Đám phán",
                        "Lập kế hoạch",
                        "Lãnh đạo nhóm",
                        "Tổ chức sự kiện",
                        "Thuyết trình",
                        "Ngoại giao"
                    )
                ),
                ActivitiesItem(
                    "Tiếp Sức Mùa Thi Olympic Toán - Lý - Tin Tiếp Sức Mùa Thi",
                    "Trưởng / Phó ban",
                    listOf("Giao tiếp", "Quản lý thời gian", "Đám phán", "Lập kế hoạch")
                ),
                ActivitiesItem(
                    "Tiếp Sức Mùa Thi Olympic Toán - Lý - Tin Tiếp Sức Mùa Thi",
                    "Trưởng / Phó ban",
                    listOf("Giao tiếp", "Quản lý thời gian", "Đám phán", "Lập kế hoạch")
                )
            )

            items(data) { item ->
                ItemActivity(item)
            }
        }


    }
    BaseBottomSheet(
        openBottomSheet = showBottomSheet,
        sheetContent = {
        ExtracurricularActivities(modifier = Modifier.padding(16.dp), {
            showBottomSheet = false
        })
    }, onDismissRequest = {
        showBottomSheet = false
    }, dragHandle = null)
}


