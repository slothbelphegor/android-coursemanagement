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

    @POST("auth/register")
    fun register(@Body registerData: RegisterData): Call<ApiResponse>

    @GET("categories")
    fun getAllCategories(): Call<List<Category>>

    @GET("courses/search")
    fun findCourse(@Query("searchTerm") searchTerm: String, @Query("condition")condition: String?): Call<MutableList<Course>>

    @POST("orders")
    fun createOrder(@Header("Authorization") token: String, @Body createOrder: CreateOrder): Call<ApiResponse>

    @GET("orders/attended")
    fun getOrderOfUser(@Header("Authorization") token: String): Call<MutableList<Course>>

    @POST("orders/check")
    fun checkIfUserAttended(@Header("Authorization") token: String, @Body createOrder: CreateOrder): Call<CheckResponse>

    @DELETE("orders/delete/{id}")
    fun deleteOrder(@Header("Authorization") token: String, @Path("id") id: String): Call<ApiResponse>

    @GET("courses/popular")
    fun sortCourses(@Query("sort") sort: String): Call<MutableList<Course>>
}