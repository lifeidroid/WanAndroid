package com.lifeidroid.wanandroid.model.entity.net

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/24
 *     desc   : 2.1 体系数据
 *     version: 1.0
 * </pre>
 */
@JsonClass(generateAdapter = true)
data class SystemEntity(
    @Json(name = "author")
    var author: String? = "",
    @Json(name = "children")
    var children: List<SystemEntity>? = listOf(),
    @Json(name = "courseId")
    var courseId: Int? = 0,
    @Json(name = "cover")
    var cover: String? = "",
    @Json(name = "desc")
    var desc: String? = "",
    @Json(name = "id")
    var id: Int? = 0,
    @Json(name = "lisense")
    var lisense: String? = "",
    @Json(name = "lisenseLink")
    var lisenseLink: String? = "",
    @Json(name = "name")
    var name: String? = "",
    @Json(name = "order")
    var order: Int? = 0,
    @Json(name = "parentChapterId")
    var parentChapterId: Int? = 0,
    @Json(name = "type")
    var type: Int? = 0,
    @Json(name = "userControlSetTop")
    var userControlSetTop: Boolean? = false,
    @Json(name = "visible")
    var visible: Int? = 0
) {

}