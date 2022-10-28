package com.lifeidroid.wanandroid.model.entity.net

import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/28
 *     desc   : 我的积分历史记录
 *     version: 1.0
 * </pre>
 */
@JsonClass(generateAdapter = true)
data class MyCoinHistoryEntity(
    @Json(name = "curPage")
    var curPage: Int? = 0,
    @Json(name = "datas")
    var datas: List<Data>? = listOf(),
    @Json(name = "offset")
    var offset: Int? = 0,
    @Json(name = "over")
    var over: Boolean? = false,
    @Json(name = "pageCount")
    var pageCount: Int? = 0,
    @Json(name = "size")
    var size: Int? = 0,
    @Json(name = "total")
    var total: Int? = 0
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "coinCount")
        var coinCount: Int? = 0,
        @Json(name = "date")
        var date: Long? = 0,
        @Json(name = "desc")
        var desc: String? = "",
        @Json(name = "id")
        var id: Int? = 0,
        @Json(name = "reason")
        var reason: String? = "",
        @Json(name = "type")
        var type: Int? = 0,
        @Json(name = "userId")
        var userId: Int? = 0,
        @Json(name = "userName")
        var userName: String? = ""
    )
}