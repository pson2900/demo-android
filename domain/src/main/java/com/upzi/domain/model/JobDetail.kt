package com.upzi.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
/**
 * Created by Phạm Sơn at 14:51/12/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Parcelize
class JobDetail(
    val jobId: Int, val jobTitle: String, val companyTitle: String,
    val salary: String, val location: String,
    val companyLogo: Int,
    val description: String,
    val timeWork: String = "Thực tập 4 - 6 tháng"
): Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JobDetail

        if (jobId != other.jobId) return false
        if (jobTitle != other.jobTitle) return false
        if (companyTitle != other.companyTitle) return false
        if (salary != other.salary) return false
        if (location != other.location) return false
        if (companyLogo != other.companyLogo) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = jobId
        result = 31 * result + jobTitle.hashCode()
        result = 31 * result + companyTitle.hashCode()
        result = 31 * result + salary.hashCode()
        result = 31 * result + location.hashCode()
        result = 31 * result + companyLogo
        result = 31 * result + description.hashCode()
        return result
    }

    override fun toString(): String {
        return "JobDetail(jobId=$jobId, jobTitle='$jobTitle', companyTitle='$companyTitle', salary='$salary', location='$location', companyLogo=$companyLogo, description='$description')"
    }
}