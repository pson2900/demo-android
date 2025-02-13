package com.example.demo_structure.screen.create_pin

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.data.proto.DataStoreManager
import com.example.data.remote.UIState
import com.example.demo_structure.core.base.BaseViewModel
import com.example.domain.model.Authentication
import com.example.domain.model.Register
import com.example.domain.model.UserProfile
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PinCodeViewModel(
    val dataStoreManager: DataStoreManager,
    val authUseCase: AuthUseCase,
    savedStateHandle: SavedStateHandle
) :
    BaseViewModel(savedStateHandle) {
    var passCode: String = ""
    var confirmPasscode: String = ""
    var email: String = ""

    private val _registerUiState: MutableStateFlow<UIState<Register>> =
        MutableStateFlow(UIState.Idle)
    val registerUiState: StateFlow<UIState<Register>> = _registerUiState


    private val _updatePWUiState: MutableStateFlow<UIState<Register>> =
        MutableStateFlow(UIState.Idle)
    val updatePWUiState: StateFlow<UIState<Register>> = _updatePWUiState

    private val _loginUiState: MutableStateFlow<UIState<Authentication>> =
        MutableStateFlow(UIState.Idle)
    val loginUiState: StateFlow<UIState<Authentication>> = _loginUiState


    fun register(secret: String) {
        viewModelScope.launch {
            processApiCall(
                call = { authUseCase.register(email, passCode, secret) },
                state = _registerUiState
            )
        }
    }


    fun clearState() {
        _registerUiState.value = UIState.Idle
        _loginUiState.value = UIState.Idle
        _updatePWUiState.value = UIState.Idle
    }

    fun updatePassword(secret: String) {
        viewModelScope.launch {
            processApiCall(
                call = { authUseCase.updatePassword(email, passCode, secret) },
                state = _updatePWUiState)
        }
    }

    fun login() {
        viewModelScope.launch {
            processApiCall(
                call = { authUseCase.login(email, passCode) },
                state = _loginUiState
            )
        }
    }

    fun saveAuth(authentication: Authentication) {
        viewModelScope.launch {
            dataStoreManager.saveAuth(authentication)
        }
    }

    fun saveUserInfo(userProfile: UserProfile){
        viewModelScope.launch{
            userProfile.email = email
            userProfile.fullName = "Tung Be De"
            dataStoreManager.saveUserProfile(userProfile)
        }
    }
}