package com.example.demo_structure.screen.verify_email

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.data.remote.UIState
import com.example.demo_structure.core.base.BaseViewModel
import com.example.domain.model.VerifyEmail
import com.example.domain.repository.AuthRepository
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class VerifyEmailViewModel(private val authUseCase: AuthUseCase, savedStateHandle: SavedStateHandle)  : BaseViewModel(savedStateHandle) {

    private val _emailUiState = MutableStateFlow<EmailState>(EmailState.Loading)
    val emailUiState: StateFlow<EmailState> = _emailUiState.asStateFlow()

     fun verifyEmail() {
        viewModelScope.launch {
            _emailUiState.value = EmailState.Loading
            delay(1000)
            try {
                val response = authUseCase.verifyEmail()
                response.collectLatest { result ->
                    _emailUiState.value = EmailState.Success(result.found)
                }
            } catch (e: Exception) {
                _emailUiState.value = EmailState.Error("Error fetching data: ${e.message}")
            }
        }
    }
}