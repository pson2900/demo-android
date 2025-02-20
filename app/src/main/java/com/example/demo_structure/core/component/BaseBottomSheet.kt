package com.example.demo_structure.core.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
private fun BottomSheetPreview() {
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

/*
@Composable
fun BaseBottomSheet(
    isShowBottomSheet: Boolean,
    sheetContent: @Composable () -> Unit,
    onDismissRequest: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(Hidden)
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
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseBottomSheet(
    isShowBottomSheet: Boolean,
    sheetContent: @Composable ColumnScope.() -> Unit,
    onDismissRequest: () -> Unit
) {
    var openBottomSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        //Button that invokes the bottom sheet
        Button(onClick = { openBottomSheet = true }) {
            Text(text = "Open Bottom Sheet")
        }
        if (openBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { openBottomSheet = false },
                sheetState = modalBottomSheetState,
                content = sheetContent
            )
           /* Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Bottom Sheet Content")
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = {
                    scope.launch { modalBottomSheetState.hide() }
                }) {
                    Text("Hide Sheet")
                }
            }*/
        }
    }
}