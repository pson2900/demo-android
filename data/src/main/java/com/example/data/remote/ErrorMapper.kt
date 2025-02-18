package com.example.data.remote

import android.database.sqlite.SQLiteException
import android.util.Log
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

/**
 * Created by Phạm Sơn at 11:29/29/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
sealed class AppException(message: String, cause: Throwable? = null) : RuntimeException(message, cause) {

    // Network Errors
    class NetworkException(message: String, cause: Throwable? = null) : AppException(message, cause)
    class TimeoutException(message: String = "Request timeout", cause: Throwable? = null) : AppException(message, cause)
    class NoInternetException(message: String = "No internet", cause: Throwable? = null) : AppException(message, cause)
    class ConnectionClosedException(message: String = "Connection closed", cause: Throwable? = null) : AppException(message, cause)
    class SSLException(message : String = "SSL Error", cause: Throwable? = null): AppException(message, cause)

    // Server Errors (HTTP)
    class ServerException(val code: Int, message: String, cause: Throwable? = null) : AppException(message, cause)
    class UnauthorizedException(message: String, cause: Throwable? = null) : AppException(message, cause)
    class NotFoundException(message: String, cause: Throwable? = null) : AppException(message, cause)
    class ConflictException(message: String, cause: Throwable? = null) : AppException(message, cause)
    class ClientException(message: String, cause: Throwable? = null) : AppException(message, cause)
    class ServerUnavailableException(message: String, cause: Throwable? = null): AppException(message, cause)
    class BadGatewayException(message: String, cause: Throwable? = null): AppException(message, cause)
    // Data Errors
    class DataParsingException(message: String, cause: Throwable? = null) : AppException(message, cause)
    class NoDataException(message: String = "No data", cause: Throwable? = null) : AppException(message, cause)
    class DataTransformException(message: String, cause: Throwable? = null): AppException(message, cause)
    class DataMappingException(message: String, cause: Throwable? = null): AppException(message, cause)
    class DataValidationException(message: String, cause: Throwable? = null): AppException(message, cause)

    //Database Error
    class DatabaseException(message: String, cause: Throwable? = null) : AppException(message, cause)
    // Generic/Unknown Errors
    class UnknownException(message: String = "An Unknown exception", cause: Throwable? = null): AppException(message,cause)
    class CustomException(message: String, cause: Throwable? = null): AppException(message, cause)

    // Gson Parsing Exceptions
    class JsonParsingException(message: String, cause: Throwable? = null) : AppException(message, cause)
    class JsonSyntaxException(message: String, cause: Throwable? = null): AppException(message, cause)

    //File system Errors
    class FileReadException(message: String, cause: Throwable? = null) : AppException(message, cause)
    class FileWriteException(message: String, cause: Throwable? = null) : AppException(message, cause)
    class FileAccessException(message: String, cause: Throwable? = null) : AppException(message, cause)
    class FileExistException(message: String, cause: Throwable? = null) : AppException(message, cause)
    class FileNotFoundException(message: String, cause: Throwable? = null) : AppException(message, cause)
}

object ErrorMapper {
    fun toAppException(exception: Throwable): AppException {
        return when (exception) {
            is IOException -> {
                when (exception) {
                    is SocketTimeoutException -> AppException.TimeoutException(exception.message ?: "Socket Timeout exception")
                    is UnknownHostException -> AppException.NoInternetException(exception.message ?: "No Internet")
                    is SSLException -> AppException.SSLException(exception.message ?: "SSL exception")
                    else -> AppException.NetworkException(exception.message ?: "IO Exception")
                }
            }
            is retrofit2.HttpException -> {
                val message = exception.response()?.errorBody()?.string()?: "An HTTP error occurred."
                when (exception.code()) {
                    401 -> AppException.UnauthorizedException(message)
                    404 -> AppException.NotFoundException(message)
                    409 -> AppException.ConflictException(message)
                    in 400..499 -> AppException.ClientException(message)
                    in 500..599 ->  {
                        when(exception.code()){
                            502 -> AppException.BadGatewayException(message)
                            503 -> AppException.ServerUnavailableException(message)
                            else -> AppException.ServerException(exception.code(), message)
                        }

                    }
                    else -> AppException.ServerException(exception.code(), message)
                }
            }
            is SQLiteException -> AppException.DatabaseException(
                exception.message ?: "DB exception"
            )

            is JsonParseException -> AppException.JsonParsingException(exception.message ?: "Json Parsing error", exception)
            is JsonSyntaxException -> AppException.JsonSyntaxException(exception.message ?: "Json syntax error", exception)
            is java.io.FileNotFoundException ->  AppException.FileNotFoundException(exception.message ?: "File not found exception", exception)
            is FileSystemException ->  AppException.FileAccessException(exception.message ?: "File system exception", exception)
            is NoSuchFileException ->  AppException.FileReadException(exception.message ?: "File read exception", exception)
            else -> AppException.UnknownException(exception.message ?: "An unknown error")
        }
    }
}