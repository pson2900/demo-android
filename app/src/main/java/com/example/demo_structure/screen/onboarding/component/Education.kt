package com.example.demo_structure.screen.onboarding.component

import android.content.res.Configuration
import android.icu.util.Calendar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.ItemView
import com.example.demo_structure.core.component.SearchBar
import com.example.demo_structure.core.component.SelectableList
import com.example.demo_structure.util.extension.randomString
import com.example.domain.model.ItemBox


@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun EducationPreview() {
    AppPreviewWrapper { modifier ->
        Education(modifier = modifier.padding(16.dp))
    }
}

@Composable
fun Education(modifier: Modifier = Modifier) {
    val listState: LazyListState = rememberLazyListState()
    var text by remember { mutableStateOf("") }
    val items = remember { mutableStateListOf<ItemBox>() }

    LaunchedEffect(Unit) {
        repeat(1000) { i ->
            val randomText = randomString() // Ví dụ: "aZ3dK"
            items.add(ItemBox(id = i, name = "item $randomText", "Ngành Nghề Trọng Điểm"))
        }
    }

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

    Column(modifier = modifier.fillMaxSize()) {
        QuestionHeader(
            modifier.padding(16.dp),
            question = "Bạn học trường nào? "
        )

        SearchBar(
            modifier = Modifier.padding(bottom = 16.dp),
            query = text,
            placeholder = "Tìm trường",
            onQueryChange = { newText ->
                text = newText
            })

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
