package com.lifeidroid.wanandroid.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * 带状态的的页面布局
 * @param modifier Modifier
 * @param state StatefulContentState
 * @param content [@androidx.compose.runtime.Composable] Function0<Unit>
 * @param clickCallBack Function0<Unit>
 */
@Composable
fun StatefulContent(
    modifier: Modifier = Modifier,
    state: StatefulStateBean = StatefulStateBean(StatefulContentState.content),
    content: @Composable () -> Unit,
    clickCallBack: () -> Unit
) {
    when (state.state) {
        StatefulContentState.content -> {
            content()
        }
        StatefulContentState.loading -> {
            Box(modifier = Modifier
                .fillMaxSize()
                .clickable {
                    clickCallBack()
                }, contentAlignment = Alignment.Center) {
                Text(text = (if (state.msg.isNullOrBlank()) "加载中.." else state.msg))
            }
        }
        StatefulContentState.error -> {
            Box(modifier = Modifier
                .fillMaxSize()
                .clickable {
                    clickCallBack()
                }, contentAlignment = Alignment.Center) {
                Text(text = (if (state.msg.isNullOrBlank()) "网络错误，请稍后重试！" else state.msg))
            }
        }
        StatefulContentState.empty -> {
            Box(modifier = Modifier
                .fillMaxSize()
                .clickable {
                    clickCallBack()
                }, contentAlignment = Alignment.Center) {
                Text(text = (if (state.msg.isNullOrBlank()) "暂无数据，请稍后重试！" else state.msg))
            }
        }
    }
}
