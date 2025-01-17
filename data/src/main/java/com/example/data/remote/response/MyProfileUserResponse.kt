package com.example.data.remote.response

import com.example.domain.model.Activity
import com.example.domain.model.BasicInfo
import com.example.domain.model.Certification
import com.example.domain.model.Characteristic
import com.example.domain.model.Education
import com.example.domain.model.Hobby
import com.example.domain.model.MyProfileUser
import com.example.domain.model.Skill
import com.example.domain.model.WorkingHistory

/**
 * Created by Phạm Sơn at 13:28/17/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
data class MyProfileUserResponse(
    val activities: List<ActivityResponse>?,
    val basicInfo: BasicInfoResponse?,
    val certifications: List<CertificationResponse>?,
    val characteristics: List<CharacteristicResponse>?,
    val educations: List<EducationResponse>?,
    val hobbies: List<HobbyResponse>?,
    val workingHistories: List<WorkingHistoryResponse>?
) {
    fun toDomain(): MyProfileUser {
        return MyProfileUser(
            activities = activities?.map {
                it.toDomain()
            }, basicInfo = basicInfo?.toDomain(),
            certifications = certifications?.map {
                it.toDomain()
            },
            characteristics = characteristics?.map {
                Characteristic(it.description, it.trait)
            },
            educations = educations?.map {
                it.toDomain()
            },
            hobbies = hobbies?.map {
                it.toDomain()
            },
            workingHistories = workingHistories?.map {
                it.toDomain()
            })

    }
}

data class ActivityResponse(
    val activity: String?,
    val description: String?,
    val endDate: String?,
    val isCurrent: Int?,
    val organization: String?,
    val skills: List<Skill?>?,
    val startDate: String?,
    val title: String?
) {
    fun toDomain(): Activity {
        return Activity(activity, description, endDate, isCurrent, organization, skills?.map {
            Skill(it?.rate, it?.skillId, it?.skillName)
        }, startDate, title)
    }
}

data class BasicInfoResponse(
    val address: String?,
    val avatar: String?,
    val birthday: String?,
    val cityId: Int?,
    val countryId: Int?,
    val districtId: Int?,
    val email: String?,
    val firstName: String?,
    val genderId: Int?,
    val highestDegreeId: Int?,
    val jobLevelId: Int?,
    val jobTitle: String?,
    val lastName: String?,
    val maritalStatusId: Int?,
    val nationalityId: Int?,
    val phone: String?,
    val userId: Int?,
    val yearsExperience: Int?
) {
    fun toDomain(): BasicInfo {
        return BasicInfo(
            address,
            avatar,
            birthday,
            cityId,
            countryId,
            districtId,
            email,
            firstName,
            genderId,
            highestDegreeId,
            jobLevelId,
            jobTitle,
            lastName,
            maritalStatusId,
            nationalityId,
            phone,
            userId,
            yearsExperience
        )
    }
}

data class CertificationResponse(
    val certification: String?,
    val endDate: String?,
    val linkCertification: String?,
    val organization: String?,
    val organizationId: Int?,
    val startDate: String?
) {
    fun toDomain(): Certification {
        return Certification(certification, endDate, linkCertification, organization, organizationId, startDate)
    }
}

data class CharacteristicResponse(
    val description: String?,
    val trait: String?
) {
    fun toDomain(): Characteristic {
        return Characteristic(description, trait)
    }
}

data class EducationResponse(
    val countryId: Int?,
    val description: String?,
    val educationOrder: Int?,
    val endDate: String?,
    val highestDegreeId: Int?,
    val major: String?,
    val schoolId: Int?,
    val schoolName: String?,
    val startDate: String?
) {
    fun toDomain(): Education {
        return Education(countryId, description, educationOrder, endDate, highestDegreeId, major, schoolId, schoolName, startDate)
    }
}

data class HobbyResponse(
    val description: String?,
    val hobbyId: Int?,
    val hobbyName: String?
) {
    fun toDomain(): Hobby {
        return Hobby(description, hobbyId, hobbyName)
    }
}

data class WorkingHistoryResponse(
    val cityId: Int?,
    val companyId: Int?,
    val companyLogo: String?,
    val companyName: String?,
    val countryId: Int?,
    val description: String?,
    val endDate: String?,
    val experienceOrder: Int?,
    val industryId: Int?,
    val isCurrent: Int?,
    val jobLevelId: Int?,
    val jobTitle: String?,
    val skills: List<SkillResponse>?,
    val startDate: String?
) {
    fun toDomain(): WorkingHistory {
        return WorkingHistory(cityId, companyId, companyLogo, companyName, countryId, description, endDate, experienceOrder, industryId, isCurrent, jobLevelId, jobTitle, skills?.map {
            Skill(it.rate, it.skillId, it.skillName)
        }, startDate)
    }
}

data class SkillResponse(
    val rate: Int?,
    val skillId: Int?,
    val skillName: String?
) {
    fun toDomain(): Skill {
        return Skill(rate, skillId, skillName)
    }

}