package com.upzi.upzi.core.permission

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

/**
 * Created by Phạm Sơn at 12:55/18/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@OptIn(ExperimentalPermissionsApi::class, ExperimentalPermissionsApi::class)
@Composable
fun FolderAccessScreen() {
    val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        listOf(
            Manifest.permission.READ_MEDIA_IMAGES,
        )
    } else {
        listOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    val permissionState: MultiplePermissionsState = rememberMultiplePermissionsState(permissions = permissions)
    var isPermissionGranted by remember {
        mutableStateOf(false)
    }

    if (permissionState.allPermissionsGranted) {
        isPermissionGranted = true
        Text(text = "Permissions Granted")
    } else {
        isPermissionGranted = false
        PermissionRequestScreen(permissionState = permissionState)
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRequestScreen(permissionState: MultiplePermissionsState) {
    Column {
        val textToShow = if (permissionState.shouldShowRationale) {
            "The application needs the permissions to load folders, please grant it"
        } else {
            "The application needs the permissions to load folders, please grant it"
        }
        Text(text = textToShow)
        Button(onClick = {
            permissionState.launchMultiplePermissionRequest()
        }) {
            Text("Request Permissions")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFolderAccessScreen() {
    FolderAccessScreen()
}