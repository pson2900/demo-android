package com.upzi.upzi.core.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.upzi.upzi.app.manager.theme.ProductXTheme
import com.upzi.upzi.app.manager.theme.hexToColor

/**
 * Created by Phạm Sơn at 10:49/21/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Composable
fun AppBox(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    shape: Shape = RectangleShape,
    border: BorderStroke? = null,
    contentAlignment: Alignment = Alignment.CenterStart,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .shadow(elevation = 0.dp, shape = shape)
            .background(
                color = backgroundColor,
                shape = shape
            )
            .clip(shape)
            .then(if (border != null) modifier.border(border, shape) else Modifier)
            .then(if (onClick != null) modifier.clickable { onClick.invoke() } else Modifier),
        contentAlignment = contentAlignment,
        propagateMinConstraints = false,
        content = content
    )
}

@Composable
fun AppBoxForce(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    contentAlignment: Alignment = Alignment.CenterStart,
    content: @Composable BoxScope.(Boolean, (Boolean) -> Unit) -> Unit
) {

    val (isFocus, setFocus) = remember { mutableStateOf(false) }
    val colorSelect = ProductXTheme.colorScheme.primary
    val colorUnSelect = hexToColor("#64748B")
    val interactionSource = remember { MutableInteractionSource() }
    val shape = RoundedCornerShape(5.dp)
    Box(
        modifier = modifier
            .shadow(elevation = 0.dp, shape = shape)
            .clip(shape)
            .border(BorderStroke(1.dp, if (isFocus) colorSelect else colorUnSelect), shape)
            .background(
                color = backgroundColor,
                shape = shape
            )

            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                setFocus(!isFocus)
            },

        contentAlignment = contentAlignment,
        propagateMinConstraints = false,
        content = {
            Box(Modifier) {
                content(isFocus) { isTextFocus ->
                    setFocus(isTextFocus)
                }
            }
        }
    )
}


@Composable
@Preview
fun AppBoxPreview() {
    AppPreviewWrapper {
        AppBoxForce(backgroundColor = Color.White) { isFocus, textFieldFocus ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Back Button
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.clickable {
                        // Handle back navigation here
                    }
                )

                /*  SearchBar(focusRequest, isFocus) {

                  }*/
            }
        }
    }
}