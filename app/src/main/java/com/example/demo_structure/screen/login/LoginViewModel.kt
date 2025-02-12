package com.example.demo_structure.screen.login

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.data.proto.DataStoreManager
import com.example.data.remote.UIState
import com.example.demo_structure.core.base.BaseViewModel
import com.example.domain.model.Authentication
import com.example.domain.usecase.AuthUseCase
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
                state = _loginUiState,
                dataKey = "LOGIN")
        }
    }

    fun saveAuth(authentication: Authentication) {
        viewModelScope.launch {
            dataStoreManager.saveAuth(authentication)
        }
    }
}