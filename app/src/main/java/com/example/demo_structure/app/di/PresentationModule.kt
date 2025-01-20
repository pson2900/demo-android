package com.example.demo_structure.app.di

import androidx.lifecycle.SavedStateHandle
import com.example.demo_structure.screen.community.CommunityViewModel
import com.example.demo_structure.screen.home.HomeViewModel
import com.example.demo_structure.screen.job_detail.JobDetailViewModel
import com.example.demo_structure.screen.login.LoginViewModel
import com.example.demo_structure.screen.education.EducationResultViewModel
import com.example.demo_structure.screen.main.MainViewModel
import com.example.demo_structure.screen.opportunity.OpportunityViewModel
import com.example.demo_structure.screen.user.UserViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Phạm Sơn at 12:47/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
val presentationModule = module {
    viewModel { HomeViewModel() }
    viewModel { MainViewModel() }
    viewModel { OpportunityViewModel() }
    viewModel { CommunityViewModel() }
    viewModel { UserViewModel(get(), get()) }
    viewModel { EducationResultViewModel(savedStateHandle = SavedStateHandle()) }
    viewModel { JobDetailViewModel() }
    viewModel { LoginViewModel() }
}