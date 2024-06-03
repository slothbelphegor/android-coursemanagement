package com.example.qlkhoahoc.api

import com.example.qlkhoahoc.R
import com.example.qlkhoahoc.methods.auth.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    val client = OkHttpClient.Builder().apply {
        addInterceptor { chain ->
            val originalRequest = chain.request()
            val token = TokenManager.getToken(null)
            if (token != null) {
                val modifiedRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
                chain.proceed(modifiedRequest)
            } else {
                chain.proceed(originalRequest)
            }
        }
    }.build()
    // IP address của API

    private const val BASE_URL = "http://192.168.1.23:3000"
    // thiết lập kết nối đến API
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
    // cung cấp lời gọi đến các interface func trong Service
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}