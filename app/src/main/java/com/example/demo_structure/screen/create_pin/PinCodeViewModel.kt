package com.example.demo_structure.screen.create_pin

import androidx.lifecycle.SavedStateHandle
import com.example.demo_structure.core.base.BaseViewModel
import com.example.domain.usecase.AuthUseCase

class PinCodeViewModel (val authUseCase: AuthUseCase, savedStateHandle: SavedStateHandle) : BaseViewModel(savedStateHandle) {
}