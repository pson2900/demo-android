package com.example.demo_structure.screen.verify_email


import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.demo_structure.core.base.BaseViewModel
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class VerifyEmailViewModel(
    private val authUseCase: AuthUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle) {

    var email: String
        get() = savedStateHandle.get<String>(EMAIL_KEY) ?: ""
        set(value) = savedStateHandle.set(EMAIL_KEY, value)

    var isChecked: Boolean
        get() = savedStateHandle.get<Boolean>(IS_CHECKED_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHECKED_KEY, value)

    private val _emailUiState = MutableStateFlow<EmailState>(EmailState.Loading(false))
    val emailUiState: StateFlow<EmailState> = _emailUiState.asStateFlow()

    fun verifyEmail(email: String) {
        viewModelScope.launch {
            _emailUiState.value = EmailState.Loading(true)
            delay(200)
            val response = authUseCase.verifyEmail(email)
            response.catch { e ->
                _emailUiState.value = EmailState.Error("Error fetching data: ${e.message}")
            }.collect { result ->
                _emailUiState.value = EmailState.Success(result.found)
            }
        }
    }

    fun clearEmailState() {
        _emailUiState.value = EmailState.Idle
    }

    companion object {
        const val EMAIL_KEY = "email"
        const val IS_CHECKED_KEY = "isChecked"
    }
}