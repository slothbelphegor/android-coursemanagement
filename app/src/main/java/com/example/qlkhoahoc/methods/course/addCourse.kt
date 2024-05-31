package com.example.qlkhoahoc.methods

import android.content.Context
import android.util.Log
import com.example.qlkhoahoc.api.ApiClient
import com.example.qlkhoahoc.model.ApiResponse
import com.example.qlkhoahoc.model.Course
import com.example.qlkhoahoc.model.CourseAdd
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// thêm dc thì callback == true, ngược lại false
fun addCourse(token: String, course: CourseAdd, callback: (Boolean) -> Unit) {
    val call = ApiClient.apiService.addCourse(token, course)
    call.enqueue(object : Callback<ApiResponse> {
        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.status == "success") {
                    Log.d("Add", "Success Response: ${responseBody.data}")
                    callback.invoke(true)
                } else {
                    Log.d("Add", "Failed Response: ${response.errorBody()?.string()}")
                    if (responseBody != null) {
                        Log.d("Status?",responseBody.status)
                    }
                    callback.invoke(false)
                }
            } else {
                Log.d("Add", "Failed Response: ${response.errorBody()?.string()}")
                callback.invoke(false)
            }
        }

        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            Log.d("Add", "Failed: ${t.message}")
            callback.invoke(false)
        }
    })
}

//    call.enqueue(object: Callback<ApiResponse> {
//        override fun onResponse(call: Call<Course>, response: Response<ApiResponse>) {
//            if (response.isSuccessful) {
//                rs = true
//                Log.d("Add","Success Response")
//            }
//            else {
//                rs = false
//                Log.d("Add","Failed Response")
//                Log.d("Response", response.toString())
//            }
//            callback.invoke(rs)
//        }
//
//        override fun onFailure(call: Call<Course>, t: Throwable) {
//            callback.invoke(false)
//            Log.d("Add","Failed")
//
//        }
//
//
//
//    })
