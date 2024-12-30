package com.example.demo_structure

import com.example.demo_structure.app.di.dataModule
import com.example.demo_structure.app.di.domainModule
import com.example.demo_structure.app.di.networkModule
import com.example.demo_structure.app.di.presentationModule
import com.example.demo_structure.ui.home.HomeViewModel
import org.junit.Test

import org.junit.Assert.*
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest : KoinComponent{
    @Test
    fun `test Koin modules`() {
        startKoin {
            modules(
                listOf(networkModule, dataModule, domainModule, presentationModule)
            )
        }

        // Verify ViewModel creation
        val homeViewModel: HomeViewModel = get()
        assertNotNull(homeViewModel)
    }
}