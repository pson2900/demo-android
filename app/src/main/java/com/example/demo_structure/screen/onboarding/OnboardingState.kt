package com.example.demo_structure.screen.onboarding

import com.example.demo_structure.screen.job_detail.JobDetailState

sealed interface  OnboardingState {
    data object Step1 : OnboardingState
    data object Step2 : OnboardingState
    data object Step3 : OnboardingState
    data object Step4 : OnboardingState
}