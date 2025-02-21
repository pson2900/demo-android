package com.example.demo_structure.core.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_structure.R

@Preview(showBackground = true)
@Composable
private fun ComboBoxDropdownPreview() {
    ComboBoxDropdown(modifier = Modifier.padding(16.dp))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComboBoxDropdown(
    modifier: Modifier = Modifier,
    hint: String = "Select an option",
    options: List<String> = listOf("Option 1", "Option 2", "Option 3")
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf<String?>(null) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            value = selectedText ?: "",
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .border(1.dp, colorResource(R.color.mischka), RoundedCornerShape(12.dp)),
            placeholder = { Text(hint) }, // Hint text
            trailingIcon = {
                Icon(Icons.Filled.ArrowDropDown, contentDescription = "Dropdown icon")
            },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = Color.Transparent,
                textColor = colorResource(R.color.cod_gray),
                backgroundColor = Color.Transparent
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedText = option
                        expanded = false
                    }
                )
            }
        }
    }
}