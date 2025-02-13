package com.example.demo_structure

import com.example.demo_structure.app.di.applicationModule
import com.example.demo_structure.app.di.dataModule
import com.example.demo_structure.app.di.databaseModule
import com.example.demo_structure.app.di.domainModule
import com.example.demo_structure.app.di.presentationModule
import com.example.demo_structure.app.di.remoteModule
import com.example.domain.usecase.MyProfileUseCase
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.inject

/**
 * Created by Phạm Sơn at 11:17/11/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

class ValidationUtilsTest : KoinTest {
    private val myProfileUseCase: MyProfileUseCase by inject()

    @Before
    fun setup() {
        startKoin {
            androidLogger(Level.DEBUG)
            printLogger(Level.DEBUG)
            modules(
                listOf(applicationModule, remoteModule, dataModule, domainModule, presentationModule, databaseModule)
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `validateBasicFirstNameAndLastName`() {
        myProfileUseCase.apply {
            assertTrue(validateBasicFirstName("John Doe", 50))
            assertTrue(validateBasicFirstName("Jane-Doe", 50))
            assertTrue(validateBasicFirstName("O'Reilly", 50))
            assertTrue(validateBasicFirstName("John.Doe", 50))
            assertTrue(validateBasicFirstName("John123", 50))
            assertTrue(validateBasicFirstName("Nguyễn Văn A", 50))

            assertFalse(validateBasicFirstName("", 50))            // Empty
            assertFalse(validateBasicFirstName("  ", 50))         // Just whitespace
            assertFalse(validateBasicFirstName("John!Doe", 50))       // Invalid character
            assertFalse(validateBasicFirstName("John Doe".repeat(10), 50)) // Too long
        }
    }


    @Test
    fun `validateBasicPhoneNumber`() {
        assertTrue(myProfileUseCase.validateBasicPhoneNumber("+84399805901"))  // Valid International with +84
        assertTrue(myProfileUseCase.validateBasicPhoneNumber("0399805901"))   // Valid Vietnamese format

        assertFalse(myProfileUseCase.validateBasicPhoneNumber("7399805901")) // Missing + and 0 - INVALID
        assertFalse(myProfileUseCase.validateBasicPhoneNumber("+399805901") ) // Missing + and under 11 - INVALID
        assertFalse(myProfileUseCase.validateBasicPhoneNumber("399805901") ) // Under 11 - INVALID

        assertFalse(myProfileUseCase.validateBasicPhoneNumber("asda0399805901") ) // Not Number International
        assertFalse(myProfileUseCase.validateBasicPhoneNumber("0399 805 901") ) //With a Space
        assertFalse(myProfileUseCase.validateBasicPhoneNumber("") ) // empty string
        assertFalse(myProfileUseCase.validateBasicPhoneNumber("        0")) //Whitespace
        assertFalse(myProfileUseCase.validateBasicPhoneNumber(" ") ) // just white space
        assertFalse(myProfileUseCase.validateBasicPhoneNumber("+3998059010000") ) // to many values
    }

    @Test
    fun `validateBasicEmail`() {
        myProfileUseCase.apply {
            assertTrue(validateBasicEmail("test@example.com"))
            assertTrue(validateBasicEmail("test.test@example.com"))
            assertTrue(validateBasicEmail("test_test@example.com"))
            assertTrue(validateBasicEmail("test%test@example.com"))
            assertTrue(validateBasicEmail("test+test@example.com"))
            assertTrue(validateBasicEmail("test-test@example.com"))
            assertTrue(validateBasicEmail("test@example.museum"))
            assertTrue(validateBasicEmail("test@example.travel"))

            assertFalse(validateBasicEmail("test@example"))       // Missing TLD
            assertFalse(validateBasicEmail("test@.com"))         // Missing domain
            assertFalse(validateBasicEmail("@example.com"))       // Missing local part
            assertFalse(validateBasicEmail("test@example..com"))  // Consecutive dots in domain
            assertFalse(validateBasicEmail("test@.example.com"))  // Domain starts with a dot
            assertFalse(validateBasicEmail("test@-example.com"))  // Domain starts with a hyphen
            assertFalse(validateBasicEmail("test@example-.com"))  // Domain ends with a hyphen
            assertFalse(validateBasicEmail("test@example.c"))     // TLD too short
            assertFalse(validateBasicEmail("test@example.123"))   // TLD contains numbers
            assertFalse(validateBasicEmail("test@@example.com"))  // Double @
            assertFalse(validateBasicEmail("test@example.com "))  // Trailing space
            assertFalse(validateBasicEmail(" test@example.com"))  // Leading space
        }

    }

    @Test
    fun `validateBasicPhoto`() {
        assertTrue(myProfileUseCase.validateBasicPhoto("https://internal link of X/image.jpg"))
        assertFalse(myProfileUseCase.validateBasicPhoto("https://external.com/image.jpg"))
    }
}