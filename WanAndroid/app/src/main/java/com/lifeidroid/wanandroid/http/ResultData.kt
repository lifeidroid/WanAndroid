package com.lifeidroid.wanandroid.http

/**
 * 返回数据装包
 * @param T 最终数据实体
 * @property requestStatus RequestStatus 网络请求状态标识
 * @property data T?    最终数据实体
 * @property isCache Boolean    是否为缓存数据
 * @property error Throwable?   错误消息
 * @property tag Any?
 * @property progress Int 进度（0~100）
 * @constructor
 */
data class ResultData<T>(
    val requestStatus: RequestStatus,
    val data: T?,
    val isCache: Boolean = false,
    val error: Throwable? = null,
    val tag: Any? = null,
    val progress: Int = 0
) {
    companion object {

        fun <T> start(tag: Any? = null): ResultData<T> {
            return ResultData(
                RequestStatus.START,
                null,
                false,
                null,
                tag
            )
        }

        fun <T> progress(progress: Int, tag: Any? = null): ResultData<T> {
            return ResultData(
                RequestStatus.PROGRESS,
                null,
                false,
                null,
                tag,
                progress
            )
        }

        fun <T> success(data: T?, isCache: Boolean = false, tag: Any? = null): ResultData<T> {
            return ResultData(
                RequestStatus.SUCCESS,
                data,
                isCache,
                null,
                tag
            )
        }

        fun <T> complete(data: T?, tag: Any? = null): ResultData<T> {
            return ResultData(
                RequestStatus.COMPLETE,
                data,
                false,
                null,
                tag
            )
        }

        fun <T> error(error: Throwable?, tag: Any? = null): ResultData<T> {
            return ResultData(
                RequestStatus.ERROR,
                null,
                false,
                error,
                tag
            )
        }
    }
}

