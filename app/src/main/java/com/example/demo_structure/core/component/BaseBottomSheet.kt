package com.example.demo_structure.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun BottomSheetPreview() {
    var showBottomSheet by remember { mutableStateOf(true) }

    BaseBottomSheet(isShowBottomSheet = showBottomSheet, sheetContent = {
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

@Composable
fun BaseBottomSheet(
    isShowBottomSheet: Boolean,
    sheetContent: @Composable () -> Unit,
    onDismissRequest: () -> Unit
) {
    val sheetState = androidx.compose.material.rememberModalBottomSheetState(Hidden)
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ModalBottomSheetLayout(
        modifier = Modifier.background(Color.Transparent),
        sheetState = sheetState,
        sheetShape = bottomSheetShape,
        sheetContent = {
            sheetContent()
        }
    ) {
        if (sheetState.isVisible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = {
                        onDismissRequest.invoke()
                        coroutineScope.launch { sheetState.hide() }
                    })
            )
        }
    }
    LaunchedEffect(isShowBottomSheet) {
        if (isShowBottomSheet) {
            sheetState.show()
        } else if (sheetState.isVisible) {
            sheetState.hide()
        }
    }
    LaunchedEffect(sheetState.currentValue) {
        if (sheetState.currentValue == Hidden) {
            onDismissRequest.invoke()
        }
    }
}