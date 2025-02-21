package com.upzi.upzi.screen.create_pin

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.upzi.data.proto.DataStoreManager
import com.upzi.data.remote.UIState
import com.upzi.upzi.core.base.BaseViewModel
import com.upzi.domain.model.Authentication
import com.upzi.domain.model.Register
import com.upzi.domain.model.UserProfile
import com.upzi.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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