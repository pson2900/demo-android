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
               val token = dataStoreManager.getAuth().first()?.getBearerToken()?:""
                Log.d("Sang","getting auth token: $token")
                token
            } catch (e: Exception) {
                Log.d("Sang","Error getting auth token: ${e.message}")
                "" // Return an empty string in case of error
            }
        }

        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .apply {
                if(token.isNotEmpty()){
                    addHeader("Authorization", token)
                }
            }
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()
        return chain.proceed(newRequest)
    }
}