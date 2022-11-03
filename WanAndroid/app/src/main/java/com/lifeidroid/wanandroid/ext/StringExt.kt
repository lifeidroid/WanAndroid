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

/**
 * 如果字符串避空
 * @receiver String
 * @param holder String
 * @return String
 */
fun String?.getMValue(holder: String = ""): String {
    return if (this.isNullOrEmpty()) {
        holder
    } else {
        this
    }
}

/**
 * 字符串转List
 * @receiver String
 * @param clazz Class<T>
 * @return List<T>
 */
fun <T> String.toArrayList(clazz: Class<T>): List<T> {
    return JSON.parseArray(this, clazz)
}

/**
 * 字符串转实体类
 * @receiver String
 * @param clazz Class<T>
 * @return T
 */
fun <T> String.toObj(clazz: Class<T>): T {
    return JSON.parseObject(this, clazz)
}