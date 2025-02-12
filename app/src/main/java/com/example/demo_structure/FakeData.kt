package com.example.demo_structure

import com.example.data.remote.response.MyProfileResponse
import com.google.gson.Gson


/**
 * Created by Phạm Sơn at 10:12/11/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class JobDetail(val jobId: Int, var jobTitle: String, var company: String, logo: Int, val salary: String)
class SearchJob(val jobId: Int, var jobTitle: String, var company: String, logo: Int, val salary: String)
val jobResult by lazy {
    listOf(
        JobDetail(jobId = 1, jobTitle = "Android Developer", company = "Tech Corp", logo = R.drawable.company_logo, salary = "1500 - 2500 USD"),
        JobDetail(jobId = 2, jobTitle = "Backend Engineer", company = "Cloud Solutions", logo = R.drawable.company_logo, salary = "2000 - 3000 USD"),
        JobDetail(jobId = 3, jobTitle = "Data Scientist", company = "DataWorld", logo = R.drawable.company_logo, salary = "3000 - 5000 USD"),
        JobDetail(jobId = 4, jobTitle = "Frontend Developer", company = "UI Labs", logo = R.drawable.company_logo, salary = "1800 - 2700 USD"),
        JobDetail(jobId = 5, jobTitle = "Project Manager", company = "Agile Minds", logo = R.drawable.company_logo, salary = "4000 - 6000 USD"),
        JobDetail(jobId = 6, jobTitle = "DevOps Engineer", company = "InfraCore", logo = R.drawable.company_logo, salary = "2500 - 3500 USD"),
        JobDetail(jobId = 7, jobTitle = "Product Designer", company = "Creative Studio", logo = R.drawable.company_logo, salary = "1700 - 2400 USD"),
        JobDetail(jobId = 8, jobTitle = "Quality Assurance", company = "Quality First", logo = R.drawable.company_logo, salary = "1200 - 2000 USD"),
        JobDetail(jobId = 9, jobTitle = "Database Administrator", company = "DB Experts", logo = R.drawable.company_logo, salary = "2200 - 3200 USD"),
        JobDetail(jobId = 10, jobTitle = "Cybersecurity Analyst", company = "SecureNet", logo = R.drawable.company_logo, salary = "2800 - 4000 USD")
    )
}

val myProfileData: MyProfileResponse by lazy {
    val data = """
        {
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
    "completionIndicator": 15,
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
  }
    """.trimIndent()

    Gson().fromJson(data, MyProfileResponse::class.java)
}