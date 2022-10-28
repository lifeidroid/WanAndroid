package com.lifeidroid.wanandroid.model.entity.net

import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/18
 *     desc   : 首页banner https://www.wanandroid.com/banner/json
 *     version: 1.0
 * </pre>
 */
@JsonClass(generateAdapter = true)
data class BannerEntity(
    @Json(name = "desc")
    var desc: String? = "",
    @Json(name = "id")
    var id: Int? = 0,
    @Json(name = "imagePath")
    var imagePath: String? = "",
    @Json(name = "isVisible")
    var isVisible: Int? = 0,
    @Json(name = "order")
    var order: Int? = 0,
    @Json(name = "title")
    var title: String? = "",
    @Json(name = "type")
    var type: Int? = 0,
    @Json(name = "url")
    var url: String? = ""
)