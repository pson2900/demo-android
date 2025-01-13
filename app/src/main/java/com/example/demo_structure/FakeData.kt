package com.example.demo_structure


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