package com.example.demo_structure.screen.login

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.data.proto.DataStoreManager
import com.example.demo_structure.core.base.BaseViewModel
import com.example.demo_structure.screen.create_pin.PinCodeState
import com.example.domain.model.Authentication
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * Created by Phạm Sơn at 15:17/3/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class LoginViewModel(val dataStoreManager: DataStoreManager , val authUseCase: AuthUseCase , savedStateHandle: SavedStateHandle) : BaseViewModel(savedStateHandle) {
    private val _loginUiState = MutableStateFlow<LoginState>(LoginState.Loading(false))
    val loginUiState: StateFlow<LoginState> = _loginUiState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginUiState.value = LoginState.Loading(true)
            delay(1000)
            val response = authUseCase.login(email, password)
            response.catch { e ->
                _loginUiState.value = LoginState.Error(e.message.toString())
            }.collect { result ->
                Log.e("Sang", "result $result")
                _loginUiState.value = LoginState.LoginSuccess(result)
            }
        }
    }

    fun saveAuth(authentication: Authentication) {
        viewModelScope.launch {
            Log.e("Sang", "save $authentication")
            dataStoreManager.saveAuth(authentication)
        }
    }
}