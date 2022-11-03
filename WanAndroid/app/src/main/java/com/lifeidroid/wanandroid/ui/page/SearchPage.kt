package com.lifeidroid.wanandroid.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.ui.components.StatefulContent
import com.lifeidroid.wanandroid.ui.components.SwipeLazyColum
import com.lifeidroid.wanandroid.ui.components.TitileBar
import com.lifeidroid.wanandroid.ui.item.ArticleListItem
import com.lifeidroid.wanandroid.ui.navigation.direction.ArticleItem
import com.lifeidroid.wanandroid.viewmodel.SearchViewModel

/**
 * 搜索页面
 * @param modifier Modifier
 */
@Composable
fun SearchPage(
    modifier: Modifier = Modifier,
    vm: SearchViewModel = hiltViewModel(),
    articleDetail: (String) -> Unit,
    goBack: () -> Unit
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

    LaunchedEffect(key1 = Unit, block = {
        vm.getHotKey()
    })

    Column() {
        TitileBar() {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_back),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        goBack.invoke()
                    }
                )
                Spacer(modifier = modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(35.dp)
                        .clip(
                            RoundedCornerShape(30.dp)
                        )
                        .background(color = colorResource(id = R.color.bg_search)),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    BasicTextField(
                        value = vm.key,
                        onValueChange = {
                            vm.updateKey(it)
                        },
                        textStyle = TextStyle(color = colorResource(id = if (vm.key.isNullOrEmpty()) R.color.text_gray else R.color.text_white)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        singleLine = true,

                        decorationBox = @Composable { innerTextField ->
                            if (vm.key.isEmpty())
                                Text(
                                    text = "用空格隔开多个关键词",
                                    color = colorResource(id = R.color.text_gray)
                                )
                            innerTextField()
                        }

                    )

                    if (!vm.key.isNullOrEmpty()) {
                        Icon(
                            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_remove),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .clickable {
                                    vm.updateKey("")
                                }
                        )
                    }
                }
                Spacer(modifier = modifier.width(10.dp))
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_search),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        vm.doSearch(true)
                    }
                )
            }
        }

        if (vm.key.isNullOrEmpty()) {
            if (!vm.hotkeyDatas.isNullOrEmpty()) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)
                ) {
                    Text(
                        text = "热门搜索",
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.text_blue)
                    )
                    FlowRow(
                        mainAxisSpacing = 8.dp,
                        crossAxisSpacing = 8.dp,
                        modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)
                    ) {
                        for ((index, child) in vm.hotkeyDatas.withIndex()) {
                            SearchItem(label = child.name!!) {
                                vm.updateKey(child.name!!)
                                vm.doSearch(true)
                            }
                        }
                    }
                }
            }
        } else {
            StatefulContent(state = vm.statefullState, content = {
                SwipeLazyColum(
                    swipeLazyColumState = vm.swipeLazyColumState,
                    onRefreshCallBack = { vm.doSearch(true) },
                    loadMoreCallBack = { vm.doSearch(false) },
                    content = {
                        itemsIndexed(vm.searchResult) { index, item ->
                            ArticleListItem(item = item, isTop = false, itemClick = {
                                articleDetail(it)
                            }, collectClick = {
                                vm.updateArtcleDataSourceCollect(index)
                            }
                            )
                        }
                    }
                )
            }) {
                vm.doSearch(true)
            }
        }
    }
}

/**
 * 热门搜索项
 * @param label String
 * @param click Function0<Unit>
 */
@Composable
fun SearchItem(label: String, click: () -> Unit) {
    Box(
        modifier = Modifier
            .height(30.dp)
            .clip(
                RoundedCornerShape(16.dp)
            )
            .clickable {
                click.invoke()
            }
            .background(color = colorResource(id = R.color.line_color))
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = colorResource(id = R.color.text_black),
            fontSize = 12.sp
        )
    }
}