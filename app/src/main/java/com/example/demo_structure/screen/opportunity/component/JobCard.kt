package com.example.demo_structure.screen.opportunity.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.demo_structure.app.manager.theme.AppIcons
import com.example.demo_structure.app.manager.theme.Generate
import com.example.demo_structure.app.manager.theme.LocalSharedTransitionScope
import com.example.demo_structure.core.component.AppBox
import com.example.demo_structure.core.component.AppText
import com.example.demo_structure.jobList
import com.example.demo_structure.util.JobCardSharedElementKey
import com.example.demo_structure.util.JobCardSharedElementKey.Companion.getShareKey
import com.example.demo_structure.util.SharedElementType
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
    val jobCardSharedElementKey = JobCardSharedElementKey(
        jobId = job.jobId,
        jobTitle = job.jobTitle,
        companyTitle = job.companyTitle,
        imageCompany = job.companyLogo,
        jobDescription = job.description,
        salary = job.salary,
        location = job.location,
    )
    sharedTransitionScope.apply {
        AppBox (

            backgroundColor = Color.White,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
//                .background(Color.White, RoundedCornerShape(20.dp))
                .clickable(onClick = { onClick(job) }),


        ) {
            Column(
                modifier = Modifier
                   /*  .sharedElement(
                        state = rememberSharedContentState(key = jobCardSharedElementKey.getShareKey(SharedElementType.Bounds)),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = durationChange)
                        }
                    )*/
                    .padding(16.dp)

            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            modifier = Modifier.sharedElement(
                                state = rememberSharedContentState(key = jobCardSharedElementKey.getShareKey(SharedElementType.Title)),
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
                                state = rememberSharedContentState(key = jobCardSharedElementKey.getShareKey(SharedElementType.Description)),
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
                                state = rememberSharedContentState(key = jobCardSharedElementKey.getShareKey(SharedElementType.Company)),
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
                                state = rememberSharedContentState(key = jobCardSharedElementKey.getShareKey(SharedElementType.Image)),
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
                    modifier = Modifier.sharedElement(
                        state = rememberSharedContentState(key = jobCardSharedElementKey.getShareKey(SharedElementType.Salary)),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = durationChange)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    AppIcons.JobDetailDola.Generate()
                    Text(text = job.salary, color = Color.Black)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = jobCardSharedElementKey.getShareKey(SharedElementType.Location)),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = durationChange)
                                }
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        AppIcons.JobDetailLocation.Generate()
                        Text(text = job.location, color = Color.Black)
                    }

                    Row(
                        modifier = Modifier
                        /* .sharedElement(
                             state = rememberSharedContentState(key = jobCardSharedElementKey.getShareKey(SharedElementType.TimeWork)),
                             animatedVisibilityScope = animatedVisibilityScope,
                             boundsTransform = { _, _ ->
                                 tween(durationMillis = durationChange)
                             }
                         )*/,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        AppText(text = job.timeWork, color = Color.Gray)
                    }

                }

                Spacer(modifier = Modifier.height(8.dp))

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