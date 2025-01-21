package com.example.domain.model

/**
 * Created by Phạm Sơn at 13:29/17/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
sealed class Profile() {
    class AttachmentProfile(val attachment: List<Attachment>) : Profile()
    class BasicProfile(val basic: Basic?) : Profile()
    class CertificationProfile(val certification: List<Certification>) : Profile()
    class CharacteristicProfile(val characteristic: List<Characteristic>) : Profile()
    class EducationProfile(val education: List<Education>) : Profile()
    class ExperienceProfile(val experience: List<Experience>) : Profile()
    class ExternalDocProfile(val externalDoc: List<ExternalDoc>) : Profile()
    class ExtraCurricularProfile(val extraCurricular: List<ExtraCurricular>) : Profile()
    class HobbyProfile(val hobby: List<Hobby>) : Profile()
    class LanguageProfile(val language: List<Language>) : Profile()
    class PreferenceProfile(val preference: Preference?) : Profile()
    class ReferenceProfile(val reference: List<Reference>) : Profile()
}

data class MyProfile(
    val completionIndicator: Int?,
    val skill: List<Skills> = emptyList(),
    val summary: String?,
    var profiles: List<Profile>
) {

}

data class Attachment(
    val fileName: String?,
    val fileUrl: String?,
    val id: Int?,
    val userId: Int?
)

data class Basic(
    val birthDate: String?,
    val currentCity: String?,
    val firstName: String?,
    val gender: String?,
    val isVietnamese: Boolean?,
    val lastName: String?,
    val phone: String?,
    val photo: String?
)

data class Certification(
    val credentialUrl: String?,
    val expireDate: String?,
    val id: Int?,
    val issueDate: String?,
    val name: String?,
    val organization: String?
)

data class Characteristic(
    val characteristic: String?,
    val id: Int?,
    val userId: Int?
)

data class Education(
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
)

data class Experience(
    val companyName: String?,
    val description: String?,
    val employmentType: Int?,
    val endDate: String?,
    val id: Int?,
    val isCurrent: Boolean?,
    val locationType: Int?,
    val skills: List<Skills> = emptyList(),
    val startDate: String?,
    val titleOriginal: String?
)

data class ExternalDoc(
    val docLink: String?,
    val docName: String?,
    val id: Int?
)

data class ExtraCurricular(
    val description: String?,
    val endDate: String?,
    val id: Int?,
    val organization: String?,
    val role: String?,
    val skills: List<Skills> = emptyList(),
    val startDate: String?,
    val type: Int?
)

data class Hobby(
    val id: Int?,
    val name: String?
)

data class Language(
    val id: Int?,
    val level: Int?,
    val name: String?
)

data class Preference(
    val desiredCareerPath: Int?,
    val desiredCity: List<DesiredCity> = emptyList(),
    val desiredEmploymentType: List<DesiredEmploymentType> = emptyList(),
    val desiredIndustry: List<DesiredIndustry> = emptyList(),
    val desiredJobTitle: String?,
    val desiredLocationType: List<DesiredLocationType> = emptyList(),
    val desiredSalary: Int?,
    val relocation: Boolean?
)

data class Reference(
    val company: String?,
    val email: String?,
    val id: Int?,
    val name: String?,
    val phone: String?,
    val title: String?
)

data class Skills(
    val id: Int?,
    val name: String?,
    val yoe: Int?
)

data class DesiredCity(
    val id: Int?,
    val name: String?
)

data class DesiredEmploymentType(
    val id: Int?,
    val name: String?
)

data class DesiredIndustry(
    val id: Int?,
    val name: String?
)

data class DesiredLocationType(
    val id: Int?,
    val name: String?
)