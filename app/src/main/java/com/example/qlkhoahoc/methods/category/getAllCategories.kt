package com.example.qlkhoahoc.methods

import android.util.Log
import com.example.qlkhoahoc.api.ApiClient
import com.example.qlkhoahoc.model.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// phuong thuc async: return ket qua tra ve bang callBack
fun getAllCategories(callback: (MutableList<Category>) -> Unit) {
    var list = mutableListOf<Category>()
    val call = ApiClient.apiService.getAllCategories()

    call.enqueue(object : Callback<List<Category>> {
        override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
            Log.d("Check", "Check before if")
            if (response.isSuccessful) {
                Log.d("Check", "Check in if")
                response.body()?.let {
                    list = it as MutableList<Category>
                }
                Log.d("number of courses", list.size.toString())
                callback.invoke(list)
            } else {
                Log.d("Check", "Check in else")
                Log.e("Error", "Request failed with status code: ${response.code()}")
                response.errorBody()?.let {
                    Log.e("Error", "Error body: ${it.string()}")
                }
            }
        }

        override fun onFailure(call: Call<List<Category>>, t: Throwable) {
            Log.e("error", "Error fetch Categories", t)
        }

    })
}