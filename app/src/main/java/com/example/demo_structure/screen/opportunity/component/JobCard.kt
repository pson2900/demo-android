package com.example.demo_structure.screen.opportunity.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo_structure.R
import com.example.demo_structure.jobList
import com.example.domain.model.JobDetail

/**
 * Created by Phạm Sơn at 20:25/15/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun JobItemSection(job: JobDetail, onClick: (JobDetail) -> Unit ) {
    Surface (
        shape = RoundedCornerShape(20.dp),
        color = Color.White, // Assuming white background for the item
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp) // Add padding around the item
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(20.dp))

                .clickable(onClick = { onClick(job) }),
        ) {
            // Row 1: Title and Grab Logo
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = job.jobTitle,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black // Adjust color
                    )
                    Text(
                        text = job.description,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black // Adjust color
                    )
                    Text(
                        text = job.companyTitle,
                        fontSize = 14.sp,
                        color = Color.Gray // Adjust color
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.company_logo), // Replace with your image resource ID
                    contentDescription = "Grab Logo",
                    modifier = Modifier.size(50.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Row 2: Salary
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "$", color = Color.Gray) // Adjust color
                Text(text = job.salary, color = Color.Gray) // Adjust color
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Row 3: Location
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    Icons.Filled.Place,
                    contentDescription = "Location",
                    tint = Color.Gray // Adjust color
                )
                Text(text = job.location, color = Color.Gray) // Adjust color
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Row 4: Like & Crown
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFE8F5E9) // Light green
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(text = "👍", color = Color(0xFF4CAF50)) // Green
                        Text(text = "80% phù hợp", color = Color(0xFF4CAF50)) // Green
                    }
                }

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFE0F2F1) // Light teal
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(text = "👑", color = Color(0xFF26A69A)) // Teal
                        Text(text = "Hồ sơ xếp hạng 1", color = Color(0xFF26A69A)) // Teal
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Row 5: Checkmark
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "✔️", color = Color(0xFF4CAF50)) // Green Checkmark
                Text(text = "Đáp ứng 5/5 yêu cầu công việc", color = Color(0xFF4CAF50)) // Green
            }
        }
    }
}


@Composable
@Preview
fun JobCardSectionPreview(){
    JobItemSection(jobList[0]){ jobDetail ->
    }
}