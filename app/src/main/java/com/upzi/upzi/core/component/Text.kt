package com.upzi.upzi.core.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.upzi.upzi.app.manager.theme.ProductXTheme

/**
 * Created by Phạm Sơn at 13:11/3/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun AppText(
    modifier: Modifier = Modifier, text: String, style: TextStyle =  ProductXTheme.typography.Regular.Label.Medium,
    color: Color = ProductXTheme.colorScheme.onSurface,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Ellipsis,
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
        lineHeight = style.lineHeight,
        textDecoration = textDecoration,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout
    )
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun AppTextPreview() {
    AppText(
        text = "Hello",
        color = ProductXTheme.colorScheme.onSurface,
        style =  ProductXTheme.typography.Regular.Heading.Large
    )
}