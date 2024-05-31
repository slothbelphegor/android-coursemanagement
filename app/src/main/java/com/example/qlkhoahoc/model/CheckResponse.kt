package com.example.qlkhoahoc.model

import com.google.gson.annotations.SerializedName

data class CheckResponse(
    @SerializedName("attended"   ) var attended   : Int?    = null,
)
