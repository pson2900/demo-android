package com.example.data.remote.network

import android.util.Log
import com.example.data.proto.DataStoreManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val dataStoreManager: DataStoreManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String = runBlocking {
            try {
                val token = dataStoreManager.getAuth().first()?.getBearerToken() ?: ""
                Log.e("Sang", "getting auth token: $token")
                token
            } catch (e: Exception) {
                Log.e("Sang", "Error getting auth token: ${e.message}")
                ""
            }
        }

        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
        if (token.isNotEmpty()) {
            newRequest.addHeader(AUTHORIZATION, token)
        }
        newRequest.addHeader(CONTENT_TYPE, "application/json")

        return chain.proceed(newRequest.build())
    }

    companion object {
         const val CONTENT_TYPE = "Content-Type"
         const val AUTHORIZATION = "Authorization"
    }
}