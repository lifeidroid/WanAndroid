package com.lifeidroid.wanandroid.http

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 根据项目实际逻辑进行改造的DSL网络请求
 * ResultType 网络返回数据对应的Data字段生成的实体
 */
suspend fun <ResultType> customRequest(
    dsl: RequestActionDsl<CommonBean<ResultType>, ResultType>.() -> Unit
): LiveData<ResultData<ResultType>> {
    val action = RequestActionDsl<CommonBean<ResultType>, ResultType>().apply(dsl)
    return liveData(Dispatchers.IO) {
        // 加载数据库缓存
        action.mLoadCache?.let {
            emit(ResultData.success(it.invoke(), true, action.mRequestTag?.invoke()))
        }
        // 通知网络请求开始
        emit(ResultData.start(action.mRequestTag?.invoke()))
        val apiResponse = try {
            // 获取网络请求数据
            val resultBean = action.api?.invoke()
            ApiResponse.create(resultBean)
        } catch (e: Throwable) {
            ApiResponse.create(e)
        }
        // 根据 ApiResponse 类型，处理对于事物
        val result = when (apiResponse) {
            is ApiEmptyResponse -> {
                null
            }
            is ApiSuccessResponse -> {
                if (apiResponse.body.errorCode == HttpResponseCode.SUCCESS) {//网络访问成功
                    // 转换数据
                    val result = apiResponse.body.data
                    if (result != null) {
                        // 缓存数据到数据库
                        action.mSaveCache?.let {
                            withContext(Dispatchers.IO) {
                                it.invoke(result)
                            }
                        }
                    }

                    result.apply {
                        emit(
                            ResultData.success<ResultType>(
                                this,
                                false,
                                action.mRequestTag?.invoke()
                            )
                        )
                    }
                    result
                } else {
                    val errorMessage = when (apiResponse.body.errorCode) {
                        HttpResponseCode.SERVER_ERROR -> {
                            apiResponse.body.errorMsg
                        }
                        HttpResponseCode.TIMEOUT -> {
                            "网络访问超时，请稍后重试！"
                        }
                        HttpResponseCode.LOGIN_TIMEOUT -> {
                            "登录状态已过期！"
                        }
                        HttpResponseCode.LOGIN_FAIL -> {
                            apiResponse.body.errorMsg
                        }
                        else -> {
                            apiResponse.body.errorMsg
                        }
                    }
                    emit(
                        ResultData.error(
                            Throwable(errorMessage),
                            action.mRequestTag?.invoke()
                        )
                    )
                    null
                }
            }
            is ApiErrorResponse -> {
                emit(ResultData.error(apiResponse.throwable, action.mRequestTag?.invoke()))
                null
            }
        }
        emit(ResultData.complete(result, action.mRequestTag?.invoke()))
    }
}
