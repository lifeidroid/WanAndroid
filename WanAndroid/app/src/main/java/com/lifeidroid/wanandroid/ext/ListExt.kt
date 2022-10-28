package com.lifeidroid.wanandroid.ext

import com.alibaba.fastjson.JSON

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */
/**
 * 清空旧数据，填装新数据
 * @receiver MutableList<T>
 * @param elements Collection<T>
 */
fun <T> MutableList<T>.clearAddAll(elements: Collection<T>){
    this.clear()
    this.addAll(elements)
}

/**
 * 列表转换成JSON字符串
 * @receiver List<T>
 * @return String
 */
fun <T> List<T>.toJsonString():String{
   return JSON.toJSONString(this)
}
