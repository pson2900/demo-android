package com.example.demo_structure.screen.onboarding.component.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppText


@Preview(showBackground = true)
@Composable
private fun ItemCardPreview() {
    AppPreviewWrapper { modifier ->
        ItemGroupCard("item 1", {})
    }
}

@Composable
fun ItemGroupCard(title: String, onAddClick: (String) -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, colorResource(R.color.mischka)),
        modifier = Modifier
            .width(160.dp)
            .height(80.dp),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppText(
                text = title,
                color = colorResource(R.color.woodsmoke),
                style = ProductXTheme.typography.Regular.Title.Medium,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = { onAddClick(title) }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_add),
                    contentDescription = "Add",
                    tint = colorResource(R.color.boulder)
                )
            }
        }
    }
}
