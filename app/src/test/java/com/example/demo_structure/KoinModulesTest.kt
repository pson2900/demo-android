package com.example.demo_structure

/**
 * Created by Phạm Sơn at 11:11/29/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
import com.example.data.remote.network.ApiService
import com.example.data.remote.network.RetrofitClient
import com.example.data.repository.HomeRepositoryImpl
import com.example.demo_structure.screen.dashboard.DashboardViewModel
import com.example.demo_structure.screen.home.HomeViewModel
import com.example.demo_structure.screen.notification.NotificationsViewModel
import com.example.domain.HomeRepository
import com.example.domain.usecase.GetClientUseCase
import com.example.domain.usecase.HomeUseCase
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject

class KoinModulesTest : KoinComponent{

    // Tạo mock cho những dependencies cần thiết
    val networkModule = module {
        single<ApiService> { RetrofitClient.createService<ApiService>() }
    }

    val dataModule = module {
        single<HomeRepository> { HomeRepositoryImpl(get(), get()) }
    }

    val domainModule = module {
        factory { GetClientUseCase(get()) }
        factory { HomeUseCase(get()) }
    }

    val presentationModule = module {
        viewModel { HomeViewModel(get()) }
        viewModel { DashboardViewModel() }
        viewModel { NotificationsViewModel() }
    }

    @Before
    fun setUp() {
        // Khởi tạo Koin với tất cả các module
        startKoin {
            modules(
                networkModule,
                dataModule,
                domainModule,
                presentationModule
            )
        }
    }

    @Test
    fun `test Koin modules`() {
        val homeViewModel: HomeViewModel = get()
        assertNotNull(homeViewModel)

        val homeRepository: HomeRepository = get()
        assertNotNull(homeRepository)

        val homeUseCase: HomeUseCase = get()
        assertNotNull(homeUseCase)

        val getClientUseCase: GetClientUseCase = get()
        assertNotNull(getClientUseCase)
    }

    @Test
    fun `test Koin inject ViewModel`() {
        val homeViewModel: HomeViewModel by inject(HomeViewModel::class.java)
        assertNotNull(homeViewModel)

        val dashboardViewModel: DashboardViewModel by inject(DashboardViewModel::class.java)
        assertNotNull(dashboardViewModel)

        val notificationsViewModel: NotificationsViewModel by inject(DashboardViewModel::class.java)
        assertNotNull(notificationsViewModel)
    }

    @After
    fun tearDown() {
        // Dừng Koin sau khi test xong
        stopKoin()
    }
}
