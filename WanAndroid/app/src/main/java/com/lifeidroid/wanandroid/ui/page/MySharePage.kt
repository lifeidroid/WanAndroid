package com.lifeidroid.wanandroid.ui.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.ui.components.StatefulContent
import com.lifeidroid.wanandroid.ui.components.SwipeLazyColum
import com.lifeidroid.wanandroid.ui.components.TitileBar
import com.lifeidroid.wanandroid.ui.item.ArticleListItem
import com.lifeidroid.wanandroid.viewmodel.MyShareViewModel

/**
 * 我的分享
 * @param modifier Modifier
 * @param goBack Function0<Unit>
 * @param doAdd Function0<Unit>
 */
@Composable
fun MySharePage(
    modifier: Modifier = Modifier,
    vm: MyShareViewModel = hiltViewModel(),
    goBack: () -> Unit,
    doAdd: () -> Unit,
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

    LaunchedEffect(key1 = Unit, block = {
        vm.getData(true)
    })

    Column {
        TitileBar() {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_back),
                    contentDescription = null, tint = Color.White,
                    modifier = Modifier.clickable {
                        goBack()
                    }
                )
                Text(
                    text = "我的分享",
                    color = colorResource(id = R.color.text_white),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_add),
                    contentDescription = null, tint = Color.White, modifier = Modifier.clickable {
                        doAdd()
                    }
                )
            }
        }

        StatefulContent(state = vm.statefullState, content = {
            SwipeLazyColum(
                swipeLazyColumState = vm.swipeLazyColumState,
                onRefreshCallBack = { vm.getData(true) },
                loadMoreCallBack = { vm.getData(false) },
                content = {
                    itemsIndexed(vm.datas) { index, item ->
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
            vm.getData(true)
        }

    }



}
