package com.lifeidroid.wanandroid.http


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommonBean<T>(
    @Json(name = "data")
    var `data`: T? = null,
    @Json(name = "errorCode")
    var errorCode: Int = 0,
    @Json(name = "errorMsg")
    var errorMsg: String? = ""
)