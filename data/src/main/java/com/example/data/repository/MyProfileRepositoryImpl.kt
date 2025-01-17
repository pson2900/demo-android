package com.example.data.repository

import com.example.data.remote.ErrorMapper
import com.example.data.remote.response.MyProfileUserResponse
import com.example.domain.model.MyProfileUser
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

    override suspend fun getMyProfile(): Flow<MyProfileUser> {
        val gson = Gson()
        val json =  """
          {
            "basicInfo": {
              "userId": 6700374,
              "firstName": "Huy",
              "lastName": "Dinh Van",
              "jobTitle": "Frontend Developer",
              "jobLevelId": 7,
              "highestDegreeId": 12,
              "avatar": "https://images.vietnamworks.com/pictureofresume/90/1727791409104250.png",
              "yearsExperience": 4,
              "email": "dvhuy.dev@gmail.com",
              "phone": "+84-981563497",
              "birthday": "1997-10-09 07:00:00",
              "genderId": 1,
              "countryId": 1,
              "cityId": 29,
              "address": "141/28 Ton That Tuyệt",
              "nationalityId": 1,
              "maritalStatusId": 2,
              "districtId": 4
            },
            "workingHistories": [
              {
                "jobTitle": "Frontend Team Leader - Mobile Web Squad",
                "jobLevelId": 0,
                "companyId": 0,
                "companyName": "Navigos Group",
                "companyLogo": "",
                "countryId": 0,
                "cityId": 0,
                "industryId": 0,
                "description": "<p>ReactJS, NextJs</p>",
                "experienceOrder": 1,
                "isCurrent": 1,
                "startDate": "2022-12-01",
                "endDate": "0000-00-00",
                "skills": [
                  {
                    "skillName": "React Native",
                    "skillId": 193318,
                    "rate": 1
                  },
                  {
                    "skillName": "CSS Framework",
                    "skillId": 35505,
                    "rate": 1
                  }
                ]
              },
              {
                "jobTitle": "Frontend developer",
                "jobLevelId": 0,
                "companyId": 0,
                "companyName": "Solidbytes",
                "companyLogo": "",
                "countryId": 0,
                "cityId": 0,
                "industryId": 0,
                "description": "<p>NextJs</p>",
                "experienceOrder": 2,
                "isCurrent": 0,
                "startDate": "2022-06-01",
                "endDate": "2023-01-01",
                "skills": [
                  {
                    "skillName": "React Native",
                    "skillId": 193318,
                    "rate": 1
                  },
                  {
                    "skillName": "CSS Framework",
                    "skillId": 35505,
                    "rate": 1
                  },
                  {
                    "skillName": "Bootstrap",
                    "skillId": 9168,
                    "rate": 1
                  },
                  {
                    "skillName": "OOP",
                    "skillId": 75579,
                    "rate": 1
                  }
                ]
              }
            ],
            "educations": [
              {
                "highestDegreeId": 12,
                "schoolId": 0,
                "schoolName": "Cao đẳng kỹ thuật Cao Thắng",
                "major": "Công nghệ thông tin",
                "countryId": 0,
                "description": "<p>123123</p>",
                "educationOrder": 1,
                "startDate": "2015-08-01",
                "endDate": "2018-08-01"
              }
            ],
            "certifications": [
              {
                "certification": "Google Analytics for Beginners",
                "organization": "Google",
                "organizationId": 0,
                "startDate": "2015-08-01",
                "endDate": "2018-08-01",
                "linkCertification": "https://drive.google.com/file/d/1-6B58QjVo-lo50pxMGP46uziBvZtEPKc/vi"
              }
            ],
            "activities": [
              {
                "activity": "Software Tester",
                "title": "Công ty TNHH Tổng Công ty Công nghệ và Giải pháp CMC",
                "organization": "",
                "startDate": "2015-08-01",
                "endDate": "2018-08-01",
                "isCurrent": 0,
                "description": "",
                "skills": [
                  {
                    "skillName": "React Native",
                    "skillId": 193318,
                    "rate": 1
                  },
                  {
                    "skillName": "CSS Framework",
                    "skillId": 35505,
                    "rate": 1
                  },
                  {
                    "skillName": "Bootstrap",
                    "skillId": 9168,
                    "rate": 1
                  },
                  {
                    "skillName": "OOP",
                    "skillId": 75579,
                    "rate": 1
                  }
                ]
              }
            ],
            "hobbies": [
              {
                "hobbyId": 0,
                "hobbyName": "Coding Challenges",
                "description": "Enjoys solving coding problems on platforms like LeetCode and HackerRank to sharpen programming skills."
              },
              {
                "hobbyId": 1,
                "hobbyName": "Reading Tech Blogs",
                "description": "Follows the latest trends in frontend development by reading blogs and articles from industry experts."
              },
              {
                "hobbyId": 2,
                "hobbyName": "Fitness Enthusiast",
                "description": "Regularly engages in physical activities such as running and gym workouts to maintain a healthy lifestyle."
              }
            ],
            "characteristics": [
              {
                "trait": "Detail-Oriented",
                "description": "Pays close attention to details, ensuring high-quality code and user interfaces."
              },
              {
                "trait": "Team Player",
                "description": "Works effectively within a team, demonstrating strong communication and collaboration skills."
              },
              {
                "trait": "Proactive Learner",
                "description": "Continuously seeks opportunities to learn new technologies and improve existing skills."
              },
              {
                "trait": "Problem Solver",
                "description": "Possesses strong analytical and problem-solving abilities, capable of tackling complex technical challenges."
              },
              {
                "trait": "Adaptable",
                "description": "Quickly adapts to new environments and technologies, staying ahead in the fast-paced tech industry."
              }
            ]
          }
        """.trimIndent()
        val result = gson.fromJson(json, MyProfileUserResponse::class.java)
        val flow = MutableStateFlow(result.toDomain())
        return flow.asStateFlow()
    }
}