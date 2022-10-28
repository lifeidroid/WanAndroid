package com.lifeidroid.wanandroid.ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/21
 *     desc   : 下拉刷新、上拉加载状态
 *     version: 1.0
 * </pre>
 */
class SwipeLazyColumState {
    var refreshState by mutableStateOf(false)
        private set

    var loadMoreState by mutableStateOf(LoadMoreState.hasMore)
        private set

    /**
     * 开始下拉刷新
     */
    fun startRefresh() {
        refreshState = true
        loadMoreState = LoadMoreState.hasMore
    }

    /**
     * 完成下拉刷新
     */
    fun finishRefresh() {
        refreshState = false
    }

    /**
     * 完成上拉加载，并已加载出所有数据
     */
    fun finishLoadMore(isAll: Boolean) {
        loadMoreState = if (isAll) {
            LoadMoreState.noMore
        } else {
            LoadMoreState.hasMore
        }
    }

    /**
     * 上拉加载中出现错误
     */
    fun doLoadMoreWithError() {
        loadMoreState = LoadMoreState.loadError
    }

}