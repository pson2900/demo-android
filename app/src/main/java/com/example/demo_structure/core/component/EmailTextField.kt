package com.example.demo_structure.core.component

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
import androidx.compose.material3.TextFieldDefaults.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo_structure.R


@Preview(showBackground = true)
@Composable
private fun EmailTextFieldPreview() {
    var email by remember { mutableStateOf("") }
    EmailTextField(
        modifier = Modifier.padding(16.dp),
        hint = "Enter email",
        value = email,
        onValueChange = { email = it },
        onDone = {},
        error = "error",
        onClose = { email = "" }
    )
}

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    onDone:()-> Unit,
    onClose: () -> Unit,
    isSuccess: Boolean = false,
    error: String? = null
) {
    var isFocused by remember { mutableStateOf(false) }
    val paddingTop = 4.dp

    Column(modifier = modifier.fillMaxWidth()) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(
                    modifier = Modifier.padding(top = paddingTop),
                    style = TextStyle(
                        fontSize = 14.sp
                    ),
                    text = hint,
                    color = Color.Gray
                )
            },
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Gray,
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
                        !error.isNullOrEmpty() -> Color.Red
                        isFocused -> colorResource(R.color.portage)
                        else -> Color.Gray
                    },
                    shape = RoundedCornerShape(12.dp)
                )
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            trailingIcon = {
                if (value.isNotEmpty()) {
                    IconButton(
                        onClick = onClose,
                        enabled = !isSuccess
                    ) {
                        Icon(
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp),
                            imageVector = if (isSuccess) ImageVector.vectorResource(R.drawable.ic_success) else ImageVector.vectorResource(
                                R.drawable.ic_close
                            ),
                            contentDescription = "Close",
                            tint = if (isSuccess) Color.Green else Color.Gray
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

        if (error?.isNotEmpty() == true) {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp),
                    painter = painterResource(id = R.drawable.ic_error),
                    contentDescription = "Error",
                    tint = Color.Red
                )
                Text(
                    text = error,
                    color = Color.Red,
                    style = TextStyle(
                        fontSize = 12.sp
                    ),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}
