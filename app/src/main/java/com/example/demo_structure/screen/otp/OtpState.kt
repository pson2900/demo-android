package com.example.demo_structure.screen.otp

import com.example.demo_structure.screen.verify_email.EmailState
import com.example.domain.model.VerifyOtp


sealed interface OtpState {
    object Idle : OtpState
    data class Loading(val isLoading: Boolean) : OtpState
    data class Success(val isSuccess: Boolean) : OtpState
    data class ForgetPasswordSuccess(val isSuccess: Boolean) : OtpState
    data class VerifyOtpSuccess(val verifyOtp: VerifyOtp) : OtpState
    data class Error(val msg: String): OtpState
}