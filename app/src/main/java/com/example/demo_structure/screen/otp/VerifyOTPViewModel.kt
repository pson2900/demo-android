package com.example.demo_structure.screen.otp

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.demo_structure.core.base.BaseViewModel
import com.example.demo_structure.screen.verify_email.EmailState
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class VerifyOTPViewModel(val authUseCase: AuthUseCase, savedStateHandle: SavedStateHandle) : BaseViewModel(savedStateHandle) {
    private val _otpUiState = MutableStateFlow<OtpState>(OtpState.Loading(false))
    val otplUiState: StateFlow<OtpState> = _otpUiState.asStateFlow()

    fun sendOtp(email: String) {
        viewModelScope.launch {
            _otpUiState.value = OtpState.Loading(true)
            delay(1000)
            val response = authUseCase.sendOtp(email)
            response.catch { e->
                _otpUiState.value = OtpState.Error(e.message.toString())
            }.collect { result ->
                _otpUiState.value = OtpState.Success(result.isSuccess)
            }
        }
    }

    fun clearOTPState() {
        _otpUiState.value = OtpState.Idle
    }


    fun forgetPassword(email: String) {
        viewModelScope.launch {
            _otpUiState.value = OtpState.Loading(true)
            delay(1000)
            val response = authUseCase.forgetPassword(email)
            response.catch { e->
                _otpUiState.value = OtpState.Error(e.message.toString())
            }.collect { result ->
                _otpUiState.value = OtpState.ForgetPasswordSuccess(result.isSuccess)
            }
        }
    }


    fun verifyOtp(email: String, otp: String) {
        viewModelScope.launch {
            _otpUiState.value = OtpState.Loading(true)
            delay(1000)
            val response = authUseCase.verifyOtp(email,otp)
            response.catch { e->
                _otpUiState.value = OtpState.Error(e.message.toString())
            }.collect { result ->
                _otpUiState.value = OtpState.VerifyOtpSuccess(result)
            }
        }
    }
}