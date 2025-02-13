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

//    fun sendOtp(email: String) {
//        viewModelScope.launch {
//            _otpUiState.value = OtpState.Loading(true)
//            delay(1000)
//            val response = authUseCase.sendOtp(email)
//            response.catch { e->
//                _otpUiState.value = OtpState.Error(e.message.toString())
//            }.collect { result ->
//                _otpUiState.value = OtpState.Success(result.isSuccess)
//            }
//        }
//    }

    fun sendOtp(email: String) {
        viewModelScope.launch {
            processApiCall(
                call = { authUseCase.sendOtp(email) },
                state = _otpUiState,
                dataKey = ITEM_SEND_OTP
            )
        }
    }

    fun clearOTPState() {
        _otpUiState.value = UIState.Idle
    }


    //    fun forgetPassword(email: String) {
//        viewModelScope.launch {
//            _otpUiState.value = OtpState.Loading(true)
//            delay(1000)
//            val response = authUseCase.forgetPassword(email)
//            response.catch { e->
//                _otpUiState.value = OtpState.Error(e.message.toString())
//            }.collect { result ->
//                _otpUiState.value = OtpState.ForgetPasswordSuccess(result.isSuccess)
//            }
//        }
//    }
    fun forgetPassword(email: String) {
        viewModelScope.launch {
            processApiCall(
                call = { authUseCase.forgetPassword(email) },
                state = _forgetPwUiState,
                dataKey = ITEM_FORGOT_PASS
            )
        }
    }

    fun verifyOtp(email: String, otp: String) {
        viewModelScope.launch {
            processApiCall(
                call = { authUseCase.verifyOtp(email, otp) },
                state = _verifyOtpUiState,
                dataKey = ITEM_VERIFY_OTP
            )
        }
    }


//    fun verifyOtp(email: String, otp: String) {
//        viewModelScope.launch {
//            _otpUiState.value = OtpState.Loading(true)
//            delay(1000)
//            val response = authUseCase.verifyOtp(email,otp)
//            response.catch { e->
//                _otpUiState.value = OtpState.Error(e.message.toString())
//            }.collect { result ->
//                _otpUiState.value = OtpState.VerifyOtpSuccess(result)
//            }
//        }
//    }

    companion object {
        private const val ITEM_SEND_OTP = "item_send_otp"
        private const val ITEM_VERIFY_OTP = "item_verify_otp"
        private const val ITEM_FORGOT_PASS = "item_forgot_password"
    }
}