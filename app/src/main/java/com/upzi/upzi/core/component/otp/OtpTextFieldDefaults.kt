package com.upzi.upzi.core.component.otp

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.upzi.upzi.app.manager.theme.ProductXTheme
import com.upzi.upzi.R

object OtpTextFieldDefaults {

    @Composable
    fun outlinedContainer(
        size: Dp = 54.dp,
        shape: Shape = RoundedCornerShape(16.dp),
        containerColor: Color = ProductXTheme.colorScheme.background_1,
        focusedBorderColor: Color = colorResource(R.color.violets_are_blue),
        unfocusedBorderColor: Color = colorResource(R.color.alto),
        focusedBorderWidth: Dp = 1.dp,
        unfocusedBorderWidth: Dp = 1.dp,
        errorColor: Color = colorResource(R.color.alizarin_crimson)
    ): DigitContainerStyle.Outlined {
        return DigitContainerStyle.Outlined(
            size = size,
            shape = shape,
            containerColor = containerColor,
            focusedBorderColor = focusedBorderColor,
            unfocusedBorderColor = unfocusedBorderColor,
            focusedBorderWidth = focusedBorderWidth,
            unfocusedBorderWidth = unfocusedBorderWidth,
            errorColor = errorColor,
        )
    }

    @Composable
    fun underlinedContainer(
        size: Dp = 54.dp,
        containerColor: Color = MaterialTheme.colorScheme.background,
        focusedBorderColor: Color = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        focusedBorderWidth: Dp = 2.dp,
        unfocusedBorderWidth: Dp = 2.dp,
        errorColor: Color = MaterialTheme.colorScheme.error,
    ): DigitContainerStyle.Underlined {
        return DigitContainerStyle.Underlined(
            size = size,
            containerColor = containerColor,
            focusedBorderColor = focusedBorderColor,
            unfocusedBorderColor = unfocusedBorderColor,
            focusedBorderWidth = focusedBorderWidth,
            unfocusedBorderWidth = unfocusedBorderWidth,
            errorColor = errorColor
        )
    }

}