package com.example.qlkhoahoc.api


import com.example.qlkhoahoc.model.Course
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers("Accept:application/json")
    // read all
    @GET("courses") // gọi router.get bên phía api
    fun getAllCourses(): Call<List<Course>>

    // create
    @POST("courses")
    fun addCourse(@Body book: Course): Call<Course>

    // get by id (search)
    @GET("courses/{id}")
    fun getById(@Path("id") id:Int): Call<List<Course>> // nếu chỉ trả về 1 book sẽ gây ra lỗi khó sửa
}