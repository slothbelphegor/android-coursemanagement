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

fun editCourse(
    token: String,
    courseId: String,
    updatedCourse: CourseAdd,
    callback: (Boolean?) -> Unit
) {
    val call = ApiClient.apiService.editCourse(token, courseId, updatedCourse)
    call.enqueue(object : Callback<ApiResponse> {
        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.status == "success") {
                    Log.d("Edited", "Success Response: ${responseBody.data}")
                    callback.invoke(true)
                } else {
                    Log.d("Edited", "Failed Response: ${response.errorBody()?.string()}")
                    if (responseBody != null) {
                        Log.d("Status?",responseBody.status)
                    }
                    callback.invoke(false)
                }
            } else {
                Log.d("Edited", "Failed Response: ${response.errorBody()?.string()}")
                callback.invoke(false)
            }
        }

        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            Log.e("EditCourse", "Failed to update course: ${t.message}")
            callback.invoke(false)
        }
    })
}