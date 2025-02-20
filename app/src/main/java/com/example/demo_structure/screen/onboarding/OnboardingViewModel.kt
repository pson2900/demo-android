package com.example.demo_structure.screen.onboarding

import androidx.lifecycle.SavedStateHandle
import com.example.data.proto.DataStoreManager
import com.example.demo_structure.core.base.BaseViewModel

class OnboardingViewModel(
    val dataStoreManager: DataStoreManager,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle) {
}