package com.example.demo_structure.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Created by Phạm Sơn at 15:17/3/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class HomeViewModel : ViewModel() {
    val homeUiState: StateFlow<HomeState> = MutableStateFlow<HomeState>(HomeState.Loading).apply {
        viewModelScope.launch {
            delay(2000)
            value = HomeState.Success
        }
    }.asStateFlow()


    fun fetchSearch() {

        /*viewModelScope.launch {
            delay(1000L)
            try {

                val fetchedPosts = RetrofitNetwork.RetrofitInstance.api.handleSearchFilter(body = RetrofitNetwork.RetrofitInstance.getBody(0)) // Fetch data
                val searchJobs = fetchedPosts.getSearchJobs().toMutableSet()
            } catch (e: Exception) {
            } finally {
            }
        }*/
    }
}