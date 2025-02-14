package com.example.demo_structure.screen.verify_email


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.data.remote.UIState
import com.example.demo_structure.core.base.BaseViewModel
import com.example.domain.model.VerifyEmail
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VerifyEmailViewModel(
     val authUseCase: AuthUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle) {

    var email: String
        get() = savedStateHandle.get<String>(EMAIL_KEY) ?: ""
        set(value) = savedStateHandle.set(EMAIL_KEY, value)

    var isChecked: Boolean
        get() = savedStateHandle.get<Boolean>(IS_CHECKED_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHECKED_KEY, value)

    private val _uiState: MutableStateFlow<UIState<VerifyEmail>> =
        MutableStateFlow(UIState.Idle)
    val uiState: StateFlow<UIState<VerifyEmail>> = _uiState


     fun verifyEmail(email: String) {
         viewModelScope.launch{
             processApiCall(
                 call = { authUseCase.verifyEmail(email) },
                 state = _uiState)
         }
    }

    fun clearEmailState() {
        _uiState.value  = UIState.Idle
    }

    companion object {
        const val EMAIL_KEY = "email"
        const val IS_CHECKED_KEY = "isChecked"
    }
}