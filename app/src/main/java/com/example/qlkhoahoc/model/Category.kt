package com.example.qlkhoahoc.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("category_id"   ) var categoryId   : Int?    = null,
    @SerializedName("category_name" ) var categoryName : String? = null,
)
