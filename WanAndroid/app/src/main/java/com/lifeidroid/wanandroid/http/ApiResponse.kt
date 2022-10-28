package com.lifeidroid.wanandroid.http

/**
 * @date：2020/4/22
 * @description:
 */
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(error)
        }

        fun <T> create(body: T?): ApiResponse<T> {
            return if (body == null) {
                ApiEmptyResponse()
            } else {
                ApiSuccessResponse(body)
            }
        }
    }
}

/**
 * 空数据响应
 * @param T
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

/**
 * 成功数据响应
 * @param T
 * @property body T
 * @constructor
 */
data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

/**
 * 错误数据响应
 * @param T
 * @property throwable Throwable
 * @constructor
 */
data class ApiErrorResponse<T>(val throwable: Throwable) : ApiResponse<T>()