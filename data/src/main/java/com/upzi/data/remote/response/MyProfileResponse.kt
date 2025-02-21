package com.upzi.data.remote.response

import com.upzi.domain.ifNotEmpty
import com.upzi.domain.ifNotNull
import com.upzi.domain.model.Attachment
import com.upzi.domain.model.Basic
import com.upzi.domain.model.Certification
import com.upzi.domain.model.Characteristic
import com.upzi.domain.model.DesiredCity
import com.upzi.domain.model.DesiredEmploymentType
import com.upzi.domain.model.DesiredIndustry
import com.upzi.domain.model.DesiredLocationType
import com.upzi.domain.model.Education
import com.upzi.domain.model.Experience
import com.upzi.domain.model.ExternalDoc
import com.upzi.domain.model.ExtraCurricular
import com.upzi.domain.model.Hobby
import com.upzi.domain.model.Language
import com.upzi.domain.model.MyProfile
import com.upzi.domain.model.Preference
import com.upzi.domain.model.Profile
import com.upzi.domain.model.Reference
import com.upzi.domain.model.Skills

/**
 * Created by Phạm Sơn at 13:28/17/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
data class MyProfileResponse(
    val attachment: List<AttachmentResponse>? = emptyList(), // cv dinh kem
    val basic: BasicResponse?,// thong tin lien he
    val certification: List<CertificationResponse>? = emptyList(), // chung chi
    val characteristic: List<CharacteristicResponse>? = emptyList(),// tinh cach
    val completionIndicator: Int?,
    val education: List<EducationResponse>? = emptyList(), // trinh do hoc van
    val experience: List<ExperienceResponse>? = emptyList(), // kinh nghiem lam viec
    val externalDoc: List<ExternalDocResponse>? = emptyList(), // link du an ca nhan
    val extraCurricular: List<ExtraCurricularResponse>? = emptyList(),  // hd ngoai khoa
    val hobby: List<HobbyResponse>? = emptyList(), // so thich
    val language: List<LanguageResponse>? = emptyList(), // ngoai ngu
    val preference: PreferenceResponse?, // tieu chi cong viec
    val reference: List<ReferenceResponse>? = emptyList(), // nguoi than chieu
    val skill: List<SkillsResponse>? = emptyList(),
    val summary: String?
) {
    fun toDomain(): MyProfile {
        val profiles = mutableListOf<Profile>()
        basic.ifNotNull {
            profiles.add(Profile.BasicProfile(it.toDomain()))
        }
        attachment.ifNotEmpty {
            profiles.add(Profile.AttachmentProfile(it.map { value -> value.toDomain() }))
        }
        certification.ifNotEmpty {
            profiles.add(Profile.CertificationProfile(it.map { value -> value.toDomain() }))
        }
        characteristic.ifNotEmpty {
            profiles.add(Profile.CharacteristicProfile(it.map { value -> value.toDomain() }))
        }
        education.ifNotEmpty {
            profiles.add(Profile.EducationProfile(it.map { value -> value.toDomain() }))
        }
        experience.ifNotEmpty {
            profiles.add(Profile.ExperienceProfile(it.map { value -> value.toDomain() }))
        }
        externalDoc.ifNotEmpty {
            profiles.add(Profile.ExternalDocProfile(it.map { value -> value.toDomain() }))
        }
        extraCurricular.ifNotEmpty {
            profiles.add(Profile.ExtraCurricularProfile(it.map { value -> value.toDomain() }))
        }
        hobby.ifNotEmpty {
            profiles.add(Profile.HobbyProfile(it.map { value -> value.toDomain() }))
        }
        language.ifNotEmpty {
            profiles.add(Profile.LanguageProfile(it.map { value -> value.toDomain() }))
        }
        preference.ifNotNull {
            profiles.add(Profile.PreferenceProfile(it.toDomain()))
        }
        reference.ifNotEmpty {
            profiles.add(Profile.ReferenceProfile(it.map { value -> value.toDomain() }))
        }


        return MyProfile(
            completionIndicator = completionIndicator,
            skill = skill?.map { it.toDomain() } ?: emptyList(),
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
            relocation
        )
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