package com.example.data.remote.response

import com.example.domain.model.UserProfile

/**
 * Created by Phạm Sơn at 10:17/29/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
data class UserProfileResponse(
    var userId: Int? = null,
    var resumeId: Int? = null,
    var avatar: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var fullName: String? = null,
    var jobLevelId: Int? = null,
    var jobLevelName: String? = null,
    var jobTitle: String? = null,
    var isDownload: Boolean? = false,
    var highestDegreeId: Int? = null,
    var degreeLevelName: String? = null,
    var yearsExperience: Int? = null,
    var lastCompany: String? = null,
    var newGraduate: Boolean? = false,
    var currentSalary: Int? = 0,
    var currentIndustries: MutableList<Int>? = mutableListOf()
) {
    fun toDomain(): UserProfile{
        return UserProfile()
    }
}