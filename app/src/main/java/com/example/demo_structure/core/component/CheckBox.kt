package com.example.demo_structure.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Created by Phạm Sơn at 13:17/22/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun AppCheckBox(modifier: Modifier = Modifier) {
    val isChecked = remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Checkbox(
            checked = isChecked.value,
            onCheckedChange = { isChecked.value = it },
            modifier = Modifier.padding(8.dp),
            enabled = true,
            colors = CheckboxDefaults.colors(
                checkmarkColor = Color.White
            ),
            interactionSource = remember { MutableInteractionSource() }
        )
        Text(
            text = if (isChecked.value) "Checked" else "Unchecked",
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
@ThemePreviews
fun AppCheckBoxPreview(){
    AppCheckBox()
}