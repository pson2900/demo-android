package com.upzi.upzi.core.component

import android.icu.util.Calendar
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.upzi.upzi.R
import com.upzi.domain.model.ItemBox

@Preview(showBackground = true)
@Composable
fun SelectableListPreview() {
    var text by remember { mutableStateOf("") }

    val items = remember {
        mutableStateListOf(
            ItemBox(1, "a", "a", true),
            ItemBox(2, "b", "a",false),
            ItemBox(3, "c", "a",false),
            ItemBox(4, "d", "a",false
            )
        )
    }
    val listState: LazyListState = rememberLazyListState()
    val filteredItems by remember(text) {
        derivedStateOf {
            if (text.isBlank()) {
                items.toList() // Create a copy to avoid direct modification
            } else {
                items.filter { item ->
                    item.name.contains(text, ignoreCase = true)
                }
            }
        }
    }

    Column {
        SelectableList(
            modifier = Modifier,
            listState = listState,
            items = filteredItems,
            itemKey = { item: ItemBox -> item.id },
            itemContent = { item ->
                ItemView(itemBox = item,
                    onSelected = {
                        val index = items.indexOfFirst { it.id == item.id }
                        if (index != -1) {
                            val newItem = items[index].copy(isChecked = !items[index].isChecked)
                            if (newItem.isChecked) {
                                newItem.checkedAt = Calendar.getInstance().timeInMillis
                            } else {
                                newItem.checkedAt = null
                            }
                            items[index] = newItem
                            //
                            val sortedList = items.sortedWith(
                                compareByDescending<ItemBox> { it.isChecked } // Ưu tiên isChecked = true
                                    .thenBy { it.checkedAt ?: Long.MAX_VALUE }
                                    .thenBy { it.name } // Nếu isChecked giống nhau, sắp xếp theo name A → Z
                            )

                            items.clear()
                            items.addAll(sortedList)
                        }
                    })
            })
    }
}

@Preview(showBackground = true)
@Composable
fun ItemViewPreview() {
    ItemView(itemBox = ItemBox(1, "demo", "hihihihi",true),
        onSelected = {

        })
}

@Composable
fun ItemView(
    modifier: Modifier = Modifier,
    itemBox: ItemBox,
    onSelected: (Boolean) -> Unit
) {
    var color = when (itemBox.isChecked) {
        true -> colorResource(R.color.violets_are_blue)
        else -> colorResource(R.color.boulder)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(1.dp, color, RoundedCornerShape(12.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var isChecked by remember { mutableStateOf(itemBox.isChecked ?: true) }
        Image(
            painter = painterResource(id = R.drawable.ic_attachment),
            contentDescription = "Image",
            modifier = Modifier
                .padding(start = 16.dp)
                .width(24.dp)
                .height(24.dp)
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = itemBox.name
        )

        RadioButton(
            selected = isChecked,
            onClick = {
                isChecked = !isChecked
                onSelected(isChecked)
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun <T> SelectableList(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    items: List<T>,
    itemKey: (T) -> Any,
    itemContent: @Composable (T) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        state = listState,
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = items,
            key = { item: T -> itemKey(item) },
        ) { item ->
            val animationProgress by animateFloatAsState(
                targetValue = 1f, // Từ 0 -> 1 (bắt đầu từ dưới lên)
                animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing),
                label = "itemInUp"
            )

            Box(
                modifier = Modifier
                    .animateItemPlacement(animationSpec = tween(durationMillis = 800))
                    .graphicsLayer {
                        translationY = (1f - animationProgress) * 50f // Trượt lên từ dưới
                        alpha = animationProgress // Fade-in dần
                    }
            ) {
                itemContent(item)
            }
        }
    }
}