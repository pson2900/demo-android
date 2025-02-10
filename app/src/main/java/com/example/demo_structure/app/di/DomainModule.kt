package com.example.demo_structure.app.di

import com.example.domain.usecase.ClientUseCase
import com.example.domain.usecase.HomeUseCase
import com.example.domain.usecase.MyProfileUseCase
import com.example.domain.usecase.VersionUseCase
import org.koin.dsl.module

/**
 * Created by Phạm Sơn at 11:22/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
val domainModule = module {
    factory<ClientUseCase> { ClientUseCase(get()) }
    factory<HomeUseCase> { HomeUseCase(get()) }
    factory<MyProfileUseCase> { MyProfileUseCase(get()) }
    factory<VersionUseCase> { VersionUseCase(get()) }
}