package com.example.domain.usecase

import com.example.domain.model.MyProfile
import com.example.domain.repository.MyProfileRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Phạm Sơn at 14:02/17/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class MyProfileUseCase(private val myProfileRepository: MyProfileRepository) {
    suspend fun getMyProfile(): Flow<MyProfile> {
        return myProfileRepository.getMyProfile()
    }

    suspend fun getListItem(): Flow<List<String>> {
        delay(6000L)
        return flow {
            val items = listOf("Item 1", "Item 2", "Item 3")
            emit(items)
        }
    }

    fun validateBasicFirstName(firstName: String) {
        require(firstName.length in 1..50) { "First name must be between 1 and 50 characters." }
        require(firstName.matches(Regex("^[a-zA-Z0-9'\\-\\.\\s]+$"))) { "First name can only contain letters, numbers, apostrophes ('), hyphens (-), periods (.), and spaces." }
    }

    fun validateBasicLastName(lastName: String) {
        require(lastName.length in 1..50) { "Last name must be between 1 and 50 characters." }
        require(lastName.matches(Regex("^[a-zA-Z0-9'\\-\\.\\s]+$"))) { "Last name can only contain letters, numbers, apostrophes ('), hyphens (-), periods (.), and spaces." }
    }

    fun validateBasicPhoneNumber(phoneNumber: String) {
        require(phoneNumber.length <= 11) { "Phone number must be at most 11 digits." }
        require(phoneNumber.matches(Regex("^[0-9]+$"))) { "Phone number must only contain digits." }
    }

    fun validateBasicEmail(email: String) {
        require(email.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))) { "Invalid email format." }
    }

    fun validateBasicPhoto(url: String) {
        require(url.startsWith("https://internal link of X")) { "Photo URL must start with 'https://internal link of X'." }

    }
}