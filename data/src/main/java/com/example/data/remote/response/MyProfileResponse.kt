package com.example.data.remote.response

import com.example.domain.model.Attachment
import com.example.domain.model.Basic
import com.example.domain.model.Certification
import com.example.domain.model.Characteristic
import com.example.domain.model.DesiredCity
import com.example.domain.model.DesiredEmploymentType
import com.example.domain.model.DesiredIndustry
import com.example.domain.model.DesiredLocationType
import com.example.domain.model.Education
import com.example.domain.model.Experience
import com.example.domain.model.ExternalDoc
import com.example.domain.model.ExtraCurricular
import com.example.domain.model.Hobby
import com.example.domain.model.Language
import com.example.domain.model.MyProfile
import com.example.domain.model.Preference
import com.example.domain.model.Profile
import com.example.domain.model.Reference
import com.example.domain.model.Skills

/**
 * Created by Phạm Sơn at 13:28/17/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
data class MyProfileResponse(
    val attachment: List<AttachmentResponse> = emptyList(), // cv dinh kem
    val basic: BasicResponse?,// thong tin lien he
    val certification: List<CertificationResponse> = emptyList(), // chung chi
    val characteristic: List<CharacteristicResponse> = emptyList(),// tinh cach
    val completionIndicator: Int?,
    val education: List<EducationResponse> = emptyList(), // trinh do hoc van
    val experience: List<ExperienceResponse> = emptyList(), // kinh nghiem lam viec
    val externalDoc: List<ExternalDocResponse> = emptyList(), // link du an ca nhan
    val extraCurricular: List<ExtraCurricularResponse> = emptyList(),  // hd ngoai khoa
    val hobby: List<HobbyResponse> = emptyList(), // so thich
    val language: List<LanguageResponse> = emptyList(), // ngoai ngu
    val preference: PreferenceResponse?, // tieu chi cong viec
    val reference: List<ReferenceResponse> = emptyList(), // nguoi than chieu
    val skill: List<SkillsResponse> = emptyList(),
    val summary: String?
) {
    fun toDomain(): MyProfile {
        val profiles = mutableListOf<Profile>()
        profiles.add(Profile.BasicProfile(basic?.toDomain()))
        profiles.add(Profile.AttachmentProfile(attachment.map { it.toDomain() }))
        profiles.add(Profile.CertificationProfile(certification.map { it.toDomain() }))
        profiles.add(Profile.CharacteristicProfile(characteristic.map { it.toDomain() }))
        profiles.add(Profile.EducationProfile(education.map { it.toDomain() }))
        profiles.add(Profile.ExperienceProfile(experience.map { it.toDomain() }))
        profiles.add(Profile.ExternalDocProfile(externalDoc.map { it.toDomain() }))
        profiles.add(Profile.ExtraCurricularProfile(extraCurricular.map { it.toDomain() }))
        profiles.add(Profile.HobbyProfile(hobby.map { it.toDomain() }))
        profiles.add(Profile.LanguageProfile(language.map { it.toDomain() }))
        profiles.add(Profile.PreferenceProfile(preference?.toDomain()))
        profiles.add(Profile.ReferenceProfile(reference.map { it.toDomain() }))

        return MyProfile(
            completionIndicator = completionIndicator,
            skill = skill.map { it.toDomain() },
            summary = summary,
            profiles = profiles
        )
    }
}

data class AttachmentResponse(
    val fileName: String?,
    val fileUrl: String?,
    val id: Int?,
    val userId: Int?
) {
    fun toDomain(): Attachment {
        return Attachment(
            fileName = fileName,
            fileUrl = fileUrl,
            id = id,
            userId = userId
        )
    }
}

data class BasicResponse(
    val birthDate: String?,
    val currentCity: String?,
    val firstName: String?,
    val gender: String?,
    val isVietnamese: Boolean?,
    val lastName: String?,
    val phone: String?,
    val photo: String?
) {
    fun toDomain(): Basic {
        return Basic(
            birthDate = birthDate,
            currentCity = currentCity,
            firstName = firstName,
            gender = gender, isVietnamese = isVietnamese,
            lastName = lastName,
            phone = phone,
            photo = photo
        )
    }
}

data class CertificationResponse(
    val credentialUrl: String?,
    val expireDate: String?,
    val id: Int?,
    val issueDate: String?,
    val name: String?,
    val organization: String?
) {
    fun toDomain(): Certification {
        return Certification(
            credentialUrl = credentialUrl,
            expireDate = expireDate,
            id = id,
            issueDate = issueDate,
            name = name,
            organization = organization
        )
    }
}

data class CharacteristicResponse(
    val characteristic: String?,
    val id: Int?,
    val userId: Int?
) {
    fun toDomain(): Characteristic {
        return Characteristic(characteristic = characteristic, id = id, userId = userId)
    }
}

data class EducationResponse(
    val degree: Int?,
    val description: String?,
    val endDate: String?,
    val favoriteSubject: String?,
    val gpa: Double?,
    val gpaSystem: Int?,
    val id: Int?,
    val instituteName: String?,
    val instituteType: String?,
    val major: String?,
    val startDate: String?
) {
    fun toDomain(): Education {
        return Education(
            degree, description, endDate, favoriteSubject, gpa, gpaSystem, id, instituteName, instituteType, major, startDate
        )

    }
}

data class ExperienceResponse(
    val companyName: String?,
    val description: String?,
    val employmentType: Int?,
    val endDate: String?,
    val id: Int?,
    val isCurrent: Boolean?,
    val locationType: Int?,
    val skills: List<SkillsResponse>?,
    val startDate: String?,
    val titleOriginal: String?
) {
    fun toDomain(): Experience {
        return Experience(companyName, description, employmentType, endDate, id, isCurrent, locationType, skills?.map { it.toDomain() } ?: emptyList(), startDate, titleOriginal)
    }
}

data class ExternalDocResponse(
    val docLink: String?,
    val docName: String?,
    val id: Int?
) {
    fun toDomain(): ExternalDoc {
        return ExternalDoc(docLink, docName, id)
    }
}

data class ExtraCurricularResponse(
    val description: String?,
    val endDate: String?,
    val id: Int?,
    val organization: String?,
    val role: String?,
    val skills: List<SkillsResponse>?,
    val startDate: String?,
    val type: Int?
) {
    fun toDomain(): ExtraCurricular {
        return ExtraCurricular(description, endDate, id, organization, role, skills?.map { it.toDomain() } ?: emptyList(), startDate, type)
    }
}

data class HobbyResponse(
    val id: Int?,
    val name: String?
) {
    fun toDomain(): Hobby {
        return Hobby(id, name)
    }
}

data class LanguageResponse(
    val id: Int?,
    val level: Int?,
    val name: String?
) {
    fun toDomain(): Language {
        return Language(id, level, name)
    }
}

data class PreferenceResponse(
    val desiredCareerPath: Int?,
    val desiredCity: List<DesiredCityResponse> = emptyList(),
    val desiredEmploymentType: List<DesiredEmploymentTypeResponse> = emptyList(),
    val desiredIndustry: List<DesiredIndustryResponse> = emptyList(),
    val desiredJobTitle: String?,
    val desiredLocationType: List<DesiredLocationTypeResponse> = emptyList(),
    val desiredSalary: Int?,
    val relocation: Boolean?
) {
    fun toDomain(): Preference {
        return Preference(
            desiredCareerPath,
            desiredCity.map { it.toDomain() },
            desiredEmploymentType.map { it.toDomain() },
            desiredIndustry.map { it.toDomain() },
            desiredJobTitle,
            desiredLocationType.map { it.toDomain() },
            desiredSalary,
            relocation)
    }
}

data class ReferenceResponse(
    val company: String?,
    val email: String?,
    val id: Int?,
    val name: String?,
    val phone: String?,
    val title: String?
) {
    fun toDomain(): Reference {
        return Reference(company, email, id, name, phone, title)
    }
}

data class SkillsResponse(
    val id: Int?,
    val name: String?,
    val yoe: Int?
) {
    fun toDomain(): Skills {
        return Skills(id, name, yoe)
    }
}

data class DesiredCityResponse(
    val id: Int?,
    val name: String?
) {
    fun toDomain(): DesiredCity {
        return DesiredCity(id, name)
    }
}

data class DesiredEmploymentTypeResponse(
    val id: Int?,
    val name: String?
) {
    fun toDomain(): DesiredEmploymentType {
        return DesiredEmploymentType(id, name)
    }
}

data class DesiredIndustryResponse(
    val id: Int?,
    val name: String?
) {
    fun toDomain(): DesiredIndustry {
        return DesiredIndustry(id, name)
    }
}

data class DesiredLocationTypeResponse(
    val id: Int?,
    val name: String?
) {
    fun toDomain(): DesiredLocationType {
        return DesiredLocationType(id, name)
    }

}