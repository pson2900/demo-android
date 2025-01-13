package com.example.demo_structure.core.base

/**
 * Created by Phạm Sơn at 10:25/4/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
sealed interface BaseState<T>  {
    /**
     * The feed is still loading.
     */
    data class Loading(val isLoading: Boolean)

    /**
     * The feed is still error.
     */
    data class Error(val errorCode: Int, val errorMessage: String)
}