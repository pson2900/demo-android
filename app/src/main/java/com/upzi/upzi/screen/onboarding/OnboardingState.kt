package com.upzi.upzi.screen.onboarding

sealed interface  OnboardingState {
    data object Step1 : OnboardingState
    data object Step2 : OnboardingState
    data object Step3 : OnboardingState
    data object Step4 : OnboardingState
}