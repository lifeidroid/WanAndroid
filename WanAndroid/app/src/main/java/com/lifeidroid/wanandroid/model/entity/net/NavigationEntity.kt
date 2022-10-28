package com.lifeidroid.wanandroid.model.entity.net
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@JsonClass(generateAdapter = true)
data class NavigationEntity(
    @Json(name = "articles")
    var articles: List<Article>? = listOf(),
    @Json(name = "cid")
    var cid: Int? = 0,
    @Json(name = "name")
    var name: String? = ""
) {
    @JsonClass(generateAdapter = true)
    data class Article(
        @Json(name = "adminAdd")
        var adminAdd: Boolean? = false,
        @Json(name = "apkLink")
        var apkLink: String? = "",
        @Json(name = "audit")
        var audit: Int? = 0,
        @Json(name = "author")
        var author: String? = "",
        @Json(name = "canEdit")
        var canEdit: Boolean? = false,
        @Json(name = "chapterId")
        var chapterId: Int? = 0,
        @Json(name = "chapterName")
        var chapterName: String? = "",
        @Json(name = "collect")
        var collect: Boolean? = false,
        @Json(name = "courseId")
        var courseId: Int? = 0,
        @Json(name = "desc")
        var desc: String? = "",
        @Json(name = "descMd")
        var descMd: String? = "",
        @Json(name = "envelopePic")
        var envelopePic: String? = "",
        @Json(name = "fresh")
        var fresh: Boolean? = false,
        @Json(name = "host")
        var host: String? = "",
        @Json(name = "id")
        var id: Int? = 0,
        @Json(name = "link")
        var link: String? = "",
        @Json(name = "niceDate")
        var niceDate: String? = "",
        @Json(name = "niceShareDate")
        var niceShareDate: String? = "",
        @Json(name = "origin")
        var origin: String? = "",
        @Json(name = "prefix")
        var prefix: String? = "",
        @Json(name = "projectLink")
        var projectLink: String? = "",
        @Json(name = "publishTime")
        var publishTime: Long? = 0,
        @Json(name = "realSuperChapterId")
        var realSuperChapterId: Int? = 0,
        @Json(name = "selfVisible")
        var selfVisible: Int? = 0,
        @Json(name = "shareDate")
        var shareDate: Any? = Any(),
        @Json(name = "shareUser")
        var shareUser: String? = "",
        @Json(name = "superChapterId")
        var superChapterId: Int? = 0,
        @Json(name = "superChapterName")
        var superChapterName: String? = "",
        @Json(name = "tags")
        var tags: List<Any?>? = listOf(),
        @Json(name = "title")
        var title: String? = "",
        @Json(name = "type")
        var type: Int? = 0,
        @Json(name = "userId")
        var userId: Int? = 0,
        @Json(name = "visible")
        var visible: Int? = 0,
        @Json(name = "zan")
        var zan: Int? = 0
    )
}