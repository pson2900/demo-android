package com.example.data.remote

/**
 * Created by Phạm Sơn at 16:02/21/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
sealed interface UIEvent {
    object Refresh : UIEvent
    data class ItemClick(val itemId: Int) : UIEvent
}