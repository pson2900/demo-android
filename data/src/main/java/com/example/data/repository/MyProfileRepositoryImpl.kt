package com.example.data.repository

import com.example.data.remote.network.ApiService
import com.example.data.remote.response.BaseResponse
import com.example.data.remote.response.MyProfileResponse
import com.example.domain.model.MyProfile
import com.example.domain.repository.MyProfileRepository
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Phạm Sơn at 12:23/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class MyProfileRepositoryImpl(val apiService: ApiService) : MyProfileRepository {
    override suspend fun getMyProfile(): Flow<MyProfile> {
        val response = """{
    "attachment": [
      {
        "fileName": "cover_letter.pdf",
        "fileUrl": "https://example.com/cover_letter.pdf",
        "id": 1,
        "userId": 1
      }
    ],
    "basic": {
      "birthDate": "1990-01-01",
      "currentCity": "Hanoi",
      "firstName": "John",
      "gender": "Male",
      "isVietnamese": true,
      "lastName": "Doe",
      "phone": "123-456-7890",
      "photo": "https://example.com/photo.jpg"
    },
    "certification": [
      {
        "credentialUrl": "https://example.com/kubernetes-cert",
        "expireDate": "2012-09-01T00:00:00Z",
        "id": 1,
        "issueDate": "2020-05-15T00:00:00Z",
        "name": "Certified Kubernetes Administrator",
        "organization": "CNCF"
      }
    ],
    "characteristic": [
      {
        "characteristic": "Detail-oriented",
        "id": 1,
        "userId": 1
      }
    ],
    "completionIndicator": 30,
    "education": [
      {
        "degree": 1,
        "description": "Bachelor's degree in Computer Science.",
        "endDate": "2012-09-01T00:00:00Z",
        "favoriteSubject": "Mathematics",
        "gpa": 3.5,
        "gpaSystem": 4,
        "id": 1,
        "instituteName": "University of Hanoi",
        "instituteType": "Public",
        "major": "Computer Science",
        "startDate": "2010-09-01T00:00:00Z"
      }
    ],
    "experience": [
      {
        "companyName": "Tech Solutions",
        "description": "Worked on various full-stack projects and developed web applications.",
        "employmentType": 1,
        "endDate": "2012-09-01T00:00:00Z",
        "id": 1,
        "isCurrent": true,
        "locationType": 1,
        "skills": [
          {
            "id": 1,
            "name": "Go",
            "yoe": 3
          },
          {
            "id": 2,
            "name": "JavaScript",
            "yoe": 2
          }
        ],
        "startDate": "2010-09-01T00:00:00Z",
        "titleOriginal": "Junior Software Developer"
      }
    ],
    "externalDoc": [
      {
        "docLink": "https://example.com/resume.pdf",
        "docName": "Resume",
        "id": 1
      }
    ],
    "extraCurricular": [
      {
        "description": "Led club activities and organized coding competitions.",
        "endDate": "2012-09-01T00:00:00Z",
        "id": 1,
        "organization": "Coding Club",
        "role": "President",
        "skills": [
          {
            "id": 3,
            "name": "Leadership",
            "yoe": 2
          }
        ],
        "startDate": "2010-09-01T00:00:00Z",
        "type": 1
      }
    ],
    "hobby": [
      {
        "id": 1,
        "name": "Reading"
      },
      {
        "id": 2,
        "name": "Traveling"
      }
    ],
    "language": [
      {
        "id": 1,
        "level": 5,
        "name": "English"
      }
    ],
    "preference": {
      "desiredCareerPath": 1,
      "desiredCity": [
        {
          "id": 24,
          "name": "Hanoi"
        }
      ],
      "desiredEmploymentType": [
        {
          "id": 1,
          "name": "internship"
        },
        {
          "id": 2,
          "name": "full-time"
        },
        {
          "id": 3,
          "name": "part-time"
        },
        {
          "id": 4,
          "name": "freelance"
        },
        {
          "id": 5,
          "name": "contract"
        }
      ],
      "desiredIndustry": [
        {
          "id": 1,
          "name": "Software Development"
        }
      ],
      "desiredJobTitle": "Software Engineer",
      "desiredLocationType": [
        {
          "id": 1,
          "name": "remote"
        },
        {
          "id": 2,
          "name": "hybrid"
        },
        {
          "id": 3,
          "name": "on-site"
        }
      ],
      "desiredSalary": 30000000,
      "relocation": true
    },
    "reference": [
      {
        "company": "Tech Solutions",
        "email": "jane.smith@example.com",
        "id": 1,
        "name": "Jane Smith",
        "phone": "987-654-3210",
        "title": "Manager"
      }
    ],
    "skill": [
      {
        "id": 1,
        "name": "Go",
        "yoe": 3
      },
      {
        "id": 2,
        "name": "JavaScript",
        "yoe": 2
      }
    ],
    "summary": "Highly skilled Software Engineer with 5 years of experience."
  }"""
        val gson = Gson().fromJson(response,  MyProfileResponse::class.java)
        return flow {
            emit(gson.toDomain())
        }

       /* return flow {
            emit(apiService.getProfile().data.toDomain())
        }*/
    }
}