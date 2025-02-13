package com.example.demo_structure.screen.opportunity.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_structure.core.component.AppChip

/**
 * Created by Phạm Sơn at 09:57/13/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun SearchBarSection(onTextChange: (String) -> Unit, onSearch: () -> Unit, isFilter: Boolean = false) {
    val visibilityFilter by remember { mutableStateOf(isFilter) }
    Column(
        modifier = Modifier
            .background(Color.White) // Assuming a white background
            .padding(horizontal = 16.dp, vertical = 8.dp) // Add some padding
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Back Button
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.clickable {
                    // Handle back navigation here
                }
            )

            // Search Bar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f) // Take up remaining space
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(8.dp)

            ) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                Text(text = "Product Designer co...")
            }

            // Search Button (Tìm bằng CV)
            Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(8.dp)) {
                Text("Tìm bằng CV")
            }
        }

        // Filters (Remote, IT, Không cần kinh nghiệm)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            AppChip(selected = true, onSelectedChange = {}) {
                Text("IT")
            }
            AppChip(selected = true, onSelectedChange = {}) {
                Text("Khong can exp")
            }
            AppChip(selected = true, onSelectedChange = {}) {
                Text("Chip")
            }
        }
    }
}

@Composable
fun SearchFilterSection(){
    rememberLazyListState()
    LazyRow {

    }
}

@Composable
@Preview
fun SearchBarSectionPreview() {
    SearchBarSection(
        onTextChange = {},
        onSearch = {}

    )
}