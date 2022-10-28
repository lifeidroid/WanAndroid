package com.lifeidroid.wanandroid.http

/**
 * 创建日期：2021/9/29 9:57 下午
 * @author LiFei
 * @version 1.0
 * 包名： com.carl.moduledata.httpframe
 * 类说明：
 */
/**
 * 网络封装DSL
 */
open class RequestActionDsl<ResponseType, ResultType> {
    /**
     * 网络请求方法
     */
    var api: (suspend () -> ResponseType)? = null

    /**
     * 加载缓存数据
     */
    var mLoadCache: (() -> ResultType)? = null
        private set

    /**
     * 缓存数据
     */
    var mSaveCache: ((ResultType) -> Unit)? = null
        private set

    /**
     * 设置请求Tag，当网络返回时，在返回值ResultData的Tag中回调回去
     */
    var mRequestTag: (() -> Any)? = null

    /**
     * 将ResponseType 转换成ResultType
     */
    @Suppress("UNCHECKED_CAST")
    var transformer: ((ResponseType?) -> ResultType?)? = {
        it as? ResultType
    }
        private set

    fun api(block: suspend () -> ResponseType) {
        this.api = block
    }

    fun loadCache(block: (() -> ResultType)?) {
        this.mLoadCache = block
    }

    fun saveCache(block: ((ResultType) -> Unit)?) {
        this.mSaveCache = block
    }

    // 将网络数据类型，转换为需要的数据类型
    fun transformer(block: (ResponseType?) -> ResultType?) {
        this.transformer = block
    }

    fun requestTag(block: (() -> Any)){
         this.mRequestTag = block
    }

}
