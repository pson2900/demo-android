package com.example.demo_structure.core.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by Phạm Sơn at 08:45/8/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemedBottomSheetScaffold() {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberModalBottomSheetState()
    )
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            // Nội dung của BottomSheet
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Bottom Sheet Content",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        sheetPeekHeight = 50.dp, // Chiều cao khi bottom sheet ở trạng thái "peek"
        containerColor = MaterialTheme.colorScheme.surface, // Màu nền của toàn bộ Scaffold
        contentColor = MaterialTheme.colorScheme.onSurface, // Màu chữ mặc định trên Scaffold
        sheetContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh, // Màu nền của BottomSheet
        sheetContentColor = MaterialTheme.colorScheme.onSurface, // Màu chữ/icon trên BottomSheet
        topBar = {
            TopAppBar(
                title = { Text("Bottom Sheet Example", color = MaterialTheme.colorScheme.onSurfaceVariant) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Main Content",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemedSearchBar() {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        query = text,
        onQueryChange = { text = it },
        onSearch = { active = false },
        active = active,
        onActiveChange = {
            active = it
        },
        placeholder = { Text("Search", color = MaterialTheme.colorScheme.onSurfaceVariant) }, // Placeholder text
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant) }, // Search Icon
        trailingIcon = {
            if (active) {
                IconButton(onClick = {
                    text = ""
                }) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Close",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
/*            contentColor = MaterialTheme.colorScheme.onSurface,
            placeholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            leadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            trailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            navigationIconColor = MaterialTheme.colorScheme.onSurfaceVariant */
        )
    ) {
        // Nội dung hiển thị khi SearchBar ở trạng thái "active" (ví dụ: gợi ý tìm kiếm)
        LazyColumn {
            items(4) { index ->
                val resultText = "Suggestion $index"
                ListItem(
                    headlineContent = { Text(text = resultText, color = MaterialTheme.colorScheme.onSurface) },
                    leadingContent = { Icon(Icons.Filled.Menu, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant) },
                    modifier = Modifier.clickable {
                        text = resultText
                        active = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentShowcase() {
    var textValue by remember { mutableStateOf("") }
    var outlinedTextValue by remember { mutableStateOf("") }
    var sliderPosition by remember { mutableStateOf(0f) }
    var switchState by remember { mutableStateOf(false) }
    var checkboxState by remember { mutableStateOf(true) }
    var radioState by remember { mutableStateOf(true) }
    var selectedTabIndex by remember { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedFilterChip by remember { mutableStateOf(false) } //For FilterChip
    val inputChipText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Component Showcase") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        },
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceVariant) {
                NavigationBarItem(
                    selected = true,
                    onClick = { /*TODO*/ },
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorite") },
                    label = { Text("Favorite") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") }
                )
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text("Buttons", style = MaterialTheme.typography.headlineSmall)
                    ElevatedButton(
                        onClick = { },
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled)
                        ),
                        enabled = true
                    ) {
                        Text("Elevated Button")
                    }
                    OutlinedButton(
                        onClick = { },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled)
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                    ) {
                        Text("Outlined Button")
                    }
                    TextButton(onClick = {  },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.primary,
                            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled)
                        )
                    ) {
                        Text("Text Button")
                    }
                }

                item {
                    Text("TextField", style = MaterialTheme.typography.headlineSmall)
                    TextField(
                        value = textValue,
                        onValueChange = { textValue = it },
                        placeholder = { Text("Enter Place Holder") },
                        label = { Text("Enter text") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled),
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            disabledContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = ContentAlpha.disabled),
                            cursorColor = MaterialTheme.colorScheme.primary,
                            errorIndicatorColor = MaterialTheme.colorScheme.error,
                            errorTextColor = MaterialTheme.colorScheme.onErrorContainer,
                            errorContainerColor = MaterialTheme.colorScheme.errorContainer,
                            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledIndicatorColor = MaterialTheme.colorScheme.outline.copy(alpha = ContentAlpha.disabled),
                            focusedLabelColor = MaterialTheme.colorScheme.primary,
                            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = ContentAlpha.disabled)
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Email,
                                contentDescription = "Email",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("OutlinedTextField", style = MaterialTheme.typography.headlineSmall)
                    OutlinedTextField(
                        value = outlinedTextValue,
                        onValueChange = { outlinedTextValue = it },
                        placeholder = { Text("Enter Place Holder") },
                        label = { Text("Enter text") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled),
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                            disabledBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = ContentAlpha.disabled),
                            cursorColor = MaterialTheme.colorScheme.primary,
                            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            errorTextColor = MaterialTheme.colorScheme.onErrorContainer,
                            errorContainerColor = MaterialTheme.colorScheme.errorContainer,
                            focusedLabelColor = MaterialTheme.colorScheme.primary,
                            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = ContentAlpha.disabled),
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Email,
                                contentDescription = "Password",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    )
                }

                item {
                    Text("Slider", style = MaterialTheme.typography.headlineSmall)
                    Slider(
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it },
                        colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colorScheme.primary,
                            activeTrackColor = MaterialTheme.colorScheme.primary,
                            inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                            activeTickColor = MaterialTheme.colorScheme.onPrimary,
                            inactiveTickColor = MaterialTheme.colorScheme.surface
                        )
                    )
                }

                item {
                    Text("Switch", style = MaterialTheme.typography.headlineSmall)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Switch State:")
                        Spacer(modifier = Modifier.width(8.dp))
                        Switch(
                            checked = switchState,
                            onCheckedChange = { switchState = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                                uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                                disabledCheckedThumbColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled),
                                disabledCheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                                disabledUncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                disabledUncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant

                            )
                        )
                    }
                }

                item {
                    Text("Checkbox", style = MaterialTheme.typography.headlineSmall)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Checkbox State:")
                        Checkbox(
                            checked = checkboxState,
                            onCheckedChange = { checkboxState = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = MaterialTheme.colorScheme.primary,
                                uncheckedColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                checkmarkColor = MaterialTheme.colorScheme.onPrimary,
                                disabledCheckedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled),
                                disabledUncheckedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled)
                            )
                        )
                    }
                }

                item {
                    Text("RadioButton", style = MaterialTheme.typography.headlineSmall)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Radio Button State:")
                        RadioButton(
                            selected = radioState,
                            onClick = { radioState = !radioState },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MaterialTheme.colorScheme.primary,
                                unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                disabledSelectedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled),
                                disabledUnselectedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled)
                            )
                        )
                    }
                }
                item {
                    Divider(color = MaterialTheme.colorScheme.outline, thickness = 1.dp)
                }


                item {
                    Text("Tabs", style = MaterialTheme.typography.headlineSmall)
                    TabRow(
                        selectedTabIndex = selectedTabIndex,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    ) {
                        Tab(selected = selectedTabIndex == 0, onClick = { selectedTabIndex = 0 }, text = { Text("Tab 1") })
                        Tab(selected = selectedTabIndex == 1, onClick = { selectedTabIndex = 1 }, text = { Text("Tab 2") })
                    }
                }

                item {
                    Text("Card", style = MaterialTheme.typography.headlineSmall)
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Card Title", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("Card Content", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }

                item {
                    Text("Chips", style = MaterialTheme.typography.headlineSmall)
                    AssistChip(
                        onClick = { },
                        label = { Text("Assist Chip") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Settings",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            trailingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant

                        ),
                       /* border = AssistChipDefaults.assistChipBorder(
                            borderColor = MaterialTheme.colorScheme.outline
                        ),*/
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Close",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                    )
                }
                item {
                    SuggestionChip(onClick = { },
                        label = { Text("Suggestion Chip") },
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            iconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        /*border = SuggestionChipDefaults.suggestionChipBorder(
                            borderColor = MaterialTheme.colorScheme.outline
                        ),*/
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = "Idea",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        })
                }
                item {
                    ElevatedSuggestionChip(onClick = { },
                        label = { Text("Elevated Suggestion Chip") },
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            labelColor = MaterialTheme.colorScheme.onSurface,
                            iconContentColor = MaterialTheme.colorScheme.onSurface
                        ),
                       /* border = SuggestionChipDefaults.suggestionChipBorder(
                            borderColor = MaterialTheme.colorScheme.outline
                        ),*/
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = "Star",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        })
                }

                item {
                    var isSelected by remember { mutableStateOf(false) }
                    FilterChip(selected = isSelected, onClick = { isSelected = !isSelected },
                        label = { Text("Filter Chip") },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
//                            labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
//                            iconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
//                            selectedLeadingIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                      /*  border = FilterChipDefaults.filterChipBorder(
                            borderColor = MaterialTheme.colorScheme.outline,
                            selectedBorderColor = MaterialTheme.colorScheme.primary,
                        ),*/
                        leadingIcon = {
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = "Check",
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        })
                }

                item {

                    InputChip(
                        selected = false,
                        onClick = {},
                        label = { Text(inputChipText) },
                        colors = InputChipDefaults.inputChipColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            trailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
//                            elevation = null,
                            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = ContentAlpha.disabled),
                            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = ContentAlpha.disabled)
                        ),
                       /* border = InputChipDefaults.inputChipBorder(
                            borderColor = MaterialTheme.colorScheme.outline,
                            disabledBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = ContentAlpha.disabled),
                            borderWidth = 1.dp,
                        ),*/
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Cancel",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    )

                }

                item {
                    Text("Snackbar", style = MaterialTheme.typography.headlineSmall)
                    Button(
                        onClick = { showDialog = true }, colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text("Show Snackbar")
                    }
                }

                item {
                    Text("Progress Indicator", style = MaterialTheme.typography.headlineSmall)
                    LinearProgressIndicator(
                        progress = 0.5f,
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Alert Dialog", color = MaterialTheme.colorScheme.onSurface) },
                text = { Text("This is an example alert dialog.", color = MaterialTheme.colorScheme.onSurface) },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }, colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary,
                    )) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }, colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )) {
                        Text("Dismiss")
                    }
                },
                containerColor = MaterialTheme.colorScheme.surface,
                titleContentColor = MaterialTheme.colorScheme.onSurface,
                textContentColor = MaterialTheme.colorScheme.onSurface

            )
        }
    }
}
/*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentShowcase() {
    var textValue by remember { mutableStateOf("") }
    var sliderPosition by remember { mutableStateOf(0f) }
    var switchState by remember { mutableStateOf(true) }
    var checkboxState by remember { mutableStateOf(true) }
    var radioState by remember { mutableStateOf(true) }
    var selectedTabIndex by remember { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Component Showcase") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { *//*TODO*//* },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        },
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceVariant) {
                NavigationBarItem(
                    selected = true,
                    onClick = { *//*TODO*//* },
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorite") },
                    label = { Text("Favorite") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { *//*TODO*//* },
                    icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") }
                )
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text("Buttons", style = MaterialTheme.typography.headlineSmall)
                    ElevatedButton(
                        onClick = { },
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text("Elevated Button")
                    }
                    OutlinedButton(
                        onClick = { },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.primary
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                    ) {
                        Text("Outlined Button")
                    }
                }

                item {
                    Text("TextField", style = MaterialTheme.typography.headlineSmall)
                    TextField(
                        value = textValue,
                        onValueChange = { textValue = it },
                        label = { Text("Enter text") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            cursorColor = MaterialTheme.colorScheme.primary,
                            errorIndicatorColor = MaterialTheme.colorScheme.error
                        )
                    )
                }

                item {
                    Text("Slider", style = MaterialTheme.typography.headlineSmall)
                    Slider(
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it },
                        colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colorScheme.primary,
                            activeTrackColor = MaterialTheme.colorScheme.primary,
                            inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                            activeTickColor = MaterialTheme.colorScheme.onPrimary,
                            inactiveTickColor = MaterialTheme.colorScheme.surface
                        )
                    )
                }

                item {
                    Text("Switch", style = MaterialTheme.typography.headlineSmall)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Switch State:")
                        Switch(
                            checked = switchState,
                            onCheckedChange = { switchState = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = MaterialTheme.colorScheme.primary,
                                checkedTrackColor = MaterialTheme.colorScheme.primary,
                                uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                            )
                        )
                    }
                }

                item {
                    Text("Checkbox", style = MaterialTheme.typography.headlineSmall)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Checkbox State:")
                        Checkbox(
                            checked = checkboxState,
                            onCheckedChange = { checkboxState = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = MaterialTheme.colorScheme.primary,
                                uncheckedColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }

                item {
                    Text("RadioButton", style = MaterialTheme.typography.headlineSmall)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Radio Button State:")
                        RadioButton(
                            selected = radioState,
                            onClick = { radioState = !radioState },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MaterialTheme.colorScheme.primary,
                                unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
                item {
                    Divider(color = MaterialTheme.colorScheme.outline, thickness = 1.dp)
                }
                item {
                    OutlinedTextField(
                        value = textValue,
                        onValueChange = { textValue = it },
                        label = { Text("Enter text") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                            cursorColor = MaterialTheme.colorScheme.primary,
                        )
                    )
                }

                item {
                    Text("Tabs", style = MaterialTheme.typography.headlineSmall)
                    TabRow(
                        selectedTabIndex = selectedTabIndex,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    ) {
                        Tab(selected = selectedTabIndex == 0, onClick = { selectedTabIndex = 0 }, text = { Text("Tab 1") })
                        Tab(selected = selectedTabIndex == 1, onClick = { selectedTabIndex = 1 }, text = { Text("Tab 2") })
                    }
                }

                item {
                    Text("Card", style = MaterialTheme.typography.headlineSmall)
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Card Title", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("Card Content", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }

                item {
                    Text("Chip", style = MaterialTheme.typography.headlineSmall)
                    AssistChip(
                        onClick = { },
                        label = { Text("Assist Chip") },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            labelColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        *//* border = AssistChipDefaults.assistChipBorder(
                             borderColor = MaterialTheme.colorScheme.outline
                         )*//*
                    )
                }

                item {
                    Text("Snackbar", style = MaterialTheme.typography.headlineSmall)
                    Button(
                        onClick = { showDialog = true }, colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text("Show Snackbar")
                    }
                }

                item {
                    Text("Progress Indicator", style = MaterialTheme.typography.headlineSmall)
                    LinearProgressIndicator(
                        progress = 0.5f,
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Alert Dialog") },
                text = { Text("This is an example alert dialog.") },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Confirm", color = MaterialTheme.colorScheme.primary)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Dismiss", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                },
                containerColor = MaterialTheme.colorScheme.surface
            )
        }
    }
}*/

@Preview(showBackground = true)
//@ThemePreviews
@Composable
fun DefaultColorSchemePreview() {
    AppPreviewWrapper {
        ComponentShowcase()
//        ThemedSearchBar()
//        ThemedBottomSheetScaffold()
    }
}


/*
@Preview(showBackground = true)
@Composable
fun DarkDefaultColorSchemePreview() {
    MaterialTheme(
        colorScheme = DarkDefaultColorScheme
    ) {
        ComponentShowcase()
    }
}

@Preview(showBackground = true)
@Composable
fun LightDefaultColorSchemePreview() {
    MaterialTheme(
        colorScheme = LightDefaultColorScheme
    ) {
        ComponentShowcase()
    }
}
*/
