package com.example.demo_structure.core.component.otp

import android.content.Context
import androidx.annotation.IntRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo_structure.R
import com.example.demo_structure.util.extension.hideKeyboard
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun OTPInputPreview() {
    PassCodeTextField(modifier = Modifier, context = LocalContext.current, onValueChange = {

    }, 6 )
}

@Composable
fun PassCodeTextField(
    modifier: Modifier = Modifier,
    context: Context,
    onValueChange: (String) -> Unit,
    @IntRange(from = 4, to = 6) numDigits: Int = 4,
    errorMessage: String? = null
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() } // Create FocusRequester
    var (text, setText) = remember {
        mutableStateOf(TextFieldValue(""))
    }

    LaunchedEffect(
        key1 = text,
    ) {
        if (text.text.length == numDigits) {
            focusManager.clearFocus()
            hideKeyboard(context, focusManager)
        }
    }

    Column(
        modifier = modifier.focusRequester(focusRequester),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.width(224.dp)
        ) {
            CompositionLocalProvider() {
                BasicTextField(
                    value = text,
                    onValueChange = { newValue ->
                        onValueChange.invoke(newValue.text)
                        if (newValue.text.length <= numDigits) {
                            setText(
                                if (newValue.selection.length > 0) {
                                    newValue.copy(
                                        selection = text.selection,
                                    )
                                } else {
                                    newValue
                                }
                            )
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Done,
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    textStyle = TextStyle(
                        color = Transparent,
                    ),
                    cursorBrush = SolidColor(Transparent),
                    modifier = Modifier
                        .clip(CircleShape)
                        .fillMaxWidth()
                        .background(Transparent)

                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .clip(CircleShape)
                    .fillMaxWidth()
                    .background(Transparent)
                    .padding(
                        top = 16.dp,
                        bottom = 16.dp,
                        start = 0.dp,
                        end = 0.dp
                    )
            ) {
                for (i in 0 until numDigits) {
                    if (!errorMessage.isNullOrEmpty()) {
                        Dot(colorResource(R.color.bridesmaid))
//                        setText(TextFieldValue(""))
                    } else
                        if (i < text.text.length) {
                            Dot(colorResource(R.color.tundora))
                        } else {
                            Dot(colorResource(R.color.mercury))
                        }
                }
            }
        }
        if (!errorMessage.isNullOrEmpty()) {
            Text(
                modifier = Modifier.padding(top = 8.dp), text = errorMessage,
                style = TextStyle(color = colorResource(R.color.alizarin_crimson), fontSize = 16.sp)
            )
        }
    }
    // LaunchedEffect outside clickable
    LaunchedEffect(key1 = focusRequester) {
        delay(300)
        focusRequester.requestFocus()
    }
}

// This can be replaced with any composable as per requirement.
@Composable
private fun Dot(color: Color) {
    Box(
        modifier = Modifier
            .requiredSize(size = 24.dp)
            .background(
                color = color,
                shape = CircleShape,
            )
    )
}