package com.lifeidroid.wanandroid.model.entity.net
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/11/03
 *     desc   : 6.5 收藏网站列表
 *     version: 1.0
 * </pre>
 */
@JsonClass(generateAdapter = true)
data class CollectNetEntity(
    @Json(name = "desc")
    var desc: String? = "",
    @Json(name = "icon")
    var icon: String? = "",
    @Json(name = "id")
    var id: Int? = 0,
    @Json(name = "link")
    var link: String? = "",
    @Json(name = "name")
    var name: String? = "",
    @Json(name = "order")
    var order: Int? = 0,
    @Json(name = "userId")
    var userId: Int? = 0,
    @Json(name = "visible")
    var visible: Int? = 0
)