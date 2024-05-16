package com.example.qlkhoahoc.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    // IP address của API
    const val BASE_URL = "http://192.168.43.247:3000"
    // thiết lập kết nối đến API
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    // cung cấp lời gọi đến các interface func trong Service
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}