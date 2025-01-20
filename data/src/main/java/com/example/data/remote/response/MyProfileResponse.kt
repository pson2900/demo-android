package com.example.data.remote.response

import com.example.domain.model.Attachment
import com.example.domain.model.Basic
import com.example.domain.model.Certification
import com.example.domain.model.Education
import com.example.domain.model.Experience
import com.example.domain.model.ExtraCurricular
import com.example.domain.model.Language
import com.example.domain.model.MyProfile
import com.example.domain.model.Preference
import com.example.domain.model.Reference
import com.example.domain.model.Skill

/**
 * Created by Phạm Sơn at 13:28/17/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
data class MyProfileResponse(
    val attachment: List<AttachmentResponse> = emptyList(),
    val basic: BasicResponse?,
    val certification: List<CertificationResponse> = emptyList(),
    val completionIndicator: Int?,
    val education: List<EducationResponse> = emptyList(),
    val experience: List<ExperienceResponse> = emptyList(),
    val extraCurricular: List<ExtraCurricularResponse> = emptyList(),
    val language: List<LanguageResponse> = emptyList(),
    val preference: PreferenceResponse?,
    val reference: List<ReferenceResponse> = emptyList(),
    val skill: List<SkillResponse> = emptyList(),
    val summary: String?
) {
    fun toDomain(): MyProfile {
        return MyProfile(
            attachment = attachment.map { it.toDomain() },
            basic = basic?.toDomain(),
            certification = certification.map { it.toDomain() },
            completionIndicator = completionIndicator,
            preference = preference?.toDomain(),
            education = education.map { it.toDomain() },
            experience = experience.map { it.toDomain() },
            extraCurricular = extraCurricular.map { it.toDomain() },
            language = language.map { it.toDomain() },
            reference = reference.map { it.toDomain() },
            skill = skill.map { it.toDomain() },
            summary = summary
        )
    }

}

data class AttachmentResponse(
    val attachmentFilename: String?,
    val attachmentType: String?,
    val id: Int?,
    val userId: Int?
) {
    fun toDomain(): Attachment {
        return Attachment(attachmentFilename, attachmentType, id, userId)
    }
}

data class BasicResponse(
    val birthDate: String?,
    val currentCity: String?,
    val firstName: String?,
    val gender: String?,
    val id: Int?,
    val isVietnamese: String?,
    val lastName: String?,
    val phone: String?,
    val photo: String?,
    val userId: Int?
) {
    fun toDomain(): Basic {
        return Basic(
            birthDate = birthDate,
            currentCity = currentCity,
            firstName = firstName,
            gender = gender,
            id = id,
            isVietnamese = isVietnamese,
            lastName = lastName,
            phone = phone,
            photo = photo,
            userId = userId
        )
    }
}

data class CertificationResponse(
    val certificationCredentialUrl: String?,
    val certificationIssueDate: String?,
    val certificationName: String?,
    val certificationOrganization: String?,
    val id: Int?,
    val userId: Int?
) {
    fun toDomain(): Certification {
        return Certification(
            certificationCredentialUrl = certificationCredentialUrl,
            certificationIssueDate = certificationIssueDate,
            certificationName = certificationName,
            certificationOrganization = certificationOrganization,
            id = id,
            userId = userId
        )
    }
}

data class EducationResponse(
    val educationDegree: Int?,
    val educationEnd: Any?,
    val educationGpa: Any?,
    val educationGpaSystem: Int?,
    val educationInstituteId: Any?,
    val educationInstituteName: String?,
    val educationInstituteType: String?,
    val educationMajor: String?,
    val educationStart: String?,
    val id: Int?,
    val userId: Int?
) {
    fun toDomain(): Education {
        return Education(
            educationDegree,
            educationEnd,
            educationGpa,
            educationGpaSystem,
            educationInstituteId,
            educationInstituteName,
            educationInstituteType,
            educationMajor,
            educationStart,
            id,
            userId
        )
    }
}

data class ExperienceResponse(
    val experienceCompanyName: String?,
    val experienceCurrent: Boolean?,
    val experienceDescription: String?,
    val experienceStart: String?,
    val experienceTitleOriginal: String?,
    val id: Int?,
    val userId: Int?
) {
    fun toDomain(): Experience {
        return Experience(experienceCompanyName, experienceCurrent, experienceDescription, experienceStart, experienceTitleOriginal, id, userId)
    }
}

data class ExtraCurricularResponse(
    val extraCurricularDescription: String?,
    val extraCurricularOrganization: String?,
    val extraCurricularRole: String?,
    val extraCurricularStart: String?,
    val id: Int?,
    val userId: Int?
) {
    fun toDomain(): ExtraCurricular {
        return ExtraCurricular(extraCurricularDescription, extraCurricularOrganization, extraCurricularRole, extraCurricularStart, id, userId)
    }
}

data class LanguageResponse(
    val id: Int?,
    val languageId: Int?,
    val languageLevel: Int?,
    val languageName: String?,
    val userId: Int?
) {
    fun toDomain(): Language {
        return Language(id, languageId, languageLevel, languageName, userId)
    }
}

data class PreferenceResponse(
    val desiredCareerPath: Int?,
    val desiredCity: List<Int> = emptyList(),
    val desiredEmploymentType: List<Int> = emptyList(),
    val desiredIndustry: List<Int> = emptyList(),
    val desiredJobTitle: String?,
    val desiredLocationType: List<Int> = emptyList(),
    val desiredSalary: Int?,
    val id: Int?,
    val relocation: Boolean?,
    val userId: Int?
) {
    fun toDomain(): Preference {
        return Preference(desiredCareerPath, desiredCity, desiredEmploymentType, desiredIndustry, desiredJobTitle, desiredLocationType, desiredSalary, id, relocation, userId)
    }
}

data class ReferenceResponse(
    val id: Int?,
    val referenceCompany: String?,
    val referenceEmail: String?,
    val referenceName: String?,
    val referencePhone: String?,
    val referenceTitle: String?,
    val userId: Int?
) {
    fun toDomain(): Reference {
        return Reference(id, referenceCompany, referenceEmail, referenceName, referencePhone, referenceTitle, userId)
    }
}

data class SkillResponse(
    val id: Int?,
    val skillName: String?,
    val skillYoe: Int?,
    val userId: Int?
) {
    fun toDomain(): Skill {
        return Skill(id, skillName, skillYoe, userId)
    }
}