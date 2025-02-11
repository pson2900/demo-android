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

    fun validateFirstName(firstName: String) {}
    fun validateLastName(lastName: String) {}
    fun validatePhoneNumber(phoneNumber: String) {}
    fun validateEmail(email: String) {}
    fun validatePhoto(url: String) {}
}