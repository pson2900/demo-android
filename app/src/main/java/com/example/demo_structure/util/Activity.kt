package com.example.demo_structure.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

/**
 * Created by Phạm Sơn at 13:03/6/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("Activity is not found")
}