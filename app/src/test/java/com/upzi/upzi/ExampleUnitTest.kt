package com.upzi.upzi

import com.upzi.upzi.app.di.dataModule
import com.upzi.upzi.app.di.domainModule
import com.upzi.upzi.app.di.applicationModule
import com.upzi.upzi.app.di.presentationModule
import com.upzi.upzi.screen.home.HomeViewModel
import org.junit.Test

import org.junit.Assert.*
import org.koin.core.context.startKoin
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
                listOf(applicationModule, dataModule, domainModule, presentationModule)
            )
        }

        // Verify ViewModel creation
        val homeViewModel: HomeViewModel = get()
        assertNotNull(homeViewModel)
    }
}