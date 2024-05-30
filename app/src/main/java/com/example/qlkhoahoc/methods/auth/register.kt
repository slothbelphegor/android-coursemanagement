package com.example.qlkhoahoc.methods.auth

import android.content.Context
import android.util.Log
import com.example.qlkhoahoc.api.ApiClient
import com.example.qlkhoahoc.model.ApiResponse
import com.example.qlkhoahoc.model.RegisterData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun register(registerData: RegisterData, callback: (Boolean) -> Unit) {
    val call = ApiClient.apiService.register(registerData)

    call.enqueue(object : Callback<ApiResponse> {
        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
            if (response.isSuccessful) {
//                Log.d("Response data", response.body().toString())
                val responseBody = response.body()
                if (responseBody != null && responseBody.status == "success") {
                    Log.d("Register", "Success Response: ${responseBody.data}")
                    callback.invoke(true)
                } else {
                    Log.d("Register", "Failed Response: ${response.errorBody()?.string()}")
                    if (responseBody != null) {
                        Log.d("Status?",responseBody.status)
                    }
                    callback.invoke(false)
                }
            } else {
                Log.d("Register", "Failed Response: ${response.errorBody()?.string()}")
                callback.invoke(false)
            }
        }

        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            Log.e("Register", "Failed to register: ${t.message}")
            callback.invoke(false)
        }
    })
}