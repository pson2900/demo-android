package com.example.demo_structure.screen.opportunity


/**
 * Created by Phạm Sơn at 20:04/1/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
sealed interface OpportunityState {
    /**
     * The feed is still loading.
     */
    data object Loading: OpportunityState

    /**
     * The feed is still error.
     */
    data class Error(val errorCode: Int, val errorMessage: String): OpportunityState

    /**
     * The feed is loaded with the given list of news resources.
     */
    data object Success : OpportunityState
}