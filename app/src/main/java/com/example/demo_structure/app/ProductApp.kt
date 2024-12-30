package com.example.demo_structure.app

import android.app.Application

/**
 * Created by Phạm Sơn at 11:24/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class ProductApp : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinSetup.init(this)
    }
}