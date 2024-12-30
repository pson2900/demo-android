package com.example.demo_structure.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.UserProfile
import com.example.domain.usecase.HomeUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val savedStateHandle: SavedStateHandle, private val homeUseCase: HomeUseCase) : ViewModel() {
    private val _welcomeMessage = MutableLiveData<UserProfile>()
    val welcomeMessage: LiveData<UserProfile> get() = _welcomeMessage

    fun loadWelcomeMessage() {
        viewModelScope.launch {
            _welcomeMessage.value = homeUseCase.execute()
        }

    }
}