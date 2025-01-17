package com.example.domain.model

/**
 * Created by Phạm Sơn at 13:29/17/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
data class MyProfileUser(
    val activities: List<Activity>?,
    val basicInfo: BasicInfo?,
    val certifications: List<Certification>?,
    val characteristics: List<Characteristic>?,
    val educations: List<Education>?,
    val hobbies: List<Hobby>?,
    val workingHistories: List<WorkingHistory>?
) {
    override fun toString(): String {
        return "MyProfileUser(activities=$activities, basicInfo=$basicInfo, certifications=$certifications, characteristics=$characteristics, educations=$educations, hobbies=$hobbies, workingHistories=$workingHistories)"
    }
}

data class Activity(
    val activity: String?,
    val description: String?,
    val endDate: String?,
    val isCurrent: Int?,
    val organization: String?,
    val skills: List<Skill?>?,
    val startDate: String?,
    val title: String?
){
    override fun toString(): String {
        return "Activity(activity=$activity, description=$description, endDate=$endDate, isCurrent=$isCurrent, organization=$organization, skills=$skills, startDate=$startDate, title=$title)"
    }
}

data class BasicInfo(
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
){
    override fun toString(): String {
        return "BasicInfo(address=$address, avatar=$avatar, birthday=$birthday, cityId=$cityId, countryId=$countryId, districtId=$districtId, email=$email, firstName=$firstName, genderId=$genderId, highestDegreeId=$highestDegreeId, jobLevelId=$jobLevelId, jobTitle=$jobTitle, lastName=$lastName, maritalStatusId=$maritalStatusId, nationalityId=$nationalityId, phone=$phone, userId=$userId, yearsExperience=$yearsExperience)"
    }
}

data class Certification(
    val certification: String?,
    val endDate: String?,
    val linkCertification: String?,
    val organization: String?,
    val organizationId: Int?,
    val startDate: String?
) {
    override fun toString(): String {
        return "Certification(certification=$certification, endDate=$endDate, linkCertification=$linkCertification, organization=$organization, organizationId=$organizationId, startDate=$startDate)"
    }
}

data class Characteristic(
    val description: String?,
    val trait: String?
){
    override fun toString(): String {
        return "Characteristic(description=$description, trait=$trait)"
    }
}

data class Education(
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
    override fun toString(): String {
        return "Education(countryId=$countryId, description=$description, educationOrder=$educationOrder, endDate=$endDate, highestDegreeId=$highestDegreeId, major=$major, schoolId=$schoolId, schoolName=$schoolName, startDate=$startDate)"
    }
}

data class Hobby(
    val description: String?,
    val hobbyId: Int?,
    val hobbyName: String?
) {
    override fun toString(): String {
        return "Hobby(description=$description, hobbyId=$hobbyId, hobbyName=$hobbyName)"
    }
}

data class WorkingHistory(
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
    val skills: List<Skill>?,
    val startDate: String?
) {
    override fun toString(): String {
        return "WorkingHistory(cityId=$cityId, companyId=$companyId, companyLogo=$companyLogo, companyName=$companyName, countryId=$countryId, description=$description, endDate=$endDate, experienceOrder=$experienceOrder, industryId=$industryId, isCurrent=$isCurrent, jobLevelId=$jobLevelId, jobTitle=$jobTitle, skills=$skills, startDate=$startDate)"
    }
}

data class Skill(
    val rate: Int?,
    val skillId: Int?,
    val skillName: String?
) {
    override fun toString(): String {
        return "Skill(rate=$rate, skillId=$skillId, skillName=$skillName)"
    }
}