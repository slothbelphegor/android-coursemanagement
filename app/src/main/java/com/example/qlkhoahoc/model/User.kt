package com.example.qlkhoahoc.model

import com.google.gson.annotations.SerializedName


data class User (
    @SerializedName("user_id"   ) var userId   : String? = null,
    @SerializedName("username"  ) var username : String? = null,
    @SerializedName("password"  ) var password : String? = null,
    @SerializedName("is_active" ) var isActive : Int?    = null,
    @SerializedName("role_id"   ) var roleId   : Int?    = null
)