package com.example.qlkhoahoc.methods

import android.util.Log
import com.example.qlkhoahoc.api.ApiClient
import com.example.qlkhoahoc.model.Course
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun findCourse(searchTerm: String, condition: String, callback: (MutableList<Course>) -> Unit) {
    val courseNull = mutableListOf<Course>(
        Course(
            "", "Không tìm thấy", "Không tìm thấy", "Không tìm thấy",
            "Không tìm thấy", 0, 0, 0, "Không tìm thấy"
        )
    )
    var rs: MutableList<Course>
    if (condition == "false"){
        val call = ApiClient.apiService.findCourse(searchTerm, condition = "")
        call.enqueue(object : Callback<MutableList<Course>> {
            override fun onResponse(
                call: Call<MutableList<Course>>,
                response: Response<MutableList<Course>>
            ) {
                if (response.isSuccessful) {
                    val r = response.body()
                    Log.d("check", r.toString())
                    rs = if (r != null && r.isNotEmpty()) {
                        r
                    } else {
                        courseNull
                    }
                    callback.invoke(rs)
                    Log.d("Find", "Success Response")
                } else {
                    Log.d("Find", "Failed Response")
                    Log.d("Response", response.toString())
                    callback.invoke(courseNull)
                }
            }


            override fun onFailure(call: Call<MutableList<Course>>, t: Throwable) {
                Log.d("Find", "Failed")
                callback.invoke(courseNull)
            }

        })
    }
    else {
        val call = ApiClient.apiService.findCourse(searchTerm, condition)
        call.enqueue(object : Callback<MutableList<Course>> {
            override fun onResponse(
                call: Call<MutableList<Course>>,
                response: Response<MutableList<Course>>
            ) {
                if (response.isSuccessful) {
                    val r = response.body()
                    Log.d("check", r.toString())
                    rs = if (r != null && r.isNotEmpty()) {
                        r
                    } else {
                        courseNull
                    }
                    callback.invoke(rs)
                    Log.d("Find", "Success Response")
                } else {
                    Log.d("Find", "Failed Response")
                    Log.d("Response", response.toString())
                    callback.invoke(courseNull)
                }
            }


            override fun onFailure(call: Call<MutableList<Course>>, t: Throwable) {
                Log.d("Find", "Failed")
                callback.invoke(courseNull)
            }

        })
    }

}