package com.example.demo_structure.screen.onboarding.component.bottomSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppText
import com.example.demo_structure.core.component.ComboBoxDropdown
import com.example.demo_structure.screen.onboarding.component.item.ItemRole
import com.example.domain.model.Role

@Preview(showBackground = true)
@Composable
private fun ActivitiesPreview() {
    AppPreviewWrapper { modifier ->
        ExtracurricularActivities(modifier = modifier.padding(16.dp), {})
    }
}


@Composable
fun ExtracurricularActivities(modifier: Modifier = Modifier, onClose: () -> Unit) {
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = colorResource(id = R.color.royal_blue),
        contentColor = Color.White,
        disabledContainerColor = colorResource(id = R.color.jumbo),
        disabledContentColor = colorResource(id = R.color.tuna),
    )
    var isEnableButton by remember { mutableStateOf(false) }
    val color = if (isEnableButton) colorResource(R.color.white) else colorResource(R.color.tuna)

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_cancel),
                contentDescription = "ic_cancel",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterStart)
                    .clickable {
                        onClose.invoke()
                    }
            )
            AppText(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.Center),
                text = "Hoạt động ngoại khoá",
                color = colorResource(R.color.text_jumbo),
                style = ProductXTheme.typography.Regular.Body.Large
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(1.dp)
                .background(color = colorResource(R.color.wild_sand))
        )
        LazyColumn(
            modifier = modifier
                .weight(1f) // Take up all available space above the button
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text(
                    text = "Tên hoạt động",
                    color = colorResource(R.color.cod_gray),
                    style = ProductXTheme.typography.Regular.Body.Large
                )
            }
            item {
                ComboBoxDropdown(modifier = Modifier.padding(top = 8.dp))
            }

            item {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "Vai trò",
                    color = colorResource(R.color.cod_gray),
                    style = ProductXTheme.typography.Regular.Body.Large
                )
            }
            val data = listOf(
                Role(1, "Trưởng nhóm", "", true),
                Role(1, "Phó nhóm", "", false),
                Role(1, "Báo đời", "", false),
                Role(1, "Phá hoại", "", false),
                Role(1, "ăn hại", "", false)
            )
            item {
                LazyRow(modifier = Modifier.padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(data) { item ->
                        ItemRole(item)
                    }
                }
            }
        }

        // Button at the bottom
        Button(
            onClick = {
                // Handle button click
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .height(56.dp)
                .testTag("ButtonNext"),
            shape = RoundedCornerShape(8.dp),
            colors = buttonColors,
            enabled = isEnableButton,
        ) {
            Text(
                text = "Tiếp tục",
                style = ProductXTheme.typography.SemiBold.Title.Medium,
                color = Color.White
            )
        }
    }
}