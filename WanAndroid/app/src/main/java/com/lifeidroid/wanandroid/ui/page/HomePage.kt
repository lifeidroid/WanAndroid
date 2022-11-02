package com.lifeidroid.wanandroid.ui.page

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.ui.components.*
import com.lifeidroid.wanandroid.ui.components.dialog.DialogComponent
import com.lifeidroid.wanandroid.ui.item.ArticleListItem
import com.lifeidroid.wanandroid.viewmodel.HomeViewModel

@Composable
fun HomePage(
    articleDetail: (String) -> Unit,
    goWebPage: (String) -> Unit,
    vm: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier

) {

    DialogComponent(vm,
        alertDlgCallback = {
            Log.d("====", "按钮点击了----")
        },
        conformDlgCallback = {
            Log.d("====", "按钮点击了----${it}")
        })

    LaunchedEffect(Unit) {
        vm.loadBanner()
        vm.loadTopArticle()
        vm.loadArticle(true)
    }

    Column {
        TitileBar(modifier = modifier) {
            Row(modifier.fillMaxWidth()) {
                Icon(
                    bitmap = ImageBitmap.imageResource(R.mipmap.ic_scan),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clickable {
                    }
                )
                Text(
                    text = "首页",
                    modifier = Modifier.weight(1f, true),
                    color = colorResource(id = R.color.white),
                    textAlign = TextAlign.Center
                )
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_search),
                    contentDescription = null, tint = Color.White,
                    modifier = Modifier.clickable {
                    }
                )
            }

        }

        StatefulContent(content = {
            SwipeLazyColum(
                swipeLazyColumState = vm.swipeLazyColumState,
                onRefreshCallBack = {
                    vm.loadTopArticle()
                    vm.loadArticle(true)
                },
                loadMoreCallBack = { vm.loadArticle(false) },
            ) {
                //以下内容跟LazyColum用法一样
                item {
                    SwiperContent(dataSource = vm.swiperData, goWebPage = goWebPage)
                }
                itemsIndexed(vm.articleTopDataSource) { index, temp ->
                    ArticleListItem(item = temp, true, itemClick = { url ->
                        articleDetail(url)
                    }, collectClick = {
                        vm.updateArtcleTopDataSourceCollect(index)
                    })
                }
                itemsIndexed(vm.articleDataSource) { index, temp ->
                    ArticleListItem(item = temp, false, itemClick = { url ->
                        articleDetail(url)
                    }, collectClick = {
                        vm.updateArtcleDataSourceCollect(index)
                    })
                }
            }
        }, state = vm.statefullState) {
            vm.loadBanner()
            vm.loadTopArticle()
            vm.loadArticle(true)
        }
    }

}