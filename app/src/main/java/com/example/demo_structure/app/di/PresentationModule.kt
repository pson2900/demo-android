package com.example.demo_structure.app.di

import com.example.demo_structure.screen.community.CommunityViewModel
import com.example.demo_structure.screen.create_pin.PinCodeViewModel
import com.example.demo_structure.screen.education.EducationViewModel
import com.example.demo_structure.screen.home.HomeViewModel
import com.example.demo_structure.screen.job_detail.JobDetailViewModel
import com.example.demo_structure.screen.login.LoginViewModel
import com.example.demo_structure.screen.main.MainViewModel
import com.example.demo_structure.screen.onboarding.OnboardingViewModel
import com.example.demo_structure.screen.opportunity.OpportunityViewModel
import com.example.demo_structure.screen.otp.VerifyOTPViewModel
import com.example.demo_structure.screen.user.UserViewModel
import com.example.demo_structure.screen.verify_email.VerifyEmailViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Phạm Sơn at 12:47/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
val presentationModule = module {
    viewModel { HomeViewModel(dataStoreManager = get(), savedStateHandle = get()) }
    viewModel { MainViewModel(savedStateHandle = get()) }
    viewModel { OpportunityViewModel(savedStateHandle = get()) }
    viewModel { CommunityViewModel(savedStateHandle = get()) }
    viewModel { UserViewModel(dataStoreManager = get(), myProfileUseCase = get(), stateHandle = get()) }
    viewModel { EducationViewModel(dataStoreManager = get(),savedStateHandle = get()) }
    viewModel { JobDetailViewModel(savedStateHandle = get()) }
    viewModel { LoginViewModel(dataStoreManager = get(), authUseCase = get(), savedStateHandle = get()) }
    viewModel { VerifyEmailViewModel(authUseCase = get(), savedStateHandle = get()) }
    viewModel { VerifyOTPViewModel(authUseCase = get(), savedStateHandle = get()) }
    viewModel { PinCodeViewModel(dataStoreManager = get(),authUseCase = get(), savedStateHandle = get()) }
    viewModel { OnboardingViewModel(dataStoreManager = get(), savedStateHandle = get()) }

}