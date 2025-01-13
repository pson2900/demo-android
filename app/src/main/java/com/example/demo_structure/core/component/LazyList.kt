package com.example.demo_structure.core.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier

/**
 * Created by Phạm Sơn at 15:01/4/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun <T> LazyList(
    modifier: Modifier = Modifier,
    items: List<T>,
    itemContent: @Composable (item: T) -> Unit,
    onScroll: (Int) -> Unit = {},
    lazyListState: LazyListState = rememberLazyListState(),
) {
    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex }
            .collect { index ->
                onScroll(index)
            }
    }

    /* LazyColumn(
         state = lazyListState,
         modifier = modifier
     ) {
         itemsIndexed(items) { index, item ->
             itemContent(item)
         }
     }
 */
    LazyColumn(
        state = lazyListState,
        modifier = modifier
    ) {
        /*itemsIndexed(items = items, lazyListState = { index, item ->
            itemContent(item)
        }, contentType = {

        })*/
    }
}