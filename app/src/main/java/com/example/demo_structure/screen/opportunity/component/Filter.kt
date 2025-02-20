package com.example.demo_structure.screen.opportunity.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_structure.core.component.AppChip

/**
 * Created by Phạm Sơn at 17:31/14/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun FilterSection(){
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        AppChip(selected = true, onSelectedChange = {}) {
            Text("Remote")
        }
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

@Composable
@Preview
fun FilterSectionPreview(){
    FilterSection()
}