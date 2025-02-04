package com.example.demo_structure.screen.otp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.demo_structure.core.base.BaseViewModel
import com.example.demo_structure.screen.verify_email.EmailState
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class VerifyOTPViewModel(val authUseCase: AuthUseCase, savedStateHandle: SavedStateHandle) : BaseViewModel(savedStateHandle) {
    private val _otpUiState = MutableStateFlow<OtpState>(OtpState.Loading(false))
    val otplUiState: StateFlow<OtpState> = _otpUiState.asStateFlow()


    fun sendOtp() {
        viewModelScope.launch {
            _otpUiState.value = OtpState.Loading(true)
            delay(1000)
            try {
                val response = authUseCase.sendOtp()
                response.collectLatest { result ->
                    _otpUiState.value = OtpState.Success(result.isSuccess)
                }
            } catch (e: Exception) {
                _otpUiState.value = OtpState.Error(e.message.toString())
            }
        }
    }
}