package com.example.qlkhoahoc.methods

import android.util.Log
import com.example.qlkhoahoc.api.ApiClient
import com.example.qlkhoahoc.model.Course
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun findCourse(searchTerm: String, condition: String, callback: (MutableList<Course>) -> Unit) {
    val courseNull = mutableListOf(
        Course(
            "", "Không tìm thấy", "Không tìm thấy", "Không tìm thấy",
            "Không tìm thấy", 0, 0, 0, "Không tìm thấy"
        )
    )

    val call = ApiClient.apiService.findCourse(searchTerm, condition)
    call.enqueue(object : Callback<MutableList<Course>> {
        override fun onResponse(
            call: Call<MutableList<Course>>,
            response: Response<MutableList<Course>>
        ) {
            val rs = if (response.isSuccessful && response.body().orEmpty().isNotEmpty()) {
                Log.e("Error", condition)
                response.body()!!
            } else {
                courseNull
            }
            callback.invoke(rs)
        }

        override fun onFailure(call: Call<MutableList<Course>>, t: Throwable) {
            Log.d("Find", "Failed")
            callback.invoke(courseNull)
        }
    })
}