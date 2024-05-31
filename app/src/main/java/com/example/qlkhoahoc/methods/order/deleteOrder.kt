package com.example.qlkhoahoc.methods.order

import android.util.Log
import com.example.qlkhoahoc.api.ApiClient
import com.example.qlkhoahoc.model.ApiResponse
import com.example.qlkhoahoc.model.CreateOrder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun deleteOrder(token: String, courseId: String, callback: (Boolean) -> Unit) {
    val call = ApiClient.apiService.deleteOrder(token, courseId)
    call.enqueue(object: Callback<ApiResponse>{
        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.status == "success") {
                    Log.d("Deleted", "Success Response: ${responseBody.data}")
                    callback.invoke(true)
                } else {
                    Log.d("Deleted", "Failed Response: ${response.errorBody()?.string()}")
                    if (responseBody != null) {
                        Log.d("Status?",responseBody.status)
                    }
                    callback.invoke(false)
                }
            } else {
                Log.d("Deleted", "Failed Response: ${response.errorBody()?.string()}")
                callback.invoke(false)
            }
        }

        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            Log.e("Deleted", "Failed to delete course: ${t.message}")
            callback.invoke(false)
        }

    })
}