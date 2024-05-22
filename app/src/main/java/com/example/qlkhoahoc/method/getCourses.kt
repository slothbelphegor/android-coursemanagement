package com.example.qlkhoahoc.method

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.qlkhoahoc.api.ApiClient
import com.example.qlkhoahoc.model.Course
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// phương thức async: return kết quả trả về bằng callback
@Composable
fun getBooks(callback: (MutableList<Course>) -> Unit) {
    var list = mutableListOf<Course>()
    val call = ApiClient.apiService.getAllCourses()

    call.enqueue(object: Callback<List<Course>> {
        override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
            if (response.isSuccessful) {
                // lấy body của response gán vào list
                response.body()?.let {
                    list = it as MutableList<Course>
                }
                // trả list nhận được ra ngoài
                callback.invoke(list)
            }
        }

        override fun onFailure(call: Call<List<Course>>, t: Throwable) {
            Log.e("error", "get failed")
        }
    })
}