package com.upzi.upzi.screen.opportunity.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.upzi.upzi.app.manager.theme.AppIcons
import com.upzi.upzi.app.manager.theme.GenerateImage

/**
 * Created by Phạm Sơn at 17:36/14/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun RecentSearchSection(list: List<String>) {
    LazyColumn(contentPadding = PaddingValues(start = 16.dp, end = 16.dp)) {
        itemsIndexed(list) { index, item ->
            SuggestionItemSection(item, index != list.lastIndex)
        }
    }
}

@Composable
internal fun SuggestionItemSection(string: String, isNotLast: Boolean) {
    Column(
        Modifier.clickable { }
    ) {
        Row(
            Modifier.padding(top = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIcons.searchIcon.GenerateImage(Modifier.size(24.dp))
            Box(Modifier.width(8.dp))
            Text(string, modifier = Modifier.weight(1f))
        }

        if (isNotLast) {
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color.Black)
            )
        }
    }
}


@Composable
@Preview
fun SuggestionSectionPreview() {
    RecentSearchSection(
        list = listOf(
            "Product Designer", "Product Designer 1", "Product Designer 2",
            "Product Designer 3", "Product Designer 4", "Product Designer 5", "Product Designer 6"
        )
    )
}