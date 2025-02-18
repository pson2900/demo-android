package com.example.demo_structure.screen.opportunity.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.LocalNavAnimatedVisibilityScope
import com.example.demo_structure.app.manager.theme.LocalSharedTransitionScope
import com.example.demo_structure.jobList
import com.example.demo_structure.util.durationChange
import com.example.domain.model.JobDetail

/**
 * Created by Phạm Sơn at 20:25/15/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun JobItemSection(animatedVisibilityScope: AnimatedVisibilityScope, job: JobDetail, onClick: (JobDetail) -> Unit) {

    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No Scope found")
    sharedTransitionScope?.apply {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = Color.White, // Assuming white background for the item
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp) // Add padding around the item
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable(onClick = { onClick(job) }),
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            modifier = Modifier.sharedElement(
                                state = rememberSharedContentState(key = "title/${job.jobTitle}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = durationChange)
                                }
                            ),
                            text = job.jobTitle,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black // Adjust color
                        )
                        Text(
                            modifier = Modifier.sharedElement(
                                state = rememberSharedContentState(key = "description/${job.description}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = durationChange)
                                }
                            ),
                            text = job.description,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black // Adjust color
                        )
                        Text(
                            modifier = Modifier.sharedElement(
                                state = rememberSharedContentState(key = "company/${job.companyTitle}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = durationChange)
                                }
                            ),
                            text = job.companyTitle,
                            fontSize = 14.sp,
                            color = Color.Gray // Adjust color
                        )
                    }
                    Image(
                        modifier = Modifier
                            .size(50.dp)
                            .sharedElement(
                                state = rememberSharedContentState(key = "image/${job.companyLogo}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = durationChange)
                                }
                            ),
                        painter = painterResource(id = R.drawable.company_logo),
                        contentDescription = null,
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "$", color = Color.Gray) // Adjust color
                    Text(text = job.salary, color = Color.Gray) // Adjust color
                }

                Spacer(modifier = Modifier.height(8.dp))

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

}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@Preview
fun JobCardSectionPreview() {
    SharedTransitionScope {
        AnimatedVisibility(true) {
            JobItemSection(this, jobList[0]) { jobDetail ->
            }
        }


    }

}