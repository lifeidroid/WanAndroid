package com.lifeidroid.wanandroid.ui.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.ui.components.StatefulContent
import com.lifeidroid.wanandroid.ui.components.TitileBar
import com.lifeidroid.wanandroid.viewmodel.MyCollectedViewModel

/**
 *
 * 我的收藏
 * @param modifier Modifier
 */
@Composable
fun MyCollectedPage(
    modifier: Modifier = Modifier,
    vm: MyCollectedViewModel = hiltViewModel(),
    goBack: () -> Unit,
    articleDetail: (String) -> Unit,
    webDetail: (String) -> Unit
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

    Column {
        TitileBar(Modifier) {
            Box(contentAlignment = Alignment.CenterStart) {
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_back),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        goBack.invoke()
                    }
                )
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                TabRow(
                    divider = { 0.dp },
                    backgroundColor = Color.Transparent,
                    selectedTabIndex = vm.selectedIndex,
                    contentColor = Color.White,
                    indicator = { },
                    modifier = Modifier.width(100.dp)
                ) {
                    Tab(selected = vm.selectedIndex == 0, onClick = { vm.updateSelectedIndex(0) }) {
                        Text(text = "文章")
                    }
                    Tab(selected = vm.selectedIndex == 1, onClick = { vm.updateSelectedIndex(1) }) {
                        Text(text = "网址")
                    }
                }
            }

        }
        if (vm.selectedIndex == 0) {
            ArticleCollectedPage(vm = vm, articleDetail = articleDetail)
        } else {
            NetCollectedForm(goWebPage = webDetail)
        }


    }
}