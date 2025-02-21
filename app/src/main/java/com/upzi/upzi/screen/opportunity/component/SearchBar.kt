package com.upzi.upzi.screen.opportunity.component

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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.upzi.upzi.app.manager.theme.AppIcons
import com.upzi.upzi.app.manager.theme.GenerateImage
import com.upzi.upzi.app.manager.theme.ProductXTheme
import com.upzi.upzi.core.component.AppBoxForce
import com.upzi.upzi.core.component.AppPreviewWrapper
import com.upzi.upzi.core.component.AppScaffold

/**
 * Created by Phạm Sơn at 09:57/13/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun SearchBarSection(focusRequester: FocusRequester, onTextChange: (String) -> Unit, onSearch: (String) -> Unit, onFocusContainer: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AppIcons.arrowLeft.GenerateImage(
                modifier = Modifier
                    .size(32.dp)
                    .padding(8.dp)
                    .clickable {
                        // Handle back navigation here
                    },
                color = Color.Black
            )

            AppBoxForce(
                backgroundColor = Color.White,
                content = { isFocusTextField, isFocusContainer ->
                    SearchBar(
                        focusRequester = focusRequester,
                        onTextChange = {
                            onTextChange.invoke(it)
                        },
                        isFocus = isFocusTextField,
                        isFocusContainer = isFocusContainer,
                        onSearch = onSearch
                    )
                    onFocusContainer.invoke(isFocusTextField)
                })
        }
    }
}

@Composable
fun SearchBar(focusRequester: FocusRequester, isFocus: Boolean, onTextChange: (String) -> Unit, onSearch: (String) -> Unit, isFocusContainer: (Boolean) -> Unit) {
    var getText by remember { mutableStateOf(TextFieldValue("")) }
    LaunchedEffect(isFocus) {
        if (isFocus) {
            focusRequester.requestFocus()
        }
    }

    LaunchedEffect(getText) {
        onTextChange.invoke(getText.text)
    }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(0.65f)
                .background(Color.White, RoundedCornerShape(8.dp))

        ) {
            Icon(
                imageVector = Icons.Filled.Search, contentDescription = "Search",
                modifier = Modifier
                    .size(24.dp)
                    .padding(start = 8.dp)
            )
            Spacer(Modifier.width(4.dp))
            BasicTextField(
                value = getText,
                onValueChange = {
                    getText = it
                },
                textStyle = ProductXTheme.typography.Regular.Label.Medium,
                modifier = Modifier
                    .focusRequester(focusRequester)

                    .onFocusChanged { focusState ->
                        isFocusContainer(focusState.isFocused)
                        getText = getText.copy(
                            selection = TextRange(getText.text.length)
                        )
                    },
                decorationBox = @Composable { innerTextField ->
                    Box(
                        Modifier
                            .background(Color.Transparent), // Clear TextField's background
                    ) {
                        innerTextField()
                    }
                },
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearch(getText.text)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search,
                )

            )
        }

        Box(
            modifier = Modifier
                .padding(end = 8.dp)
                .height(IntrinsicSize.Min)
                .weight(0.35f)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = ProductXTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp))
        ) {
            Text(
                text = "Tìm bằng CV",
                style = ProductXTheme.typography.Regular.Label.Medium,
                color = Color.White,
                modifier = Modifier
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
    AppPreviewWrapper {
        AppScaffold(backgroundColor = Color.White) {
            SearchBarSection(
                focusRequester = FocusRequester(),
                onTextChange = {},
                onSearch = {},
                onFocusContainer = {}
            )
        }
    }
}