package com.example.demo_structure.screen.otp

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.data.remote.UIState
import com.example.demo_structure.core.base.BaseViewModel
import com.example.domain.model.SendOtp
import com.example.domain.model.VerifyOtp
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VerifyOTPViewModel(val authUseCase: AuthUseCase, savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {

    private val _otpUiState: MutableStateFlow<UIState<SendOtp>> =
        MutableStateFlow(UIState.Idle)
    val otpUiState: StateFlow<UIState<SendOtp>> = _otpUiState

    private val _verifyOtpUiState: MutableStateFlow<UIState<VerifyOtp>> =
        MutableStateFlow(UIState.Idle)
    val verifyOtpUiState: StateFlow<UIState<VerifyOtp>> = _verifyOtpUiState


    private val _forgetPwUiState: MutableStateFlow<UIState<SendOtp>> =
        MutableStateFlow(UIState.Idle)
    val forgetPwUiState: StateFlow<UIState<SendOtp>> = _forgetPwUiState

    fun sendOtp(email: String) {
        viewModelScope.launch {
            processApiCall(
                call = { authUseCase.sendOtp(email) },
                state = _otpUiState
            )
        }
    }

    fun clearOTPState() {
        _otpUiState.value = UIState.Idle
    }

    fun forgetPassword(email: String) {
        viewModelScope.launch {
            processApiCall(
                call = { authUseCase.forgetPassword(email) },
                state = _forgetPwUiState
            )
        }
    }

    fun verifyOtp(email: String, otp: String) {
        viewModelScope.launch {
            processApiCall(
                call = { authUseCase.verifyOtp(email, otp) },
                state = _verifyOtpUiState
            )
        }
    }
}