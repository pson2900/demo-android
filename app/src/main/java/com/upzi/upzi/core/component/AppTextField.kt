package com.upzi.upzi.core.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upzi.upzi.R
import kotlinx.coroutines.delay


@Preview(showBackground = true)
@Composable
private fun AppTextFieldPreview() {
    var email by remember { mutableStateOf("") }
    AppTextField(
        modifier = Modifier.padding(16.dp),
        hint = "Enter email",
        textValue = email,
        onValueChange = { email = it },
        onDone = {},
        onFocusChanged = {},
        error = "error",
    )
}


@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    hint: String,
    textValue: String,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit,
    onFocusChanged: (Boolean) -> Unit,
    isSuccess: Boolean = false,
    error: String? = null
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(text = textValue, selection = TextRange(textValue.length)))
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        var labelColor = when {
            textFieldValue.text.isEmpty() && !isFocused -> R.color.cod_gray
            else -> R.color.boulder
        }

        val labelPadding = if (textFieldValue.text.isEmpty() && !isFocused) 0.dp else 4.dp
        TextField(
            value = textFieldValue,
            onValueChange = { newTextFieldValue ->
                textFieldValue = newTextFieldValue
                onValueChange(newTextFieldValue.text)
            },
            label = {
                Text(
                    modifier = Modifier.padding(top = labelPadding),
                    style = TextStyle(
                        fontSize = 14.sp
                    ),
                    text = hint,
                    color = colorResource(labelColor)
                )
            },
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = colorResource(R.color.cod_gray),
                unfocusedTextColor = colorResource(R.color.cod_gray),
                disabledTextColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                cursorColor = Color.Gray,
                errorCursorColor = Color.Gray,
                errorContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .border(
                    width = if (isFocused) 2.dp else 1.dp,
                    color = when {
                        !error.isNullOrEmpty() -> colorResource(R.color.alizarin_crimson)
                        isFocused -> colorResource(R.color.portage)
                        else -> colorResource(R.color.alto)
                    },
                    shape = RoundedCornerShape(12.dp)
                )
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                    onFocusChanged(focusState.isFocused)
                }
                .focusRequester(focusRequester),
            trailingIcon = {
                val isShowSuccess = (isSuccess && !isFocused)
                if (textFieldValue.text.isNotEmpty() && isFocused || isShowSuccess) {
                    IconButton(
                        onClick = {
                            textFieldValue = TextFieldValue("")
                            onValueChange(textFieldValue.text)
                        },
                        enabled = !isShowSuccess
                    ) {
                        Icon(
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp),
                            imageVector = if (isShowSuccess) ImageVector.vectorResource(R.drawable.ic_success) else ImageVector.vectorResource(
                                R.drawable.ic_close
                            ),
                            contentDescription = "Close",
                            tint = if (isShowSuccess) colorResource(R.color.mountain_meadow) else colorResource(
                                R.color.boulder
                            )
                        )
                    }
                }
            },
            isError = error?.isNotEmpty() == true,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onDone.invoke()
                }
            )
        )
        LaunchedEffect(isFocused) {
            if (isFocused) {
                focusRequester.requestFocus()
                delay(200)
                textFieldValue =
                    textFieldValue.copy(selection = TextRange(textFieldValue.text.length))
            }
        }

        if (error?.isNotEmpty() == true) {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp),
                    painter = painterResource(id = R.drawable.ic_email_error),
                    contentDescription = "Error",
                    tint = colorResource(R.color.alizarin_crimson)
                )
                Text(
                    text = error,
                    color = colorResource(R.color.alizarin_crimson),
                    style = TextStyle(
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}

//@Composable
//fun AppTextField(
//    modifier: Modifier = Modifier,
//    hint: String,
//    value: String,
//    onValueChange: (String) -> Unit,
//    onDone: () -> Unit,
//    onClose: () -> Unit,
//    isSuccess: Boolean = false,
//    error: String? = null
//) {
//    var isFocused by remember { mutableStateOf(false) }
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//    ) {
//        var labelColor = when {
//            value.isEmpty() && !isFocused -> R.color.cod_gray
//            else -> R.color.boulder
//        }
//
//        val labelPadding = if (value.isEmpty() && !isFocused) 0.dp else 4.dp
//        TextField(
//            value = value,
//            onValueChange = onValueChange,
//            label = {
//                Text(
//                    modifier = Modifier.padding(top = labelPadding),
//                    style = TextStyle(
//                        fontSize = 14.sp
//                    ),
//                    text = hint,
//                    color = colorResource(labelColor)
//                )
//            },
//            textStyle = TextStyle(fontSize = 16.sp),
//            colors = TextFieldDefaults.colors(
//                focusedTextColor = colorResource(R.color.cod_gray),
//                unfocusedTextColor = colorResource(R.color.cod_gray),
//                disabledTextColor = Color.LightGray,
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent,
//                disabledIndicatorColor = Color.Transparent,
//                errorIndicatorColor = Color.Transparent,
//                cursorColor = Color.Gray,
//                errorCursorColor = Color.Gray,
//                errorContainerColor = Color.Transparent,
//                focusedContainerColor = Color.Transparent,
//                unfocusedContainerColor = Color.Transparent,
//            ),
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(64.dp)
//                .border(
//                    width = if (isFocused) 2.dp else 1.dp,
//                    color = when {
//                        !error.isNullOrEmpty() -> colorResource(R.color.alizarin_crimson)
//                        isFocused -> colorResource(R.color.portage)
//                        else -> colorResource(R.color.alto)
//                    },
//                    shape = RoundedCornerShape(12.dp)
//                )
//                .onFocusChanged { focusState ->
//                    isFocused = focusState.isFocused
//                },
//            trailingIcon = {
//                val isShowSuccess = isSuccess && !isFocused
//                if (value.isNotEmpty() || isShowSuccess) {
//                    IconButton(
//                        onClick = onClose,
//                        enabled = !isShowSuccess
//                    ) {
//                        Icon(
//                            modifier = Modifier
//                                .width(20.dp)
//                                .height(20.dp),
//                            imageVector = if (isShowSuccess) ImageVector.vectorResource(R.drawable.ic_success) else ImageVector.vectorResource(
//                                R.drawable.ic_close
//                            ),
//                            contentDescription = "Close",
//                            tint = if (isShowSuccess) colorResource(R.color.mountain_meadow) else colorResource(
//                                R.color.boulder
//                            )
//                        )
//                    }
//                }
//            },
//            isError = error?.isNotEmpty() == true,
//            singleLine = true,
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Email,
//                imeAction = ImeAction.Done
//            ),
//            keyboardActions = KeyboardActions(
//                onDone = {
//                    onDone.invoke()
//                }
//            )
//        )
//
//        if (error?.isNotEmpty() == true) {
//            Row(
//                modifier = Modifier.padding(top = 8.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    modifier = Modifier
//                        .width(16.dp)
//                        .height(16.dp),
//                    painter = painterResource(id = R.drawable.ic_email_error),
//                    contentDescription = "Error",
//                    tint = Color.Red
//                )
//                Text(
//                    text = error,
//                    color = colorResource(R.color.alizarin_crimson),
//                    style = TextStyle(
//                        fontSize = 16.sp
//                    ),
//                    modifier = Modifier.padding(start = 4.dp)
//                )
//            }
//        }
//    }
//}
