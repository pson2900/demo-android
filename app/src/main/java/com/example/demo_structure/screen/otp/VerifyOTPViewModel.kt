package com.example.demo_structure.screen.otp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.demo_structure.core.base.BaseViewModel
import com.example.demo_structure.screen.verify_email.EmailState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VerifyOTPViewModel(savedStateHandle: SavedStateHandle) : BaseViewModel(savedStateHandle) {
    val menuUiState: StateFlow<EmailState> = MutableStateFlow<EmailState>(EmailState.Loading).apply {
        viewModelScope.launch {
            delay(2000)
            value = EmailState.Success
        }
    }.asStateFlow()
}