package com.example.demo_structure.screen.otp


sealed class OTPType(val type: String) {
    data object LOGIN : OTPType("login")
    data object FORGOT_PASSWORD : OTPType("forgot_password")
}