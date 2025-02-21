package com.upzi.upzi

import com.upzi.data.remote.response.MyProfileResponse
import com.upzi.upzi.app.manager.theme.AppIcons
import com.upzi.domain.model.Filter
import com.upzi.domain.model.FilterType
import com.upzi.domain.model.JobDetail
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

const val ITEM_COUNT = 10000
val ITEMS = (1..ITEM_COUNT).map {
    JobDetail(it, "Software Engineer $it", "Google $it", "$120k", "Mountain View", android.R.drawable.ic_menu_call, "Description $it")
}

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

val filterItems: MutableList<Filter> by lazy {
    val items = mutableListOf<Filter>()
    items.add(Filter(type = FilterType.Sort, icon =  AppIcons.sort))
    items.add(Filter(type = FilterType.Job, icon =  AppIcons.expand))
    items.add(Filter(type = FilterType.Salary, icon =  AppIcons.expand))
    items.add(Filter(type = FilterType.Location, icon =  AppIcons.expand))
    items.add(Filter(type = FilterType.WorkType, icon =  AppIcons.expand))
    items.add(Filter(type = FilterType.JobType, icon =  AppIcons.expand))

    items
}