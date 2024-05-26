package com.example.qlkhoahoc.methods

import android.util.Log
import com.example.qlkhoahoc.api.ApiClient
import com.example.qlkhoahoc.model.Course
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// thêm dc thì callback == true, ngược lại false
fun addCourse(course: Course, callback: (Boolean) -> Unit) {
    var rs : Boolean
    val call = ApiClient.apiService.addCourse(course)

    call.enqueue(object: Callback<Course> {
        override fun onResponse(call: Call<Course>, response: Response<Course>) {
            if (response.isSuccessful) {
                rs = true
                Log.d("Add","Success")
            }
            else {
                rs = false
                Log.d("Add","Failed")
            }
            callback.invoke(rs)
        }

        override fun onFailure(call: Call<Course>, t: Throwable) {
            callback.invoke(false)
        }

    })
}