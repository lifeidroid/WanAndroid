package com.lifeidroid.wanandroid.model.entity.net
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/28
 *     desc   : 获取个人积分，需要登录后访问
 *     version: 1.0
 * </pre>
 */
@JsonClass(generateAdapter = true)
data class MyCoinDetailEntity(
    @Json(name = "coinCount")
    var coinCount: Int? = 0,
    @Json(name = "rank")
    var rank: Int? = 0,
    @Json(name = "userId")
    var userId: Int? = 0,
    @Json(name = "username")
    var username: String? = ""
)