package com.example.demo_structure.util.extension

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

fun buildClickableText(
    text: String,
    clickableText: String,
    style: SpanStyle,
    textLinkStyles : SpanStyle,
    tag: String,
    onClick: ((link: LinkAnnotation) -> Unit)? = null
): AnnotatedString {
    return buildAnnotatedString {
        val startIndex = text.indexOf(clickableText)
        val endIndex = startIndex + clickableText.length

        withStyle(style = style) { append(text) }
        addLink(
            LinkAnnotation.Clickable(
                tag = tag,
                styles = TextLinkStyles(textLinkStyles),
                linkInteractionListener = onClick
            ),
            startIndex,
            endIndex
        )
    }
}

fun String.truncate(maxLength: Int = 25): String {
    return if (this.length > maxLength) {
        this.substring(0, maxLength) + "..."
    } else {
        this
    }
}

fun isJsonObjectRegex(text: String): Boolean {
    val jsonRegex = Regex("""^\{.*\}$""")
    return jsonRegex.matches(text.trim())
}

fun randomString(length: Int = 5): String {
    val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
    return (1..length)
        .map { chars.random() }
        .joinToString("")
}