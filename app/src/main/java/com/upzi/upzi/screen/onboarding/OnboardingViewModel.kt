package com.upzi.upzi.screen.onboarding

import androidx.lifecycle.SavedStateHandle
import com.upzi.data.proto.DataStoreManager
import com.upzi.upzi.core.base.BaseViewModel

class OnboardingViewModel(
    val dataStoreManager: DataStoreManager,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle) {
}