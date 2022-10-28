package com.lifeidroid.wanandroid.ext

import java.text.SimpleDateFormat
import java.util.*

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */
fun Long.formateDateStr(pattern: String): String {
    val simpleDateFormat = SimpleDateFormat(pattern)
    return simpleDateFormat.format(Date(this))
}