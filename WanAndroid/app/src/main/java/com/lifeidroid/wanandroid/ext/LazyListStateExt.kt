package com.lifeidroid.wanandroid.ext

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*

/**
 * 扩展LazyColum的LazyListState，通过计算判断是否到达最后一项
 * @receiver LazyListState
 * @param buffer Int 指定离底部还剩几个时进行加载更多回调
 * @param loadMore Function0<Unit>
 */
@Composable
fun LazyListState.OnBottomReached(buffer: Int = 0, loadMore: () -> Unit) {
    require(buffer >= 0) {
        "buffer 值必须是正整数"
    }
    //是否应该加载更多的状态
    val shouldLoadMore = remember {
        //因为状态由layoutInfo计算得到
        derivedStateOf {
            //列表为空的话直接返回false,因为Colum默认存在一个footer,所以列表为空情况下，size也为1
            if (layoutInfo.visibleItemsInfo.size <= 1){
                return@derivedStateOf false
            }
            val lastVisibleItem = layoutInfo.visibleItemsInfo.last()
            //如果现实项为最后一个item 返回true
            lastVisibleItem.index == layoutInfo.totalItemsCount - 2 - buffer
        }
    }
    LaunchedEffect(key1 = shouldLoadMore, block = {
        snapshotFlow {
            shouldLoadMore.value
        }.collect {
            if (it) {
                loadMore()
            }
        }
    })
}