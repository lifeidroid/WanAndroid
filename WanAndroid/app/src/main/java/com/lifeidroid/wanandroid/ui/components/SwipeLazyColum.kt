package com.lifeidroid.wanandroid.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.lifeidroid.wanandroid.ext.OnBottomReached

/**
 * 下拉刷新上拉加载控件
 * @param modifier Modifier
 * @param swipeLazyColumState SwipeLazyColumState   控件状态
 * @param onRefreshCallBack Function0<Unit> 下拉刷新回调
 * @param loadMoreCallBack Function0<Unit>  加载更多回调
 * @param content [@kotlin.ExtensionFunctionType] Function1<LazyListScope, Unit>
 */
@Composable
fun SwipeLazyColum(
    modifier: Modifier = Modifier,
    swipeLazyColumState: SwipeLazyColumState,
    onRefreshCallBack: () -> Unit,
    loadMoreCallBack: () -> Unit,
    content: LazyListScope.() -> Unit
) {
    val lazyListState = rememberLazyListState()
    lazyListState.OnBottomReached {
        if (swipeLazyColumState.loadMoreState == LoadMoreState.hasMore) {
            loadMoreCallBack()
        }
    }
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = swipeLazyColumState.refreshState),//用来控制SwipeRefresh的刷新状态
        onRefresh = {//刷新回调
            onRefreshCallBack()
        }) {
        LazyColumn(modifier, state = lazyListState) {
            content()
            //LoadMore的提示内容,可以根据自己的需求去自定义该部分显示样式
            item {
                Box(
                    modifier = Modifier
                        .clickable {
                            if (swipeLazyColumState.loadMoreState == LoadMoreState.loadError) {
                                loadMoreCallBack()
                            }
                        }
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = when (swipeLazyColumState.loadMoreState) {
                            LoadMoreState.hasMore -> "正在加载.."
                            LoadMoreState.noMore -> "没有更多数据了.."
                            LoadMoreState.loadError -> "网络出错，点击重试！"
                        },
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
