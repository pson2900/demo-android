package com.example.demo_structure.app.di

import com.example.domain.usecase.GetClientUseCase
import com.example.domain.usecase.HomeUseCase
import com.example.domain.usecase.MyProfileUseCase
import org.koin.dsl.module

/**
 * Created by Phạm Sơn at 11:22/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
val domainModule = module {
    factory { GetClientUseCase(get()) }
    factory { HomeUseCase(get()) }
    factory { MyProfileUseCase(get()) }
}