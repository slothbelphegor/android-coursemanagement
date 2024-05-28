package com.example.qlkhoahoc.methods.course

import android.content.Context
import android.util.Log
import com.example.qlkhoahoc.api.ApiClient
import com.example.qlkhoahoc.methods.auth.TokenManager
import com.example.qlkhoahoc.model.ApiResponse
import com.example.qlkhoahoc.model.Course
import com.example.qlkhoahoc.model.CourseAdd
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun editCourse(token: String, context: Context, courseId: String, updatedCourse: CourseAdd, callback: (ApiResponse) -> Unit) {
    val call = ApiClient.apiService.editCourse(token, courseId, updatedCourse)
    call.enqueue(object : Callback<ApiResponse> {
        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {

        }

        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {

        }

    })
}