package com.example.qlkhoahoc.methods.order

import android.util.Log
import com.example.qlkhoahoc.api.ApiClient
import com.example.qlkhoahoc.model.ApiResponse
import com.example.qlkhoahoc.model.CreateOrder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun createOrder(token: String, createOrder: CreateOrder, callback: (Boolean) -> Unit) {
    val call = ApiClient.apiService.createOrder(token, createOrder)
//    Log.d("createOrderRequest", "Token: $token")
//    Log.d("createOrderRequest", "Request Body: $createOrder")
    call.enqueue(object : Callback<ApiResponse> {
        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
            if (response.isSuccessful) {
//                Log.d("createOrderResponse", "Response Code: ${response.code()}")
//                Log.d("createOrderResponse", "Headers: ${response.headers()}")
                val responseBody = response.body()
                if (responseBody != null && responseBody.status == "success") {
                    Log.d("Attended to course", "Success Response: ${responseBody.data}")
                    callback.invoke(true)
                } else {
                    Log.d("Attended to course", "Failed Response: ${response.errorBody()?.string()}")
                    if (responseBody != null) {
                        Log.d("Status?",responseBody.status)
                    }
                    callback.invoke(false)
                }
            } else {
                Log.d("Attended to course", "Failed Response: ${response.errorBody()?.string()}")
                callback.invoke(false)
            }
        }
        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            Log.d("Attended to course", "Failed: ${t.message}")
            callback.invoke(false)
        }
    })
}