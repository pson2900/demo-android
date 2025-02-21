package com.example.demo_structure.screen.onboarding.component.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ContextualFlowRow
import androidx.compose.foundation.layout.ContextualFlowRowOverflow
import androidx.compose.foundation.layout.ContextualFlowRowOverflowScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppText
import com.example.domain.model.ActivitiesItem

@Preview(showBackground = true)
@Composable
private fun ActivityGroupItemPreview() {
    AppPreviewWrapper { modifier ->
        ItemActivity(
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
            )
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ItemActivity(item: ActivitiesItem) {
    var expanded by remember { mutableStateOf(false) } // Quản lý trạng thái dropdown
    Card(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, colorResource(R.color.mischka)),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 30.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_certification), // Thay bằng hình của bạn
                            contentDescription = "Icon",
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .size(size = 20.dp)
                        )

                        AppText(
                            text = item.title,
                            color = colorResource(R.color.woodsmoke),
                            style = ProductXTheme.typography.Regular.Title.Medium,
                            maxLines = 2
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_certification), // Thay bằng hình của bạn
                            contentDescription = "Icon",
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .size(size = 20.dp)
                        )
                        AppText(
                            text = item.levelName,
                            color = colorResource(R.color.woodsmoke),
                            style = ProductXTheme.typography.Regular.Title.Medium,
                        )
                    }
                }
// Box chứa IconButton để định vị chính xác DropdownMenu
                Box(
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    IconButton(modifier = Modifier
                        .padding(bottom = 8.dp, start = 8.dp)
                        .size(24.dp)
                        .align(Alignment.TopEnd), onClick = { expanded = true }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_more),
                            contentDescription = "More",
                            tint = colorResource(R.color.santas_gray)
                        )
                    }

                    // DropdownMenu xuất hiện ngay dưới IconButton
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        offset = DpOffset(0.dp, 8.dp) // Đẩy xuống một chút
                    ) {
                        DropdownMenuItem(
                            text = { Text("Chỉnh sửa") },
                            onClick = { /* Xử lý chỉnh sửa */ expanded = false }
                        )
                        DropdownMenuItem(
                            text = { Text("Xóa") },
                            onClick = { /* Xử lý xóa */ expanded = false }
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(14.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_certification), // Thay bằng hình của bạn
                    contentDescription = "Icon",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(size = 20.dp)
                )
                DynamicFlowRow(listTag = item.listTag)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DynamicFlowRow(listTag: List<String>) {
    val totalCount = listTag.size
    val moreOrCollapseIndicator = @Composable { scope: ContextualFlowRowOverflowScope ->
        val remainingItems = totalCount - scope.shownItemCount
        TagItem(if (remainingItems == 0) "Less" else "+$remainingItems")
    }

    ContextualFlowRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        overflow = ContextualFlowRowOverflow.expandOrCollapseIndicator(
            minRowsToShowCollapse = 4,
            expandIndicator = moreOrCollapseIndicator,
            collapseIndicator = moreOrCollapseIndicator
        ),
        maxLines = 2,
        itemCount = totalCount,
    ) {
        TagItem(listTag[it])
    }
}

@Composable
private fun TagItem(text: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(colorResource(R.color.titan_white), RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = text, fontSize = 12.sp, color = colorResource(R.color.electric_violet))
    }
}