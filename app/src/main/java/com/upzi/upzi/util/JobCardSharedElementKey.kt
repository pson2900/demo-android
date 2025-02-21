/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.upzi.upzi.util

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.ui.unit.IntSize

data class JobCardSharedElementKey(
    val jobId: Int,
    val jobTitle: String,
    val companyTitle: String,
    val imageCompany: Int,
    val jobDescription: String,
    val salary: String,
    val location: String,
    val timeWork: String = "4 - 6 Tháng"
){
    companion object{
        fun JobCardSharedElementKey.getShareKey(type: SharedElementType): String {
            return when(type){
                SharedElementType.Bounds -> "bounds/${jobId}"
                SharedElementType.Image -> "image/${imageCompany}"
                SharedElementType.Title -> "title/${jobTitle}"
                SharedElementType.Description -> "description/${jobDescription}"
                SharedElementType.Company -> "company/${companyTitle}"
                SharedElementType.Salary -> "salary/${salary}"
                SharedElementType.Location -> "location/${location}"
                SharedElementType.TimeWork -> "timeWork/${timeWork}"
            }
        }
    }
}

enum class SharedElementType {
    Bounds,
    Image,
    Title,
    Description,
    Company,
    Salary,
    Location,
    TimeWork
}

object FilterSharedElementKey

var durationChange = 600

fun getEnterTransition(initialSize: IntSize, targetSize: IntSize): EnterTransition {
    var enterTransition: EnterTransition = fadeIn(animationSpec = tween(300))
    if(initialSize != IntSize.Zero && targetSize != IntSize.Zero) {
        enterTransition = enterTransition + scaleIn(
            animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing),
            initialScale = 0.3f
        )
    }
    return enterTransition
}

fun getExitTransition(initialSize: IntSize, targetSize: IntSize): ExitTransition {
    var exitTransition: ExitTransition = fadeOut(animationSpec = tween(300))
    if(initialSize != IntSize.Zero && targetSize != IntSize.Zero) {
        exitTransition = exitTransition + scaleOut(
            animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing),
            targetScale = 0.3f
        )
    }
    return exitTransition
}