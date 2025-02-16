package com.example.demo_structure.screen.opportunity

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.demo_structure.ITEMS
import com.example.domain.model.JobDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    val items : Flow<PagingData<JobDetail>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2, enablePlaceholders = false),
        pagingSourceFactory = { JobPagingSource(ITEMS, 20) }
    ).flow.cachedIn(viewModelScope)

    fun onTextChange(text: String){
        if (text.isNullOrEmpty()){

        }
    }

    fun onSearch(){

    }

    fun onSearchWithCV(){

    }
}

