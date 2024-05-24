package com.example.qlkhoahoc.methods.auth

import android.util.Log
import com.example.qlkhoahoc.api.ApiClient
import com.example.qlkhoahoc.model.LoginData
import com.example.qlkhoahoc.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Context

fun login(context: Context, loginData: LoginData, callback: (Boolean, String) -> Unit) {
    val call = ApiClient.apiService.login(loginData)

    call.enqueue(object : Callback<LoginResponse> {
        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
            if (response.isSuccessful) {
                // Successful login
                Log.d("Login", "Successful")
                val token = response.body()?.token
                if (token != null) {
                    TokenManager.saveToken(context, token)
                    callback.invoke(true, token)
                    TokenManager.getToken(context)?.let { Log.d("Token", it) }
                }
            } else {
                // Failed login
                Log.d("Login", "Failed")
                callback.invoke(false, null.toString())
            }
        }

        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            // Network failure or other errors
            Log.e("Login", "Error: ${t.message}", t)
            callback.invoke(false, null.toString())
        }
    })
}