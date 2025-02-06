package com.example.demo_structure.core.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.demo_structure.app.manager.theme.ProductXTheme

/**
 * Created by Phạm Sơn at 15:08/16/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun AppButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    background: Color,
    colorEffect: Color,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        contentPadding = contentPadding,
        colors = ButtonDefaults.buttonColors(
            containerColor = background, // Background color
            contentColor = colorEffect, // Text and icon color
            disabledContainerColor = Color.DarkGray, // Background color when disabled
            disabledContentColor = Color.DarkGray // Text and icon color when disabled
        ),
        content = content
    )

}


@Composable
@ThemePreviews
fun AppButtonPreview(){
    AppPreviewWrapper {
        AppButton(onClick = {}, background = ProductXTheme.colorScheme.background, colorEffect = ProductXTheme.colorScheme.onPrimary) {
            Text("OnClick Item")
        }
    }
}