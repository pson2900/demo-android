package com.upzi.upzi.app.di

import com.upzi.upzi.screen.community.CommunityViewModel
import com.upzi.upzi.screen.create_pin.PinCodeViewModel
import com.upzi.upzi.screen.education.EducationViewModel
import com.upzi.upzi.screen.home.HomeViewModel
import com.upzi.upzi.screen.job_detail.JobDetailViewModel
import com.upzi.upzi.screen.login.LoginViewModel
import com.upzi.upzi.screen.main.MainViewModel
import com.upzi.upzi.screen.onboarding.OnboardingViewModel
import com.upzi.upzi.screen.opportunity.OpportunityViewModel
import com.upzi.upzi.screen.otp.VerifyOTPViewModel
import com.upzi.upzi.screen.user.UserViewModel
import com.upzi.upzi.screen.verify_email.VerifyEmailViewModel
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