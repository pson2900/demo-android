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

    fun validateBasicFirstName(firstName: String, numberValidate: Int): Boolean {
        val trimmedFirstName = firstName.trim()
        if (trimmedFirstName.length !in 1..numberValidate) return false

        // This regex allows letters, numbers, ', -, ., spaces, Vietnamese chars, BUT EXCLUDES emojis
        val regex = "^\\p{L}\\p{N}'\\-\\.\\s\\u00C0-\\u1FFF&&[^\\p{So}\\p{Sk}]+$".toRegex()
        return trimmedFirstName.matches(regex)
        return true //OK
    }

    fun validateBasicLastName(lastName: String): Boolean {
        if (lastName.length !in 1..50) return false
        if (!lastName.matches(Regex("^[a-zA-Z0-9'\\-\\.\\s]+$"))) return false
        return true
    }

    fun validateBasicPhoneNumber(phoneNumber: String): Boolean {
        val formattedPhoneNumber = phoneNumber.trim()

        return when {
            formattedPhoneNumber.startsWith("+84") -> {
                val digitsAfterPlus84 = formattedPhoneNumber.substring(3)
                digitsAfterPlus84.length == 9 && digitsAfterPlus84.all { it.isDigit() }
            }
            formattedPhoneNumber.startsWith("0") -> {
                formattedPhoneNumber.length == 10 && formattedPhoneNumber.all { it.isDigit() }
            }
            else -> false
        }
    }

    fun validateBasicEmail(email: String): Boolean {
        val emailRegex = "^[a-zA-Z0-9._%+-]+@([a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,}$".toRegex()
        return email.matches(emailRegex)
    }

    fun validateBasicPhoto(url: String): Boolean {
        return url.startsWith("https://internal link of X")
    }

    fun validateDesiredSalary(desiredSalary: Int): Boolean {
        return desiredSalary > 0
    }

    fun validateDesiredJobTitle(desiredJobTitle: String): Boolean {
        return desiredJobTitle.split("\\s+".toRegex()).size <= 2
    }

    fun validateDesiredCareerPath(desiredCareerPath: Int, careerPathIds: List<Int>): Boolean {
        return desiredCareerPath in careerPathIds
    }

    fun validateDesiredCity(desiredCity: Int, cityIds: List<Int>): Boolean {
        return desiredCity in cityIds
    }

    fun validateDesiredEmploymentType(desiredEmploymentType: Int, employmentTypeIds: List<Int>): Boolean {
        return desiredEmploymentType in employmentTypeIds
    }

    fun validateDesiredLocationType(desiredLocationType: Int, locationTypeIds: List<Int>): Boolean {
        return desiredLocationType in locationTypeIds
    }

    fun validateDesiredIndustry(desiredIndustries: List<Int>, industryIds: List<Int>): Boolean {
        if (desiredIndustries.size > 3) {
            return false
        }
        return desiredIndustries.all { it in industryIds }
    }

}