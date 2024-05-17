package com.example.qlkhoahoc.model

import com.google.gson.annotations.SerializedName

data class Role(
    @SerializedName("role_id"   ) var roleId   : String? = null,
    @SerializedName("role_name" ) var roleName : String? = null,
)
