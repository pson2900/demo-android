package com.example.data.remote.response

/**
 * Created by Phạm Sơn at 13:27/17/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class BaseResponse<T>(
    status: String, message: String, data: T, error: Error?
)