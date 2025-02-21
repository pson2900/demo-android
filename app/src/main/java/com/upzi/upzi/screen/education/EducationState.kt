package com.upzi.upzi.screen.education

import com.upzi.domain.model.Authentication

interface EducationState {
    object Idle : EducationState
    data class Loading(val isLoading: Boolean) : EducationState
    data class AuthSuccess(val authentication: Authentication) : EducationState
    data class AuthError(val msg: String): EducationState
}