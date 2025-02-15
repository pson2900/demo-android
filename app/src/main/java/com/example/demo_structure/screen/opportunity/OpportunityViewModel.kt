package com.example.demo_structure.screen.opportunity

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Created by Phạm Sơn at 16:16/2/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class OpportunityViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val uiState: StateFlow<OpportunityState> = MutableStateFlow<OpportunityState>(OpportunityState.Loading).apply {
        viewModelScope.launch {
            delay(2000)
            value = OpportunityState.Success
        }
    }.asStateFlow()


    fun onTextChange(text: String){
        if (text.isNullOrEmpty()){

        }
    }

    fun onSearch(){

    }

    fun onSearchWithCV(){

    }
}

