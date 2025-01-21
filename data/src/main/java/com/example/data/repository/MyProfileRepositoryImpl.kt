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
      "firstName": "John",
      "lastName": "Doe",
      "photo": "https://example.com/photo.jpg",
      "birthDate": "1990-01-01",
      "gender": "Male",
      "phone": "123-456-7890",
      "currentCity": "Hanoi",
      "isVietnamese": true
    },
    "preference": {
      "desiredSalary": 30000000,
      "desiredJobTitle": "Software Engineer",
      "desiredCareerPath": 1,
      "desiredCity": [
        {
          "id": 24,
          "name": "Hanoi"
        }
      ],
      "relocation": true,
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
      "desiredIndustry": [
        {
          "id": 1,
          "name": "Software Development"
        }
      ]
    },
    "summary": "Highly skilled Software Engineer with 5 years of experience.",
    "education": [
      {
        "id": 1,
        "instituteName": "University of Hanoi",
        "instituteType": "Public",
        "major": "Computer Science",
        "start": "2010-09-01T00:00:00Z",
        "Eed": "2012-09-01T00:00:00Z",
        "degree": 1,
        "gpa": 3.5,
        "gpaSystem": 4,
        "description": "Bachelor's degree in Computer Science.",
        "favoriteSubject": "Mathematics"
      }
    ],
    "experience": [
      {
        "id": 1,
        "titleOriginal": "Junior Software Developer",
        "companyName": "Tech Solutions",
        "start": "2015-06-01T00:00:00Z",
        "end": "2012-09-01T00:00:00Z",
        "isCurrent": true,
        "locationType": 1,
        "employmentType": 1,
        "description": "Worked on various full-stack projects and developed web applications.",
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
        ]
      }
    ],
    "extraCurricular": [
      {
        "id": 1,
        "Organization": "Coding Club",
        "Role": "President",
        "Type": 1,
        "Start": "2012-09-01T00:00:00Z",
        "End": "2012-09-01T00:00:00Z",
        "Description": "Led club activities and organized coding competitions.",
        "skills": [
          {
            "id": 3,
            "name": "Leadership",
            "yoe": 2
          }
        ]
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
    "certification": [
      {
        "id": 1,
        "name": "Certified Kubernetes Administrator",
        "organization": "CNCF",
        "issueDate": "2020-05-15T00:00:00Z",
        "expireDate": "2012-09-01T00:00:00Z",
        "credentialUrl": "https://example.com/kubernetes-cert"
      }
    ],
    "reference": [
      {
        "id": 1,
        "name": "Jane Smith",
        "title": "Manager",
        "company": "Tech Solutions",
        "email": "jane.smith@example.com",
        "phone": "987-654-3210"
      }
    ],
    "language": [
      {
        "id": 1,
        "name": "English",
        "level": 5
      }
    ],
    "externalDoc": [
      {
        "id": 1,
        "docName": "Resume",
        "docLink": "https://example.com/resume.pdf"
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
    "characteristic": [
      {
        "id": 1,
        "userId": 1,
        "characteristic": "Detail-oriented"
      }
    ],
    "completionIndicator": 30,
    "attachment": [
      {
        "id": 1,
        "userId": 1,
        "fileName": "cover_letter.pdf",
        "fileUrl": "https://example.com/cover_letter.pdf"
      }
    ]
  }
        """.trimIndent()
        val result = gson.fromJson(json, MyProfileResponse::class.java)
        val flow = MutableStateFlow(result.toDomain())
        return flow.asStateFlow()
    }
}