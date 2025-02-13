package com.example.demo_structure.screen.opportunity.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

/**
 * Created by Phạm Sơn at 09:57/13/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun SearchBarSection(onTextChange: (String) -> Unit, onSearch: () -> Unit, isFilter: Boolean = false) {
    val visibilityFilter by remember { mutableStateOf(isFilter) }

    Box(modifier = Modifier.background(Color.White)){
        Row {

        }
        AnimatedVisibility(visibilityFilter) {
            SearchFilterSection()
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

}