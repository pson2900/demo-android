package com.upzi.upzi.screen.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.upzi.data.proto.DataStoreManager
import com.upzi.data.remote.UIState
import com.upzi.upzi.core.base.BaseViewModel
import com.upzi.domain.model.Authentication
import com.upzi.domain.model.UserProfile
import com.upzi.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Created by Phạm Sơn at 15:17/3/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class LoginViewModel(val dataStoreManager: DataStoreManager , val authUseCase: AuthUseCase , savedStateHandle: SavedStateHandle) : BaseViewModel(savedStateHandle) {

    private val _loginUiState: MutableStateFlow<UIState<Authentication>> =
        MutableStateFlow(UIState.Idle)
    val loginUiState: StateFlow<UIState<Authentication>> = _loginUiState

    fun login(email: String, password: String) {
        viewModelScope.launch{
            processApiCall(
                call = {  authUseCase.login(email, password) },
                state = _loginUiState)
        }
    }

    fun saveAuth(authentication: Authentication) {
        viewModelScope.launch {
            dataStoreManager.saveAuth(authentication)
        }
    }

    fun saveUserInfo(userProfile: UserProfile){
        viewModelScope.launch{
            dataStoreManager.saveUserProfile(userProfile)
        }
    }
}