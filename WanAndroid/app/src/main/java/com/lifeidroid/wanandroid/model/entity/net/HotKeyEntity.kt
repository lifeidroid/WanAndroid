package com.lifeidroid.wanandroid.model.entity.net
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/11/02
 *     desc   : 1.4 搜索热词
 *     version: 1.0
 * </pre>
 */
@JsonClass(generateAdapter = true)
data class HotKeyEntity(
    @Json(name = "id")
    var id: Int? = 0,
    @Json(name = "link")
    var link: String? = "",
    @Json(name = "name")
    var name: String? = "",
    @Json(name = "order")
    var order: Int? = 0,
    @Json(name = "visible")
    var visible: Int? = 0
)