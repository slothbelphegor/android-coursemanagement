package com.example.qlkhoahoc.api


import com.example.qlkhoahoc.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers("Accept:application/json")
    // read all
    @GET("courses") // gọi router.get bên phía api
    fun getAllCourses(): Call<List<Course>>

    // create
    @POST("courses")
    fun addCourse(@Header("Authorization") token: String, @Body course: CourseAdd): Call<ApiResponse>

    // get by id (search)
    @GET("courses/{id}")
    fun getById(@Path("id") id:String): Call<List<Course>> // nếu chỉ trả về 1 book sẽ gây ra lỗi khó sửa

    // Edit course
    @PUT("courses/{id}")
    fun editCourse(@Header("Authorization") token: String, @Path("id") id: String, @Body course: CourseAdd): Call<ApiResponse>

    @POST("auth/login")
    fun login(@Body loginData: LoginData): Call<LoginResponse>

    @POST("auth/logout")
    fun logout(@Header("Authorization") token: String): Call<LogoutResponse>

    @GET("categories")
    fun getAllCategories(): Call<List<Category>>

    @GET("courses/search")
    fun findCourse(@Query("searchTerm") searchTerm: String): Call<MutableList<Course>>


}