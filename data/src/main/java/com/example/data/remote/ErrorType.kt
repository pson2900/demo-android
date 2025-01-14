package com.example.data.remote

import android.database.sqlite.SQLiteException
import android.net.http.HttpException
import java.io.IOException

/**
 * Created by Phạm Sơn at 11:29/29/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
sealed class ErrorType {
    object NetworkError : ErrorType()
    data class ServerError(val code: Int, val message: String) : ErrorType()
    object DatabaseError : ErrorType()
    object UnknownError : ErrorType()
    data class CustomError(val message: String) : ErrorType()

    fun showMessage(): String {
        return ""
    }
}


class ErrorMapper {
    fun mapToErrorType(exception: Throwable): ErrorType {
        return when (exception) {
            is IOException -> ErrorType.NetworkError
//            is HttpException -> {
////                val code = exception.hashCode()
//                val message = exception.message.toString()
//                ErrorType.ServerError(400, message)
//            }
            is SQLiteException -> ErrorType.DatabaseError
            else -> ErrorType.UnknownError
        }
    }
}
