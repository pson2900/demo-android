package com.example.demo_structure.core.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.app.manager.theme.RegularFont

/**
 * Created by Phạm Sơn at 13:11/3/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun AppText(
    modifier: Modifier = Modifier, text: String, style: TextStyle = RegularFont.Label.Medium,
    color: Color = ProductXTheme.colorScheme.onPrimary,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        style = style,
        textAlign = textAlign,
        lineHeight = lineHeight,
        textDecoration = textDecoration,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun a() {
    AppText(text = "Hello", style = RegularFont.Heading.Large)
}