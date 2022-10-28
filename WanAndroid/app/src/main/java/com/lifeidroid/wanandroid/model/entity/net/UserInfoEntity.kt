package com.lifeidroid.wanandroid.model.entity.net
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/27
 *     desc   : 12. 个人信息接口
 *     version: 1.0
 * </pre>
 */
@JsonClass(generateAdapter = true)
data class UserInfoEntity(
    @Json(name = "coinInfo")
    var coinInfo: CoinInfo? = CoinInfo(),
    @Json(name = "collectArticleInfo")
    var collectArticleInfo: CollectArticleInfo? = CollectArticleInfo(),
    @Json(name = "userInfo")
    var userInfo: UserInfo? = UserInfo()
) {
    @JsonClass(generateAdapter = true)
    data class CoinInfo(
        @Json(name = "coinCount")
        var coinCount: Int? = 0,
        @Json(name = "level")
        var level: Int? = 0,
        @Json(name = "nickname")
        var nickname: String? = "",
        @Json(name = "rank")
        var rank: String? = "",
        @Json(name = "userId")
        var userId: Int? = 0,
        @Json(name = "username")
        var username: String? = ""
    )

    @JsonClass(generateAdapter = true)
    data class CollectArticleInfo(
        @Json(name = "count")
        var count: Int? = 0
    )

    @JsonClass(generateAdapter = true)
    data class UserInfo(
        @Json(name = "admin")
        var admin: Boolean? = false,
        @Json(name = "chapterTops")
        var chapterTops: List<Any?>? = listOf(),
        @Json(name = "coinCount")
        var coinCount: Int? = 0,
        @Json(name = "collectIds")
        var collectIds: List<Int>? = listOf(),
        @Json(name = "email")
        var email: String? = "",
        @Json(name = "icon")
        var icon: String? = "",
        @Json(name = "id")
        var id: Int? = 0,
        @Json(name = "nickname")
        var nickname: String? = "",
        @Json(name = "password")
        var password: String? = "",
        @Json(name = "publicName")
        var publicName: String? = "",
        @Json(name = "token")
        var token: String? = "",
        @Json(name = "type")
        var type: Int? = 0,
        @Json(name = "username")
        var username: String? = ""
    )
}