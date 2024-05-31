package com.example.qlkhoahoc.methods.order

import android.util.Log
import com.example.qlkhoahoc.api.ApiClient
import com.example.qlkhoahoc.model.ApiResponse
import com.example.qlkhoahoc.model.Course
import com.example.qlkhoahoc.model.CourseAdd
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getOrderOfUser(token: String, callback: (MutableList<Course>) -> Unit) {
    var list = mutableListOf<Course>()
    val call = ApiClient.apiService.getOrderOfUser(token)
    call.enqueue(object : Callback<MutableList<Course>> {
        override fun onResponse(
            call: Call<MutableList<Course>>,
            response: Response<MutableList<Course>>
        ) {
            if (response.isSuccessful) {
                response.body()?.let {
                    list = it
                }
                callback.invoke(list)
            } else {
                Log.e("Error", "Request failed with status code: ${response.code()}")
                response.errorBody()?.let {
                    Log.e("Error", "Error body: ${it.string()}")
                }
            }
        }

        override fun onFailure(call: Call<MutableList<Course>>, t: Throwable) {
            Log.e("error", "Error fetch Courses", t)
        }

    })
}