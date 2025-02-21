package com.upzi.upzi.app.di

import com.upzi.domain.repository.MyProfileRepository
import com.upzi.domain.usecase.AuthUseCase
import com.upzi.domain.usecase.ClientUseCase
import com.upzi.domain.usecase.HomeUseCase
import com.upzi.domain.usecase.MyProfileUseCase
import com.upzi.domain.usecase.VersionUseCase
import org.koin.dsl.module

/**
 * Created by Phạm Sơn at 11:22/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
val domainModule = module {
    factory<ClientUseCase> { ClientUseCase(get()) }
    factory<HomeUseCase> { HomeUseCase(get()) }
    factory<MyProfileUseCase> { MyProfileUseCase(get<MyProfileRepository>()) }
    factory<VersionUseCase> { VersionUseCase(get()) }
    factory<AuthUseCase>{ AuthUseCase(get()) }
}