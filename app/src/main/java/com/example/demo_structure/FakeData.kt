package com.example.demo_structure

import com.example.data.remote.response.MyProfileResponse
import com.example.domain.model.JobDetail
import com.google.gson.Gson


/**
 * Created by Phạm Sơn at 10:12/11/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class SearchJob(val jobId: Int, var jobTitle: String, var company: String, logo: Int, val salary: String)

val jobList = listOf(
    JobDetail(1, "Software Engineer", "Google", "$120k", "Mountain View", android.R.drawable.ic_menu_call, "Description 1"),
    JobDetail(2, "UI/UX Designer", "Facebook", "$110k", "Menlo Park", android.R.drawable.ic_menu_camera, "Description 2"),
    JobDetail(3, "Product Manager", "Amazon", "$130k", "Seattle", android.R.drawable.ic_menu_compass, "Description 3"),
    JobDetail(4, "Data Scientist", "Netflix", "$140k", "Los Gatos", android.R.drawable.ic_menu_directions, "Description 4"),
    JobDetail(5, "Mobile Developer", "Spotify", "$100k", "Stockholm", android.R.drawable.ic_menu_gallery, "Description 5"),
    JobDetail(6, "Security Engineer", "Apple", "$150k", "Cupertino", android.R.drawable.ic_menu_mylocation, "Description 6"),
    JobDetail(7, "Cloud Architect", "Microsoft", "$160k", "Redmond", android.R.drawable.ic_menu_send, "Description 7"),
    JobDetail(8, "AI Researcher", "OpenAI", "$170k", "San Francisco", android.R.drawable.ic_menu_share, "Description 8")
)

val myProfileData: MyProfileResponse by lazy {
    val data = """
        {
    "characteristic": [
      {
        "id": 1,
        "userId": 1,
        "characteristic": "Detail-oriented"
      }
    ]
  }
    """.trimIndent()

    Gson().fromJson(data, MyProfileResponse::class.java)
}