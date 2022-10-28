package com.lifeidroid.wanandroid.ext

import com.alibaba.fastjson.JSON

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */
/**
 * 对象转换成JSON字符串
 * @receiver List<T>
 * @return String
 */
fun Any.toJsonString():String{
    return JSON.toJSONString(this)
}
