package com.example.demo_structure.screen.community


/**
 * Created by Phạm Sơn at 20:04/1/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
sealed interface CommunityState {
    /**
     * The feed is still loading.
     */
    data object Loading: CommunityState

    /**
     * The feed is still error.
     */
    data class Error(val errorCode: Int, val errorMessage: String): CommunityState

    /**
     * The feed is loaded with the given list of news resources.
     */
    data object Success : CommunityState
}