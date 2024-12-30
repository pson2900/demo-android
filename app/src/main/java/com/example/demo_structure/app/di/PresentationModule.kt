package com.example.demo_structure.app.di

import com.example.demo_structure.ui.dashboard.DashboardViewModel
import com.example.demo_structure.ui.home.HomeViewModel
import com.example.demo_structure.ui.notification.NotificationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Phạm Sơn at 12:47/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
val presentationModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { DashboardViewModel(get()) }
    viewModel { NotificationsViewModel(get()) }
}