package com.example.demo_structure.app.di

import com.example.data.remote.ErrorMapper
import com.example.data.repository.HomeRepositoryImpl
import com.example.data.repository.MyProfileRepositoryImpl
import com.example.domain.repository.HomeRepository
import com.example.domain.repository.MyProfileRepository
import org.koin.dsl.module

/**
 * Created by Phạm Sơn at 11:21/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
val dataModule = module {
    single<HomeRepository> { HomeRepositoryImpl(get(), get()) }
    single<MyProfileRepository> { MyProfileRepositoryImpl(get()) }
    single<ErrorMapper> { ErrorMapper() }
}