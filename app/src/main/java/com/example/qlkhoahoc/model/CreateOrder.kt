package com.example.qlkhoahoc.model

import com.google.gson.annotations.SerializedName

data class CreateOrder(
    @SerializedName("course_id"   ) var course_id: String? = null,
)
