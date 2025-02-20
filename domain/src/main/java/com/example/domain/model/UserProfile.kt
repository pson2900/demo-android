package com.example.domain.model


/**
 * Created by Phạm Sơn at 14:19/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
data class UserProfile(
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
    var currentIndustries: MutableList<Int>? = mutableListOf(),
    var email: String?= null
)  {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserProfile

        if (userId != other.userId) return false
        if (resumeId != other.resumeId) return false
        if (avatar != other.avatar) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (fullName != other.fullName) return false
        if (jobLevelId != other.jobLevelId) return false
        if (jobLevelName != other.jobLevelName) return false
        if (jobTitle != other.jobTitle) return false
        if (isDownload != other.isDownload) return false
        if (degreeLevelName != other.degreeLevelName) return false
        if (yearsExperience != other.yearsExperience) return false
        if (highestDegreeId != other.highestDegreeId) return false
        if (lastCompany != other.lastCompany) return false
        if (newGraduate != other.newGraduate) return false
        if (currentSalary != other.currentSalary) return false
        return currentIndustries == other.currentIndustries
    }

    override fun hashCode(): Int {
        var result = userId ?: 0
        result = 31 * result + (resumeId ?: 0)
        result = 31 * result + (avatar?.hashCode() ?: 0)
        result = 31 * result + (firstName?.hashCode() ?: 0)
        result = 31 * result + (lastName?.hashCode() ?: 0)
        result = 31 * result + (fullName?.hashCode() ?: 0)
        result = 31 * result + (jobLevelId ?: 0)
        result = 31 * result + (jobLevelName?.hashCode() ?: 0)
        result = 31 * result + (jobTitle?.hashCode() ?: 0)
        result = 31 * result + (isDownload?.hashCode() ?: 0)
        result = 31 * result + (degreeLevelName?.hashCode() ?: 0)
        result = 31 * result + (yearsExperience ?: 0)
        result = 31 * result + (highestDegreeId ?: 0)
        result = 31 * result + (lastCompany?.hashCode() ?: 0)
        result = 31 * result + (newGraduate?.hashCode() ?: 0)
        result = 31 * result + (currentSalary ?: 0)
        result = 31 * result + (currentIndustries?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return ""
    }

}