package com.example.qlkhoahoc.model

data class ApiResponse(
    val status: String,
    val data: ResponseData
)

data class ResponseData(
    val fieldCount: Int,
    val affectedRows: Int,
    val insertId: Int,
    val info: String,
    val serverStatus: Int,
    val warningStatus: Int,
    val changedRows: Int
)