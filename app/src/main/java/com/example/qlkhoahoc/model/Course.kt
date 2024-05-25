package com.example.qlkhoahoc.model

import com.google.gson.annotations.SerializedName

data class Course(
    @SerializedName("course_id"   ) var courseId    : String? = null,
    @SerializedName("course_name" ) var courseName  : String? = null,
    @SerializedName("description" ) var description : String? = null,
    @SerializedName("image"       ) var image       : String? = null,
    @SerializedName("video"       ) var video       : String? = null,
    @SerializedName("category_id" ) var categoryId  : Int?    = null,
    @SerializedName("is_deleted"  ) var isDeleted   : Int?    = null,
    val categoryName: String
)
