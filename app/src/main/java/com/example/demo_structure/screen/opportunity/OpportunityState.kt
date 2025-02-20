package com.example.demo_structure.screen.opportunity


/**
 * Created by Phạm Sơn at 20:04/1/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
sealed interface OpportunityState {
    data object Loading: OpportunityState
    data class Error(val errorCode: Int, val errorMessage: String): OpportunityState
    data object Success : OpportunityState
}

sealed class DetailScreenState {
    object Default : DetailScreenState()
    object SearchResult : DetailScreenState()
    object Suggestion : DetailScreenState()
    object Recent : DetailScreenState()
}

interface DetailScreenActions {
    val onTextChange : (String) -> Unit
    val onSearch: (String) -> Unit
    val onSearchWithCV: () -> Unit

    fun getTitle()
    fun onClick()

}