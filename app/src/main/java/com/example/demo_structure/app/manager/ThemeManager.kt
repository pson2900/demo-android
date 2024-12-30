package com.example.demo_structure.app.manager

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

/**
 * Created by Phạm Sơn at 23:41/27/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class ThemeManager {

    fun setNightMode(isNightMode: Boolean) {
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    fun getCurrentMode(context: Context): Int {
        return AppCompatDelegate.getDefaultNightMode()
    }
}