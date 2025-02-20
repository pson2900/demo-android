package com.example.demo_structure.core.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_structure.R
import com.example.domain.model.ItemBox

@Preview(showBackground = true)
@Composable
fun SelectableListPreview() {
    val items = remember {
        mutableStateListOf(
            ItemBox(1, "a", "a", true),
            ItemBox(2, "b", "a"),
            ItemBox(3, "c", "a"),
            ItemBox(4, "d", "a")
        )
    }
    Column {
        SelectableList(
            modifier = Modifier,
            items = items,
            itemKey = { item: ItemBox -> item.id },
            itemContent = { item ->
                ItemView(itemBox = item, onSelected = { isChecked ->
                    //reset all
                    items.forEach { it.isChecked = false }
                    // set checked
                    val index = items.indexOf(item)
                    if (index != -1) {
                        items[index] = item.copy(isChecked = isChecked)
                    }
                })
            })
    }
}

@Preview(showBackground = true)
@Composable
fun ItemViewPreview() {
    ItemView(itemBox = ItemBox(1, "demo", "hihihihi"),
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
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = items,
            key = { item: T -> itemKey(item) },
        ) { item ->
            itemContent(item)
        }
    }
}