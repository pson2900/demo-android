package com.example.demo_structure.screen.create_pin

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.data.proto.DataStoreManager
import com.example.demo_structure.core.base.BaseViewModel
import com.example.demo_structure.screen.otp.OtpState
import com.example.demo_structure.screen.verify_email.VerifyEmailViewModel.Companion.EMAIL_KEY
import com.example.domain.model.Authentication
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PinCodeViewModel(val dataStoreManager: DataStoreManager ,  val authUseCase: AuthUseCase, savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {
    var passCode: String = ""
    var confirmPasscode: String = ""

    private val _registerUiState = MutableStateFlow<PinCodeState>(PinCodeState.Loading(false))
    val registerUiState: StateFlow<PinCodeState> = _registerUiState.asStateFlow()

    private val _updatePassWordUiState = MutableStateFlow<PinCodeState>(PinCodeState.Loading(false))
    val updatePassWordUiState: StateFlow<PinCodeState> = _updatePassWordUiState.asStateFlow()


    private val _loginUiState = MutableStateFlow<PinCodeState>(PinCodeState.Loading(false))
    val loginUiState: StateFlow<PinCodeState> = _loginUiState.asStateFlow()

    fun register(email: String, password: String, secret: String) {
        viewModelScope.launch {
            _registerUiState.value = PinCodeState.Loading(true)
            delay(1000)
            val response = authUseCase.register(email, password, secret)
            response.catch { e ->
                _registerUiState.value = PinCodeState.Error(e.message.toString())
            }.collect { result ->
                _registerUiState.value = PinCodeState.RegisterSuccess(
                    result.isSuccess,
                    email = email,
                    passCode = password
                )
            }
        }
    }

    fun clearRegisterState() {
        _registerUiState.value = PinCodeState.Idle
    }


    fun updatePassword(email: String, password: String, secret: String) {
        viewModelScope.launch {
            _updatePassWordUiState.value = PinCodeState.Loading(true)
            delay(1000)
            val response = authUseCase.updatePassword(email, password, secret)
            response.catch { e ->
                _updatePassWordUiState.value = PinCodeState.Error(e.message.toString())
            }.collect { result ->
                _updatePassWordUiState.value = PinCodeState.UpdatePasswordSuccess(result.isSuccess)
            }
        }
    }


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginUiState.value = PinCodeState.Loading(true)
            delay(1000)
            val response = authUseCase.login(email, password)
            response.catch { e ->
                _loginUiState.value = PinCodeState.Error(e.message.toString())
            }.collect { result ->
                _loginUiState.value = PinCodeState.LoginSuccess(result)
            }
        }
    }


    fun saveAuth(authentication: Authentication) {
        viewModelScope.launch {
            dataStoreManager.saveAuth(authentication)
        }
    }
}