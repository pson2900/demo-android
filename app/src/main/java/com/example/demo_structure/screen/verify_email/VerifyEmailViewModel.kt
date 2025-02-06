package com.example.demo_structure.screen.verify_email

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.demo_structure.core.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

class VerifyEmailViewModel(savedStateHandle: SavedStateHandle) : BaseViewModel(savedStateHandle) {
    val menuUiState: StateFlow<EmailState> = MutableStateFlow<EmailState>(EmailState.Loading).apply {
        viewModelScope.launch {
            delay(2000)
            value = EmailState.Success
        }
    }.asStateFlow()
    val otp: MutableStateFlow<String> = MutableStateFlow("")
    val validateErrorMail: Flow<Boolean> = flow {
        emit(checkValidate(otp.value))
    }.conflate()
        .flowOn(Dispatchers.IO)
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 1)

    fun checkValidate(otp: String): Boolean {
        if (otp.length == 4) {
            if (otp == "1234") {

            } else {
                return true
            }
        }
        return false
    }
}