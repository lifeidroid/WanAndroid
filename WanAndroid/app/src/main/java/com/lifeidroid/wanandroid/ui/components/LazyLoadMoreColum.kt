package com.lifeidroid.wanandroid.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lifeidroid.wanandroid.ext.OnBottomReached

/**
 * 为LazyColum增加加载更多提示
 * @param modifier Modifier
 * @param loadMoreState LoadMoreState
 * @param loadMore Function0<Unit>
 * @param content [@kotlin.ExtensionFunctionType] Function1<LazyListScope, Unit>
 */
@Composable
fun LazyLoadMoreColum(
    modifier: Modifier = Modifier,
    loadMoreState: LoadMoreState = LoadMoreState.hasMore,
    loadMoreCallBack: () -> Unit,
    content: LazyListScope.() -> Unit
) {
    val lazyListState = rememberLazyListState()
    lazyListState.OnBottomReached {
        if (loadMoreState == LoadMoreState.hasMore) {
            loadMoreCallBack()
        }
    }
    LazyColumn(modifier, state = lazyListState) {
        content()
        //LoadMore的提示内容,可以根据自己的需求去自定义该部分显示样式
        item {
            Box(
                modifier = Modifier
                    .clickable {
                        if (loadMoreState == LoadMoreState.loadError) {
                            loadMoreCallBack()
                        }
                    }
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = when (loadMoreState) {
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
