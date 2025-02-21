package com.upzi.upzi.core.component

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.upzi.upzi.app.manager.theme.ProductXTheme

/**
 * Created by Phạm Sơn at 15:08/16/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun AppButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    background: Color = ProductXTheme.colorScheme.primary,
    contentColor: Color = ProductXTheme.colorScheme.onPrimary,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable() (RowScope.() -> Unit)
) {

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        contentPadding = contentPadding,
        colors = ButtonDefaults.buttonColors(
            containerColor = background, // Background color
            contentColor = contentColor, // Text and icon color
            disabledContainerColor = ProductXTheme.colorScheme.surface,
            disabledContentColor =  ProductXTheme.colorScheme.onSurface
        ),
        content = content
    )

}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun AppButtonPreview() {
    AppButton(
        onClick = {},
    ) {
        AppText(
            text = "OnClick Item",
            color = ProductXTheme.colorScheme.onSurface
        )
    }
}