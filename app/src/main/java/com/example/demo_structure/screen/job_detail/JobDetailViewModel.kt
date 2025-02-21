package com.example.demo_structure.screen.job_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.demo_structure.ITEMS
import com.example.demo_structure.core.base.BaseViewModel
import com.example.demo_structure.screen.opportunity.JobPagingSource
import com.example.domain.model.JobDetail
import kotlinx.coroutines.flow.Flow

/**
 * Created by Phạm Sơn at 23:50/8/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class JobDetailViewModel(savedStateHandle: SavedStateHandle): BaseViewModel(savedStateHandle) {

}