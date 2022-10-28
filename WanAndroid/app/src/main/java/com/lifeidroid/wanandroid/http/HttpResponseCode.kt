package com.lifeidroid.wanandroid.http

/**
 * 创建日期：2021/9/29 10:21 下午
 * @author LiFei
 * @version 1.0
 * 包名： com.carl.moduledata.httpframe
 * 类说明：网络访问错误码
 */
object HttpResponseCode {
    const val SUCCESS = 0             //成功
    const val SERVER_ERROR = 500        //服务器错误
    const val TIMEOUT = 408             //访问超时
    const val LOGIN_TIMEOUT = 401       //登录状态已过期
    const val LOGIN_FAIL = 511          //登录失败
}
