package com.example.demo_structure.app.di

import com.example.data.remote.ErrorMapper
import com.example.domain.HomeRepository
import com.example.data.repository.HomeRepositoryImpl
import org.koin.dsl.module

/**
 * Created by Phạm Sơn at 11:21/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
val dataModule = module {
    single<HomeRepository> { HomeRepositoryImpl(get(), get()) }
    single<ErrorMapper> { ErrorMapper() }
}