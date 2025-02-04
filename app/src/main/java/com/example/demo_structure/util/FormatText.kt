package com.example.demo_structure.util

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import kotlin.text.append

object FormatText {
    @Composable
    fun buildClickableText(
        text: String, // The full text including the specific text
        clickableText: String, // The specific text you want to be clickable
        style: SpanStyle,
        textLinkStyles: SpanStyle,
        tag: String,
        onClick: (() -> Unit)? = null
    ): Pair<AnnotatedString, List<Pair<String, () -> Unit>>> {
        val annotatedString = buildAnnotatedString {
            val startIndex = text.indexOf(clickableText)
            val endIndex = startIndex + clickableText.length

            withStyle(style = style) {
                append(text)
            }
            pushStringAnnotation(tag = tag, annotation = "")
            addStyle(
                style = textLinkStyles,
                start = startIndex,
                end = endIndex
            )
            pop()
        }

        val annotations = mutableListOf<Pair<String, () -> Unit>>()
        if (onClick != null) {
            annotations.add(Pair(tag, onClick))
        }
        return Pair(annotatedString, annotations)
    }
}
