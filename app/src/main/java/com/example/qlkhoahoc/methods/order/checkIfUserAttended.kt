package com.example.qlkhoahoc.methods.order

import android.util.Log
import com.example.qlkhoahoc.api.ApiClient
import com.example.qlkhoahoc.model.Category
import com.example.qlkhoahoc.model.CheckResponse
import com.example.qlkhoahoc.model.CreateOrder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun checkIfUserAttended(token: String, createOrder: CreateOrder, callback: (Boolean) -> Unit) {
    val call = ApiClient.apiService.checkIfUserAttended(token, createOrder)
    call.enqueue(object : Callback<CheckResponse> {
        override fun onResponse(call: Call<CheckResponse>, response: Response<CheckResponse>) {
            if (response.isSuccessful) {
//                Log.d("createOrderResponse", "Response Code: ${response.code()}")
//                Log.d("createOrderResponse", "Headers: ${response.headers()}")
                val body = response.body()
                val attended = body?.attended == 1
                Log.d("attended", attended.toString());
                callback(attended)
//                if (body != null && attended) {
//                    callback.invoke(true)
//                } else {
//                    callback.invoke(false)
//                }
            } else {
                callback.invoke(false)
            }
        }

        override fun onFailure(call: Call<CheckResponse>, t: Throwable) {
            callback.invoke(false)
        }

    })
}