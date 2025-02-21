package com.upzi.upzi.app.manager

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

/**
 * Created by Phạm Sơn at 23:42/27/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
object LanguageManager {

    fun setLocale(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    fun getCurrentLanguage(): String {
        return Locale.getDefault().language
    }
}