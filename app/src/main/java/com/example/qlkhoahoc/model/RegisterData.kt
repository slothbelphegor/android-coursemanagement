package com.example.qlkhoahoc.model

import com.google.gson.annotations.SerializedName

data class RegisterData(
    @SerializedName("email") var email: String? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("retypePassword") var retypePassword: String? = null,
)
