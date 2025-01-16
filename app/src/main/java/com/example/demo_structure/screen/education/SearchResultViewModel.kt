package com.example.demo_structure.screen.education

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * Created by Phạm Sơn at 16:16/2/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class SearchResultViewModel : ViewModel() {
    private val searchResultState = MutableStateFlow(
        SearchResultViewModelState(
            isLoading = true,
            searchJobs = mutableListOf(),
            searchInput = ""
        )
    )

    val uiState = searchResultState
        .map(SearchResultViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            searchResultState.value.toUiState()
        )

    init {
    }
}

