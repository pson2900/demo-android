package com.upzi.domain.model

data class VerifyOtp (val isValid: Boolean,val secret: String,val message:String) {
}