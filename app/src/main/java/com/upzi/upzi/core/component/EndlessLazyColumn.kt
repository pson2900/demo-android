package com.upzi.upzi.core.component


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier


@Composable
internal fun <T> EndlessLazyColumn(
    modifier: Modifier ,
    totalItems: Int,
    loading: Boolean = false,
    listState: LazyListState = rememberLazyListState(),
    items: List<T>,
    itemKey: (T) -> Any,
    itemContent: @Composable (T) -> Unit,
    loadingItem: @Composable () -> Unit,
    loadMore: () -> Unit
) {

    val reachedBottom: Boolean by remember { derivedStateOf { listState.reachedBottom(totalItems) } }

    // load more if scrolled to bottom
    LaunchedEffect(reachedBottom) {
        if (reachedBottom && !loading) loadMore()
    }


    LazyColumn(modifier = modifier, state = listState) {
        items(
            items = items,
            key = { item: T -> itemKey(item) },
        ) { item ->
            itemContent(item)
        }
        if (loading) {
            item {
                loadingItem()
            }
        }
    }
}

//private fun LazyListState.reachedBottom(): Boolean {
//    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
//    return lastVisibleItem?.index != 0 && lastVisibleItem?.index == this.layoutInfo.totalItemsCount - 1
//}

private fun LazyListState.reachedBottom(totalItems: Int): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
    return lastVisibleItem != null && lastVisibleItem.index == this.layoutInfo.totalItemsCount - 1 && this.layoutInfo.totalItemsCount < totalItems
}