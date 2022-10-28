package com.lifeidroid.wanandroid.ui.components

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/20
 *     desc   : LoadMore状态值
 *     version: 1.0
 * </pre>
 */
enum class LoadMoreState {
    hasMore,    //可加载更多内容
    noMore,     //已加载完全部内容
    loadError   //加载出错
}