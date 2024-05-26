package com.example.qlkhoahoc.methods

import android.util.Log
import com.example.qlkhoahoc.api.ApiClient
import com.example.qlkhoahoc.model.Course
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// phuong thuc async: return ket qua tra ve bang callBack
fun getAllCourses(callback: (MutableList<Course>) -> Unit) {
    var list = mutableListOf<Course>()
    val call = ApiClient.apiService.getAllCourses()

    call.enqueue(object : Callback<List<Course>> {
        override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
            Log.d("Check", "Check before if")
            if (response.isSuccessful) {
                Log.d("Check", "Check in if")
                response.body()?.let {
                    list = it as MutableList<Course>
                }
                callback.invoke(list)
            } else {
                Log.d("Check", "Check in else")
                Log.e("Error", "Request failed with status code: ${response.code()}")
                response.errorBody()?.let {
                    Log.e("Error", "Error body: ${it.string()}")
                }
            }
        }

        override fun onFailure(call: Call<List<Course>>, t: Throwable) {
            Log.e("error", "Error fetch Courses", t)
        }

    })
}