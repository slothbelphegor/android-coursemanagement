package com.example.qlkhoahoc.model

import com.google.gson.annotations.SerializedName

data class CourseAdd(
    @SerializedName("name") var course: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("video") var video: String? = null,
    @SerializedName("category_id") var category_id: Int? = null
)