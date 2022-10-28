package com.lifeidroid.wanandroid.ui.page

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.ext.clearAddAll
import com.lifeidroid.wanandroid.model.entity.net.SystemEntity
import com.lifeidroid.wanandroid.ui.components.StatefulContent
import com.lifeidroid.wanandroid.ui.components.SwipeLazyColum
import com.lifeidroid.wanandroid.ui.components.TitileBar
import com.lifeidroid.wanandroid.ui.item.ArticleListItem
import com.lifeidroid.wanandroid.viewmodel.SystemArticleViewModel

/**
 * 体系二级页面
 * @param modifier Modifier
 */
@Composable
fun SystemDetailPage(
    index: Int,
    title: String,
    datas: List<SystemEntity>,
    vm: SystemArticleViewModel = hiltViewModel(),
    goBack: () -> Unit,
    articleDetail: (String) -> Unit,
) {


    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = true
        )
        onDispose {}
    }
    var selectedIndex by remember {
        mutableStateOf(index)
    }

    LaunchedEffect(Unit) {
        vm.datas.clearAddAll(datas)
    }

    LaunchedEffect(key1 = selectedIndex) {
        vm.updateId(selectedIndex)
        Log.d("====","开始")
        vm.loadSystemArticle(true)
    }

    Column() {
        TitileBar() {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_back),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        goBack()
                    }
                )
                Text(
                    text = title,
                    modifier = Modifier.fillMaxWidth(),
                    color = colorResource(id = R.color.text_white),
                    textAlign = TextAlign.Center
                )
            }

        }

        ScrollableTabRow(
            divider = { 0.dp },
            backgroundColor = colorResource(id = R.color.bg_blue),
            selectedTabIndex = selectedIndex,
            contentColor = Color.White,
            indicator = { },
            modifier = Modifier.height(40.dp),
            edgePadding = 0.dp
        ) {
            datas.forEachIndexed { index, systemEntity ->
                Tab(selected = selectedIndex == index, onClick = { selectedIndex = index }) {
                    Text(text = "${systemEntity.name}", fontSize = 14.sp)
                }
            }
        }

        StatefulContent(content = {
            SwipeLazyColum(
                swipeLazyColumState = vm.swipeLazyColumState,
                onRefreshCallBack = {
                    vm.loadSystemArticle(true)
                },
                loadMoreCallBack = {
                    Log.d("====","开始2")
                    vm.loadSystemArticle(false)
                                   },
            ) {
                itemsIndexed(vm.articleDataSource) {index, temp ->
                    ArticleListItem(item = temp, isTop = false, itemClick = { url ->
                        articleDetail(url)
                    }, collectClick = {
                        vm.updateArtcleDataSourceCollect(index)
                    })
                }
            }
        }) {
            vm.loadSystemArticle(true)
        }
    }

}

