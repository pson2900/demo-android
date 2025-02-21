package com.upzi.upzi.core.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
private fun BottomSheetPreview() {
    var showBottomSheet by remember { mutableStateOf(true) }

    BaseBottomSheet(openBottomSheet = showBottomSheet, sheetContent = {
        BottomSheetContent(onDismiss = {
            showBottomSheet = false
        })
    }, onDismissRequest = {
        showBottomSheet = false
    })
}

@Composable
private fun BottomSheetContent(onDismiss: () -> Unit) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text("This is a BottomSheet")
        Button(onClick = onDismiss) {
            Text("Close")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseBottomSheet(
    openBottomSheet: Boolean,
    sheetContent: @Composable ColumnScope.() -> Unit,
    onDismissRequest: () -> Unit,
    dragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() }
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        //Button that invokes the bottom sheet
        if (openBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = onDismissRequest,
                sheetState = modalBottomSheetState,
                dragHandle = dragHandle,
                content = sheetContent
            )
        }
    }
}