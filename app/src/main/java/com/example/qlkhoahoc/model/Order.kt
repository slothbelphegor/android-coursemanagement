package com.example.qlkhoahoc.model

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("order_id"  ) var orderId  : String? = null,
    @SerializedName("user_id"   ) var userId   : String? = null,
    @SerializedName("course_id" ) var courseId : String? = null,
)
