package com.example.data.remote.response

import com.example.domain.model.BasicInformation

/**
 * Created by Phạm Sơn at 10:34/16/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class BasicInformationResponse(var icon: Int, var title: String, var type: Int) {
    fun toDomain(): BasicInformation {
        return BasicInformation(icon, title, type)
    }
}