package com.example.demo_structure.screen.onboarding.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.R
import com.example.domain.model.Role

@Preview(showBackground = true)
@Composable
private fun ItemRolePreview() {
    AppPreviewWrapper { modifier ->
        ItemRole( Role(1, "Trưởng nhóm", "", true))
    }
}

@Composable
fun ItemRole(role: Role) {
    val borderColor = if (role.isSelect) R.color.portage else R.color.mischka
    val background = if (role.isSelect) R.color.selago else R.color.white
    Row(
        modifier = Modifier
            .background(colorResource(background), shape = RoundedCornerShape(50.dp))
            .border(1.dp, colorResource(borderColor), shape = RoundedCornerShape(50.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_certification), // Thay bằng hình của bạn
            contentDescription = "Icon",
            modifier = Modifier
                .size(size = 24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = role.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

