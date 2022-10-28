package com.lifeidroid.wanandroid.ext

import com.alibaba.fastjson.JSON
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
fun String.encode() = URLEncoder.encode(this, StandardCharsets.UTF_8.toString()) ?: ""

fun String.decode() = URLDecoder.decode(this, StandardCharsets.UTF_8.toString()) ?: ""

fun <T> String.toArrayList(clazz: Class<T>): List<T> {
    return JSON.parseArray(this, clazz)
}