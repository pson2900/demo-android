package com.upzi.upzi.app.di

import com.upzi.data.proto.DataStoreManager
import com.upzi.data.repository.AuthRepositoryImpl
import com.upzi.data.repository.HomeRepositoryImpl
import com.upzi.data.repository.MyProfileRepositoryImpl
import com.upzi.data.repository.VersionCheckerRepositoryImpl
import com.upzi.domain.repository.AuthRepository
import com.upzi.domain.repository.HomeRepository
import com.upzi.domain.repository.MyProfileRepository
import com.upzi.domain.repository.VersionRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Phạm Sơn at 11:21/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
val dataModule = module {
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single<MyProfileRepository> { MyProfileRepositoryImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<DataStoreManager> { DataStoreManager(androidContext()) }
    single<VersionRepository> { VersionCheckerRepositoryImpl(get(), get()) }

}