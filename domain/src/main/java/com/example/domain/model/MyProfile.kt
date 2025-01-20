package com.example.domain.model

/**
 * Created by Phạm Sơn at 13:29/17/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
data class MyProfile(
    val attachment: List<Attachment?> = emptyList(),
    val basic: Basic?,
    val certification: List<Certification> = emptyList(),
    val completionIndicator: Int?,
    val education: List<Education> = emptyList(),
    val experience: List<Experience> = emptyList(),
    val extraCurricular: List<ExtraCurricular> = emptyList(),
    val language: List<Language> = emptyList(),
    val preference: Preference?,
    val reference: List<Reference> = emptyList(),
    val skill: List<Skill> = emptyList(),
    val summary: String?
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MyProfile) return false

        if (attachment != other.attachment) return false
        if (basic != other.basic) return false
        if (certification != other.certification) return false
        if (completionIndicator != other.completionIndicator) return false
        if (education != other.education) return false
        if (experience != other.experience) return false
        if (extraCurricular != other.extraCurricular) return false
        if (language != other.language) return false
        if (preference != other.preference) return false
        if (reference != other.reference) return false
        if (skill != other.skill) return false
        if (summary != other.summary) return false

        return true
    }

    override fun hashCode(): Int {
        var result = attachment.hashCode()
        result = 31 * result + (basic?.hashCode() ?: 0)
        result = 31 * result + certification.hashCode()
        result = 31 * result + (completionIndicator ?: 0)
        result = 31 * result + education.hashCode()
        result = 31 * result + experience.hashCode()
        result = 31 * result + extraCurricular.hashCode()
        result = 31 * result + language.hashCode()
        result = 31 * result + (preference?.hashCode() ?: 0)
        result = 31 * result + reference.hashCode()
        result = 31 * result + skill.hashCode()
        result = 31 * result + (summary?.hashCode() ?: 0)
        return result
    }


    override fun toString(): String {
        return "MyProfile(attachment=$attachment, basic=$basic, certification=$certification, completionIndicator=$completionIndicator, education=$education, experience=$experience, extraCurricular=$extraCurricular, language=$language, preference=$preference, reference=$reference, skill=$skill, summary=$summary)"
    }
}

data class Attachment(
    val attachmentFilename: String?,
    val attachmentType: String?,
    val id: Int?,
    val userId: Int?
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Attachment) return false

        if (attachmentFilename != other.attachmentFilename) return false
        if (attachmentType != other.attachmentType) return false
        if (id != other.id) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = attachmentFilename?.hashCode() ?: 0
        result = 31 * result + (attachmentType?.hashCode() ?: 0)
        result = 31 * result + (id ?: 0)
        result = 31 * result + (userId ?: 0)
        return result
    }


    override fun toString(): String {
        return "Attachment(attachmentFilename=$attachmentFilename, attachmentType=$attachmentType, id=$id, userId=$userId)"
    }
}

data class Basic(
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
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Basic) return false

        if (birthDate != other.birthDate) return false
        if (currentCity != other.currentCity) return false
        if (firstName != other.firstName) return false
        if (gender != other.gender) return false
        if (id != other.id) return false
        if (isVietnamese != other.isVietnamese) return false
        if (lastName != other.lastName) return false
        if (phone != other.phone) return false
        if (photo != other.photo) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = birthDate?.hashCode() ?: 0
        result = 31 * result + (currentCity?.hashCode() ?: 0)
        result = 31 * result + (firstName?.hashCode() ?: 0)
        result = 31 * result + (gender?.hashCode() ?: 0)
        result = 31 * result + (id ?: 0)
        result = 31 * result + (isVietnamese?.hashCode() ?: 0)
        result = 31 * result + (lastName?.hashCode() ?: 0)
        result = 31 * result + (phone?.hashCode() ?: 0)
        result = 31 * result + (photo?.hashCode() ?: 0)
        result = 31 * result + (userId ?: 0)
        return result
    }

    override fun toString(): String {
        return "Basic(birthDate=$birthDate, currentCity=$currentCity, firstName=$firstName, gender=$gender, id=$id, isVietnamese=$isVietnamese, lastName=$lastName, phone=$phone, photo=$photo, userId=$userId)"
    }
}

data class Certification(
    val certificationCredentialUrl: String?,
    val certificationIssueDate: String?,
    val certificationName: String?,
    val certificationOrganization: String?,
    val id: Int?,
    val userId: Int?
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Certification) return false

        if (certificationCredentialUrl != other.certificationCredentialUrl) return false
        if (certificationIssueDate != other.certificationIssueDate) return false
        if (certificationName != other.certificationName) return false
        if (certificationOrganization != other.certificationOrganization) return false
        if (id != other.id) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = certificationCredentialUrl?.hashCode() ?: 0
        result = 31 * result + (certificationIssueDate?.hashCode() ?: 0)
        result = 31 * result + (certificationName?.hashCode() ?: 0)
        result = 31 * result + (certificationOrganization?.hashCode() ?: 0)
        result = 31 * result + (id ?: 0)
        result = 31 * result + (userId ?: 0)
        return result
    }

    override fun toString(): String {
        return "Certification(certificationCredentialUrl=$certificationCredentialUrl, certificationIssueDate=$certificationIssueDate, certificationName=$certificationName, certificationOrganization=$certificationOrganization, id=$id, userId=$userId)"
    }
}

data class Education(
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
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Education) return false

        if (educationDegree != other.educationDegree) return false
        if (educationEnd != other.educationEnd) return false
        if (educationGpa != other.educationGpa) return false
        if (educationGpaSystem != other.educationGpaSystem) return false
        if (educationInstituteId != other.educationInstituteId) return false
        if (educationInstituteName != other.educationInstituteName) return false
        if (educationInstituteType != other.educationInstituteType) return false
        if (educationMajor != other.educationMajor) return false
        if (educationStart != other.educationStart) return false
        if (id != other.id) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = educationDegree ?: 0
        result = 31 * result + (educationEnd?.hashCode() ?: 0)
        result = 31 * result + (educationGpa?.hashCode() ?: 0)
        result = 31 * result + (educationGpaSystem ?: 0)
        result = 31 * result + (educationInstituteId?.hashCode() ?: 0)
        result = 31 * result + (educationInstituteName?.hashCode() ?: 0)
        result = 31 * result + (educationInstituteType?.hashCode() ?: 0)
        result = 31 * result + (educationMajor?.hashCode() ?: 0)
        result = 31 * result + (educationStart?.hashCode() ?: 0)
        result = 31 * result + (id ?: 0)
        result = 31 * result + (userId ?: 0)
        return result
    }


    override fun toString(): String {
        return "Education(educationDegree=$educationDegree, educationEnd=$educationEnd, educationGpa=$educationGpa, educationGpaSystem=$educationGpaSystem, educationInstituteId=$educationInstituteId, educationInstituteName=$educationInstituteName, educationInstituteType=$educationInstituteType, educationMajor=$educationMajor, educationStart=$educationStart, id=$id, userId=$userId)"
    }
}

data class Experience(
    val experienceCompanyName: String?,
    val experienceCurrent: Boolean?,
    val experienceDescription: String?,
    val experienceStart: String?,
    val experienceTitleOriginal: String?,
    val id: Int?,
    val userId: Int?
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Experience) return false

        if (experienceCompanyName != other.experienceCompanyName) return false
        if (experienceCurrent != other.experienceCurrent) return false
        if (experienceDescription != other.experienceDescription) return false
        if (experienceStart != other.experienceStart) return false
        if (experienceTitleOriginal != other.experienceTitleOriginal) return false
        if (id != other.id) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = experienceCompanyName?.hashCode() ?: 0
        result = 31 * result + (experienceCurrent?.hashCode() ?: 0)
        result = 31 * result + (experienceDescription?.hashCode() ?: 0)
        result = 31 * result + (experienceStart?.hashCode() ?: 0)
        result = 31 * result + (experienceTitleOriginal?.hashCode() ?: 0)
        result = 31 * result + (id ?: 0)
        result = 31 * result + (userId ?: 0)
        return result
    }


    override fun toString(): String {
        return "Experience(experienceCompanyName=$experienceCompanyName, experienceCurrent=$experienceCurrent, experienceDescription=$experienceDescription, experienceStart=$experienceStart, experienceTitleOriginal=$experienceTitleOriginal, id=$id, userId=$userId)"
    }
}

data class ExtraCurricular(
    val extraCurricularDescription: String?,
    val extraCurricularOrganization: String?,
    val extraCurricularRole: String?,
    val extraCurricularStart: String?,
    val id: Int?,
    val userId: Int?
)

data class Language(
    val id: Int?,
    val languageId: Int?,
    val languageLevel: Int?,
    val languageName: String?,
    val userId: Int?
){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Language) return false

        if (id != other.id) return false
        if (languageId != other.languageId) return false
        if (languageLevel != other.languageLevel) return false
        if (languageName != other.languageName) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (languageId ?: 0)
        result = 31 * result + (languageLevel ?: 0)
        result = 31 * result + (languageName?.hashCode() ?: 0)
        result = 31 * result + (userId ?: 0)
        return result
    }


    override fun toString(): String {
        return "Language(id=$id, languageId=$languageId, languageLevel=$languageLevel, languageName=$languageName, userId=$userId)"
    }
}

data class Preference(
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
){

    override fun toString(): String {
        return "Preference(desiredCareerPath=$desiredCareerPath, desiredCity=$desiredCity, desiredEmploymentType=$desiredEmploymentType, desiredIndustry=$desiredIndustry, desiredJobTitle=$desiredJobTitle, desiredLocationType=$desiredLocationType, desiredSalary=$desiredSalary, id=$id, relocation=$relocation, userId=$userId)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Preference) return false

        if (desiredCareerPath != other.desiredCareerPath) return false
        if (desiredCity != other.desiredCity) return false
        if (desiredEmploymentType != other.desiredEmploymentType) return false
        if (desiredIndustry != other.desiredIndustry) return false
        if (desiredJobTitle != other.desiredJobTitle) return false
        if (desiredLocationType != other.desiredLocationType) return false
        if (desiredSalary != other.desiredSalary) return false
        if (id != other.id) return false
        if (relocation != other.relocation) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = desiredCareerPath ?: 0
        result = 31 * result + desiredCity.hashCode()
        result = 31 * result + desiredEmploymentType.hashCode()
        result = 31 * result + desiredIndustry.hashCode()
        result = 31 * result + (desiredJobTitle?.hashCode() ?: 0)
        result = 31 * result + desiredLocationType.hashCode()
        result = 31 * result + (desiredSalary ?: 0)
        result = 31 * result + (id ?: 0)
        result = 31 * result + (relocation?.hashCode() ?: 0)
        result = 31 * result + (userId ?: 0)
        return result
    }
}

data class Reference(
    val id: Int?,
    val referenceCompany: String?,
    val referenceEmail: String?,
    val referenceName: String?,
    val referencePhone: String?,
    val referenceTitle: String?,
    val userId: Int?
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Reference) return false

        if (id != other.id) return false
        if (referenceCompany != other.referenceCompany) return false
        if (referenceEmail != other.referenceEmail) return false
        if (referenceName != other.referenceName) return false
        if (referencePhone != other.referencePhone) return false
        if (referenceTitle != other.referenceTitle) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (referenceCompany?.hashCode() ?: 0)
        result = 31 * result + (referenceEmail?.hashCode() ?: 0)
        result = 31 * result + (referenceName?.hashCode() ?: 0)
        result = 31 * result + (referencePhone?.hashCode() ?: 0)
        result = 31 * result + (referenceTitle?.hashCode() ?: 0)
        result = 31 * result + (userId ?: 0)
        return result
    }


    override fun toString(): String {
        return "Reference(id=$id, referenceCompany=$referenceCompany, referenceEmail=$referenceEmail, referenceName=$referenceName, referencePhone=$referencePhone, referenceTitle=$referenceTitle, userId=$userId)"
    }
}

data class Skill(
    val id: Int?,
    val skillName: String?,
    val skillYoe: Int?,
    val userId: Int?
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Skill) return false

        if (id != other.id) return false
        if (skillName != other.skillName) return false
        if (skillYoe != other.skillYoe) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (skillName?.hashCode() ?: 0)
        result = 31 * result + (skillYoe ?: 0)
        result = 31 * result + (userId ?: 0)
        return result
    }


    override fun toString(): String {
        return "Skill(id=$id, skillName=$skillName, skillYoe=$skillYoe, userId=$userId)"
    }
}