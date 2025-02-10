package com.example.demo_structure.app.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.remote.local.database.AppDatabase
import com.example.data.remote.local.database.dao.VersionDao
import org.koin.dsl.module

/**
 * Created by Phạm Sơn at 22:07/8/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
val databaseModule = module {
  /*  single {
        Room.databaseBuilder(
            get<Application>(), // Cần Application context
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    single<VersionDao> { get<AppDatabase>().versionDao() }*/
}