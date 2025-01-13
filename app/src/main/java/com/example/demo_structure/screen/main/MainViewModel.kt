package com.example.demo_structure.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo_structure.JobDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Created by Phạm Sơn at 16:38/23/11/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class MainViewModel : ViewModel() {
    var uiState: MutableStateFlow<MainActivityUiState> = MutableStateFlow(MainActivityUiState.Loading)

    init {
        viewModelScope.launch {

            uiState.stateIn(
                scope = viewModelScope,
                initialValue = MainActivityUiState.Loading,
                started = SharingStarted.WhileSubscribed(5_000),
            )
            delay(3000)
            uiState.value = MainActivityUiState.Success
        }
    }

    // Holds the list of posts
    private val _posts = MutableStateFlow<MutableList<JobDetail>>(mutableListOf())
    val posts: StateFlow<MutableList<JobDetail>> = _posts

    // Holds the loading state
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _loadingMore = MutableStateFlow(false)
    val loadingMore: StateFlow<Boolean> = _loadingMore

    // Holds any error messages
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    private var currentPage = 0
    private var isLastPage = false

    init {
//        fetchSearch()
    }



}