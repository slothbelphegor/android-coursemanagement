package com.example.qlkhoahoc.methods.course

import android.util.Log
import com.example.qlkhoahoc.api.ApiClient
import com.example.qlkhoahoc.model.Course
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getCourseById(courseId: String, callback: (Course?) -> Unit) {
    val c = Course("Not Found", "Not Found", "Not Found", "Not Found", "Not Found", 0, 0, "Not Found")
    var rs: Course
    val call = ApiClient.apiService.getById(courseId)

    call.enqueue(object : Callback<List<Course>> {
        override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
            if (response.isSuccessful) {
                val courses = response.body()
                rs = if (!courses.isNullOrEmpty()) {
                    courses[0]
                } else {
                    c
                }
                callback.invoke(rs)
            } else {
                callback.invoke(c)
            }
        }

        override fun onFailure(call: Call<List<Course>>, t: Throwable) {
            Log.e("error", "Error fetch Courses", t)
        }
    })
}