package com.lifeidroid.wanandroid.model.entity.net
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/11/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@JsonClass(generateAdapter = true)
data class CollecteEntity(
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