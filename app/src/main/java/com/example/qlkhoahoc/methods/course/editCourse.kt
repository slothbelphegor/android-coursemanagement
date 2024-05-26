package com.example.qlkhoahoc.methods.course

import android.content.Context
import android.util.Log
import com.example.qlkhoahoc.api.ApiClient
import com.example.qlkhoahoc.methods.auth.TokenManager
import com.example.qlkhoahoc.model.Course
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun editCourse(context: Context, courseId: String, updatedCourse: Course, callback: (Course?) -> Unit) {
    val token = TokenManager.getToken(context)
    token?.let { authToken ->
        val call = ApiClient.apiService.editCourse(courseId, updatedCourse, authToken)

        call.enqueue(object : Callback<Course> {
            override fun onResponse(call: Call<Course>, response: Response<Course>) {
                if (response.isSuccessful) {
                    val course = response.body()
                    callback.invoke(course)
                } else {
                    Log.e("Error", "Request failed with status code: ${response.code()}")
                    response.errorBody()?.let {
                        Log.e("Error", "Error body: ${it.string()}")
                    }
                    callback.invoke(null)
                }
            }

            override fun onFailure(call: Call<Course>, t: Throwable) {
                Log.e("Error", "Error editing course", t)
                callback.invoke(null)
            }
        })
    } ?: run {
        Log.e("Error", "Token not found")
        callback.invoke(null)
    }
}