package com.example.demo_structure.screen.opportunity.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_structure.core.component.AppInputChip
import com.example.demo_structure.filterItems
import com.example.domain.model.FilterType
import kotlinx.coroutines.launch

/**
 * Created by Phạm Sơn at 17:31/14/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun FilterSection() {
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(filterItems) {
        filterItems.sortBy { it.isSelect }
        Log.d("QQQ","filterItems: ${filterItems.filter { it.isSelect }}")
    }
    LazyRow(
        state = lazyListState,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        itemsIndexed(filterItems) { index, item ->
            when (item.type) {
                FilterType.Sort -> {
                    AppInputChip(isSelect = item.isSelect, onSelectedChange = {
                        Log.d("QQQ", "${item.type.title} --- Is Select: $it -- index: $index")
                        filterItems[index] = item.copy(isSelect = it)
                    }, label = item.type.title, iconLeft = item.icon)
                }

                else -> {
                    AppInputChip(isSelect = item.isSelect, onSelectedChange = {
                        Log.d("QQQ", "${item.type.title} --- Is Select: $it -- index: $index")
                        filterItems[index] = item.copy(isSelect = it)
                    }, label = item.type.title, iconRight = item.icon)
                }
            }
        }
    }
}

@Composable
@Preview
fun FilterSectionPreview() {
    FilterSection()
}