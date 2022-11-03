package com.lifeidroid.wanandroid.model.entity.net
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/11/03
 *     desc   : 6.1 收藏文章列表
 *     version: 1.0
 * </pre>
 */
@JsonClass(generateAdapter = true)
data class CollectArticleEnity(
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
        @Json(name = "author")
        var author: String? = "",
        @Json(name = "chapterId")
        var chapterId: Int? = 0,
        @Json(name = "chapterName")
        var chapterName: String? = "",
        @Json(name = "courseId")
        var courseId: Int? = 0,
        @Json(name = "desc")
        var desc: String? = "",
        @Json(name = "envelopePic")
        var envelopePic: String? = "",
        @Json(name = "id")
        var id: Int? = 0,
        @Json(name = "link")
        var link: String? = "",
        @Json(name = "niceDate")
        var niceDate: String? = "",
        @Json(name = "origin")
        var origin: String? = "",
        @Json(name = "originId")
        var originId: Int? = 0,
        @Json(name = "publishTime")
        var publishTime: Long? = 0,
        @Json(name = "title")
        var title: String? = "",
        @Json(name = "userId")
        var userId: Int? = 0,
        @Json(name = "visible")
        var visible: Int? = 0,
        @Json(name = "zan")
        var zan: Int? = 0
    )
}