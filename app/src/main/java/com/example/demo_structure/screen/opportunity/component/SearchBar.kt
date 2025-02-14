package com.example.demo_structure.screen.opportunity.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppBoxForce
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
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        // Handle back navigation here
                    }
            )

            AppBoxForce(backgroundColor = Color.White) { isFocus, textFieldFocus ->
                SearchBar(isFocus, textFieldFocus)
            }
        }

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
fun SearchBar(isFocus: Boolean, textFieldFocus: (Boolean) -> Unit) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var isTextFieldFocused by remember { mutableStateOf(false) }
    var (getText, setTextState) = remember { mutableStateOf(TextFieldValue("Product Designer")) }
    LaunchedEffect(isFocus) {
        if (isFocus) {
            focusRequester.requestFocus()
        } else {
            focusManager.clearFocus()
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(0.65f)
                .background(Color.White, RoundedCornerShape(8.dp))

        ) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search", modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(4.dp))
            TextField(
                value = getText,
                onValueChange = {
                    setTextState(it)
                },
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        isTextFieldFocused = it.isFocused
                        textFieldFocus(isTextFieldFocused)
                        if (it.isFocused) {
                            setTextState(
                                getText.copy(
                                    selection = TextRange(getText.text.length)
                                )
                            )

                        }
                    },
                colors = TextFieldDefaults.colors(
//                    backgroundColor = Color.Transparent, // Clear TextField's background
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,  // Remove focus indicator
                    unfocusedIndicatorColor = Color.Transparent, //Remove focus indicator
                    disabledIndicatorColor = Color.Transparent //Remove focus indicator
                ),
            )
        }

        // Search Button (Tìm bằng CV)
        Box(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .weight(0.35f)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = ProductXTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp)),
        ) {
            Text(
                "Tìm bằng CV", Modifier
                    .padding(8.dp)
                    .align(Alignment.Center)
            )
        }
    }

}

@Composable
fun SearchFilterSection() {
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