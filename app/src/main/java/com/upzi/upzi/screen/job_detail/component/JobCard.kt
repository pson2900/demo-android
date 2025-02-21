package com.upzi.upzi.screen.job_detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upzi.upzi.R

/**
 * Created by Phạm Sơn at 22:16/17/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Composable
fun JobItemCard() {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "Thực Tập Sinh UI/UX",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black // Adjust color
                    )
                    Text(
                        text = "Designer (Có Lương)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black // Adjust color
                    )
                    Text(
                        text = "Grab Vietnam",
                        fontSize = 14.sp,
                        color = Color.Gray // Adjust color
                    )
                }
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.company_logo), // Replace with your image resource ID
                        contentDescription = "Grab Logo",
                        modifier = Modifier.size(50.dp),
                    )

                    JobImage(image = null) // PlaceHolder Job Image
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Salary and Time +3, Location
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Salary +3
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Time",
                        tint = Color.Gray
                    )
                    Text(text = "12-20 triệu", color = Color.Gray) // Adjust color
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Salary +3
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Time",
                        tint = Color.Gray
                    )
                    Text(text = "Thực tập 4 - 6 tháng", color = Color.Gray) // Adjust color
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Salary +3
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = "Time",
                        tint = Color.Gray
                    )
                    Text(text = "Quận 7, Hồ Chí Minh", color = Color.Gray) // Adjust color
                    Text(text = "+3", color = Color.Gray) // Adjust color

                }

            }
        }
    }
}

@Composable
fun JobImage(image: Painter?) {
    val imageModifier = Modifier
        .size(36.dp)
        .clip(CircleShape)

    Surface(
        color = Color(0xff4fbe7c).copy(alpha = 0.74f), //Set green backgroud to the view
        modifier = Modifier.size(36.dp).padding(16.dp), // Adjust for desired size
        shape = RoundedCornerShape(30),

        ) {
        Column(modifier = Modifier.fillMaxSize().size(36.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "P", color = Color.White, fontSize = 15.sp)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun JobItemCardPreview() {
    JobItemCard()
}