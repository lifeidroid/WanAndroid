package com.lifeidroid.wanandroid.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.lifeidroid.wanandroid.model.entity.net.BannerEntity
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SwiperContent(
    dataSource: List<BannerEntity>,
    goWebPage: (String) -> Unit
) {
    val pageState = rememberPagerState(initialPage = 0)
    val coroutineScope = rememberCoroutineScope()
    DisposableEffect(dataSource) {
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                coroutineScope.launch {
                    if (pageState.currentPage + 1 >= dataSource.size) {
                        pageState.animateScrollToPage(0)
                    } else {
                        pageState.animateScrollToPage(pageState.currentPage + 1)
                    }
                }
            }

        }, 3000, 3000)

        onDispose {
            timer.cancel()
        }
    }

    //轮播图
    HorizontalPager(
        state = pageState,
        count = dataSource.size
    ) { index ->
        AsyncImage(
            model = dataSource[index].imagePath,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
                .clickable {
                    goWebPage(dataSource[index].url!!)
                },
            contentScale = ContentScale.Crop
        )
    }
}
