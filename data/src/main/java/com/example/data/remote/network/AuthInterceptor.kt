package com.example.data.remote.network

import com.example.data.proto.DataStoreManager
import com.example.domain.model.Authentication
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val dataStoreManager: DataStoreManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (response.code == 401) {
            chain.call().cancel()
            // Token expired!
            println("Token expired!")
//            val newAuth = refreshToken() // Refresh the token
//            if (newAuth != null) {
//                // Retry the request with the new token
//                val newRequest = request.newBuilder()
//                val nearerToken = newAuth.getBearerToken()
//                if (!nearerToken.isNullOrEmpty()) {
//                    newRequest.header("Authorization", nearerToken)
//                }
//                return chain.proceed(newRequest.build())
//            } else {
//                // Handle refresh failure (e.g., logout)
//                println("Token refresh failed!")
//                // You might want to log the user out here
//            }
        }

        return response
    }

    private fun refreshToken( authRepository: AuthRepository): Authentication? = runBlocking {
        val oldToken = dataStoreManager.getAuth().firstOrNull()?.refreshToken
        if (oldToken != null) {
            val response = try {
                authRepository.renewToken(oldToken)
            } catch (e: Exception) {
                println("Error refreshing token: ${e.message}")
                return@runBlocking null
            }
            val auth = response.firstOrNull()
            if (auth != null) {
                dataStoreManager.saveAuth(auth)
                auth
            } else {
                println("Refresh token request failed")
                null
            }
        } else {
            println("Refresh token request failed")
            null
        }
    }
}