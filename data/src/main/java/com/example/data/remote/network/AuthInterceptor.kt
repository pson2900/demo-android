package com.example.data.remote.network

import android.util.Log
import com.example.data.proto.DataStoreManager
import com.example.data.remote.network.HeaderInterceptor.Companion.AUTHORIZATION
import com.example.data.remote.response.BaseResponse
import com.example.data.remote.response.LoginResponse
import com.example.domain.model.Authentication
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Call
import retrofit2.Callback
import java.net.HttpURLConnection
import java.util.Calendar
import java.util.concurrent.CountDownLatch

class AuthInterceptor(
    private val dataStoreManager: DataStoreManager
) : Interceptor {
    val TAG = "AuthInterceptor"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        //case 401
//        if(response.code == HttpURLConnection.HTTP_UNAUTHORIZED){
//            Log.d(TAG, "HTTP UNAUTHORIZED")
//        }
        //check token
        val authentication = runBlocking { dataStoreManager.getAuth().firstOrNull() }
        if (authentication != null) {
            val currentTime = Calendar.getInstance().timeInMillis
            val expiresTime = authentication.expiresTime
            val refreshExpiresTime = authentication.refreshExpiresTime
            //case refresh token
            if (refreshExpiresTime < currentTime) {
                // Refresh token has expired, stop the request and return an error response
                Log.d(TAG, "Refresh token expired. Stopping request.")
                // You might want to clear the authentication data here
                runBlocking {
                    dataStoreManager.clearAll()
                }
                // return chain.proceed(request.newBuilder().build())
                val errorResponseBody = createErrorResponseBody("login")
                return Response.Builder()
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .code(HttpURLConnection.HTTP_UNAUTHORIZED)
                    .message("Refresh token expired")
                    .body(errorResponseBody)
                    .build()
            }
            //case expired token
            if (expiresTime < currentTime) {
                Log.d(TAG, "Token expired!")
                val newAuth = refreshToken() // Refresh the token
                if (newAuth != null) {
                    // Retry the request with the new token
                    val nearerToken = newAuth.getBearerToken()
                    if (!nearerToken.isNullOrEmpty()) {
                        val newRequest = request.newBuilder()
                            .header(AUTHORIZATION, nearerToken)
                            .build()
                        // Return the response from the new request
                        return chain.proceed(newRequest)
                    }
                } else {
                    Log.d(TAG, "Token refresh failed!")
                    // Return the original request if refresh failed
                    return chain.proceed(request)
                }
            }
        }
        // Return the response from the original request
        return chain.proceed(request)
    }

    private fun refreshToken(): Authentication? {
        val oldToken = runBlocking { dataStoreManager.getAuth().firstOrNull()?.refreshToken }
        if (oldToken == null) {
            Log.d(TAG, "Refresh token request failed: No refresh token found")
            return null
        }
        Log.d(TAG, "oldToken: $oldToken")
        val apiService = RetrofitClient(dataStoreManager, false).createService<ApiService>()
        val latch = CountDownLatch(1)
        var result: Authentication? = null
        val body = HashMap<String, String>()
        body["refreshToken"] = oldToken

        apiService.renewToken(body).enqueue(object : Callback<BaseResponse<LoginResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<LoginResponse>>,
                response: retrofit2.Response<BaseResponse<LoginResponse>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data?.toDomain()
                    if (data != null) {
                        Log.d(TAG, "Refresh token isSuccessful")
                        runBlocking {
                            dataStoreManager.saveAuth(data)
                        }
                        Log.d(TAG, "newToken: ${data.refreshToken}")
                        result = data
                    } else {
                        Log.d(TAG, "Refresh token request failed: Empty response body")
                    }
                } else {
                    Log.d(
                        TAG,
                        "Refresh token request failed: ${response.code()} - ${response.message()}"
                    )
                }
                latch.countDown()
            }

            override fun onFailure(call: Call<BaseResponse<LoginResponse>>, t: Throwable) {
                Log.d(TAG, "Refresh token request failed: ${t.message}")
                latch.countDown()
            }
        })

        try {
            latch.await() // Wait for the asynchronous call to complete
        } catch (e: InterruptedException) {
            Log.e(TAG, "Error waiting for refresh token: ${e.message}")
            Thread.currentThread().interrupt()
        }

        return result
    }

    fun createErrorResponseBody(errorCode: String): ResponseBody {
        val jsonString = "{\"errorCode\": \"$errorCode\"}"
        return jsonString.toResponseBody("application/json; charset=utf-8".toMediaTypeOrNull())
    }
}