package com.example.demo_structure.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

object FormatText {
    fun buildClickableText(
        text: String, // The full text including the specific text
        clickableText: String, // The specific text you want to be clickable
        style: SpanStyle,
        tag: String,
        color: Color,
        onClick: ((link: LinkAnnotation) -> Unit)? = null
    ): AnnotatedString {
        return buildAnnotatedString {
            val startIndex = text.indexOf(clickableText)
            val endIndex = startIndex + clickableText.length

            val textLinkStyles = TextLinkStyles(
                style = style.copy(color = color,
                    fontSize = 17.sp
                )
            )
            withStyle(style = style) { append(text) }
            addLink(
                LinkAnnotation.Clickable(
                    tag = tag,
                    styles = textLinkStyles,
                    linkInteractionListener = onClick
                ),
                startIndex,
                endIndex
            )
        }
    }
}