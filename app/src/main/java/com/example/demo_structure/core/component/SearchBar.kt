package com.example.demo_structure.core.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import  com.example.demo_structure.R


@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    SearchBar(
        modifier = Modifier,
        query = "",
        placeholder = "Tìm môn học yêu thích",
        onQueryChange = {

        })
}


@Composable
fun SearchBar(
    query: String,
    placeholder: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(
                width = 1.dp,
                color = colorResource(R.color.alto),
                shape = RoundedCornerShape(50)
            ),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(50),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Icon",
                tint = colorResource(R.color.cod_gray)
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                color = colorResource(R.color.cod_gray),
                textAlign = TextAlign.Companion.Left,
                modifier = Modifier.fillMaxWidth()
            )
        },
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(

            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent
        ),
        textStyle = TextStyle(textAlign = TextAlign.Companion.Left)
    )
}