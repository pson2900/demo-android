package com.example.data.repository

import com.example.data.remote.ErrorMapper
import com.example.data.remote.response.MyProfileResponse
import com.example.domain.model.MyProfile
import com.example.domain.repository.MyProfileRepository
import com.google.gson.Gson

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Created by Phạm Sơn at 12:23/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class MyProfileRepositoryImpl(private val errorMapper: ErrorMapper) : MyProfileRepository {

    override suspend fun getMyProfile(): Flow<MyProfile> {
        val gson = Gson()
        val json = """
           {
            "basic": {
              "id": 1,
              "userId": 1,
              "firstName": "John",
              "lastName": "Doe",
              "photo": "https://example.com/photo.jpg",
              "birthDate": "1990-01-01",
              "gender": "Male",
              "phone": "1234567890",
              "currentCity": "New York",
              "isVietnamese": "No"
            },
            "preference": {
              "id": 1,
              "userId": 1,
              "desiredSalary": 50000000,
              "desiredJobTitle": "Software Engineer",
              "desiredCareerPath": 1,
              "desiredCity": [
                1,
                2
              ],
              "relocation": true,
              "desiredEmploymentType": [
                1,
                2
              ],
              "desiredLocationType": [
                1
              ],
              "desiredIndustry": [
                1,
                2,
                3
              ]
            },
            "summary": "A highly motivated software engineer with 5+ years of experience.",
            "education": [
              {
                "id": 1,
                "userId": 1,
                "educationInstituteName": "University of Technology",
                "educationInstituteId": null,
                "educationInstituteType": "Public",
                "educationMajor": "Computer Science",
                "educationStart": "2008-09-01T00:00:00Z",
                "educationEnd": null,
                "educationDegree": 1,
                "educationGpa": null,
                "educationGpaSystem": 4
              }
            ],
            "experience": [
              {
                "id": 1,
                "userId": 1,
                "experienceTitleOriginal": "Software Developer",
                "experienceCompanyName": "TechCorp",
                "experienceStart": "2015-01-01T00:00:00Z",
                "experienceCurrent": true,
                "experienceDescription": "Developed scalable web applications."
              }
            ],
            "extraCurricular": [
              {
                "id": 1,
                "userId": 1,
                "extraCurricularOrganization": "Tech Club",
                "extraCurricularRole": "President",
                "extraCurricularStart": "2014-09-01T00:00:00Z",
                "extraCurricularDescription": "Led technical workshops and events."
              }
            ],
            "skill": [
              {
                "id": 1,
                "userId": 1,
                "skillName": "Golang",
                "skillYoe": 5
              }
            ],
            "certification": [
              {
                "id": 1,
                "userId": 1,
                "certificationName": "AWS Certified Solutions Architect",
                "certificationOrganization": "Amazon",
                "certificationIssueDate": "2020-01-15T00:00:00Z",
                "certificationCredentialUrl": "https://example.com/cert"
              }
            ],
            "reference": [
              {
                "id": 1,
                "userId": 1,
                "referenceName": "Jane Smith",
                "referenceTitle": "Manager",
                "referenceCompany": "TechCorp",
                "referenceEmail": "jane.smith@example.com",
                "referencePhone": "9876543210"
              }
            ],
            "language": [
              {
                "id": 1,
                "userId": 1,
                "languageName": "English",
                "languageId": 1,
                "languageLevel": 5
              }
            ],
            "attachment": [
              {
                "id": 1,
                "userId": 1,
                "attachmentFilename": "resume.pdf",
                "attachmentType": "Resume"
              }
            ],
            "completionIndicator": 85
            }
        """.trimIndent()
        val result = gson.fromJson(json, MyProfileResponse::class.java)
        val flow = MutableStateFlow(result.toDomain())
        return flow.asStateFlow()
    }
}