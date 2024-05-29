package com.example.qlkhoahoc.methods.auth

import android.content.Context
import android.widget.Toast
import com.example.qlkhoahoc.api.ApiClient
import com.example.qlkhoahoc.model.LogoutResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun logout(token: String, context: Context, callback: (Boolean, String) -> Unit) {
    val call = ApiClient.apiService.logout(token)

    call.enqueue(object : Callback<LogoutResponse> {
        override fun onResponse(call: Call<LogoutResponse>, response: Response<LogoutResponse>) {
            if (response.isSuccessful) {
                TokenManager.clearToken(context)
                Toast.makeText(context, "Đăng xuất thành công", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Lỗi khi đăng xuất", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
            Toast.makeText(context, "Lỗi khi đăng xuất", Toast.LENGTH_SHORT).show()
        }
    })
}