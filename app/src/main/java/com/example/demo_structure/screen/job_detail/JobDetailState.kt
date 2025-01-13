package com.example.demo_structure.screen.job_detail

/**
 * Created by Phạm Sơn at 22:07/9/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
sealed interface JobDetailState {
    /**
     * The feed is still loading.
     */
    data object Loading : JobDetailState

    /**
     * The feed is loaded with the given list of news resources.
     */
    data object Success : JobDetailState
}