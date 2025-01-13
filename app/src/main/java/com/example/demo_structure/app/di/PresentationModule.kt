package com.example.demo_structure.app.di

import com.example.demo_structure.screen.home.HomeViewModel
import com.example.demo_structure.screen.job_detail.JobDetailViewModel
import com.example.demo_structure.screen.search_result.SearchResultViewModel
import com.example.demo_structure.screen.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Phạm Sơn at 12:47/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
val presentationModule = module {
    viewModel { HomeViewModel() }
    viewModel { JobDetailViewModel() }
    viewModel { UserViewModel() }
    viewModel { SearchResultViewModel() }
}