package com.lifeidroid.wanandroid.ui.page

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.model.entity.NavigationItem
import com.lifeidroid.wanandroid.ui.navigation.direction.SystemDetailDirections

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainPage(
    articleDetail: (String) -> Unit,
    toSystemDetail: (args: SystemDetailDirections.SystemDetailArgs) -> Unit,
    goMyCoinPage: () -> Unit,
    goWebPage: (String) -> Unit,
    goSearchPage: () -> Unit,
    goMyInfo:()->Unit,
    goMyShare:()->Unit
) {

    val navController = rememberNavController()

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = true
        )
        onDispose {}
    }

    val navigationItems = listOf(
        NavigationItem(
            "首页",
            icon = ImageBitmap.imageResource(id = R.mipmap.ic_bottom_bar_home),
            Screen.HomePage
        ),
        NavigationItem(
            "问答",
            icon = ImageBitmap.imageResource(id = R.mipmap.ic_bottom_bar_ques),
            Screen.QuestionPage
        ),
        NavigationItem(
            "体系",
            icon = ImageBitmap.imageResource(id = R.mipmap.ic_bottom_bar_navi),
            Screen.SystemPage
        ),
        NavigationItem(
            "我的",
            icon = ImageBitmap.imageResource(id = R.mipmap.ic_bottom_bar_mine),
            Screen.MinePage
        ),
    )


    Scaffold(bottomBar = {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.surface,
            modifier = Modifier.navigationBarsPadding(bottom = true)
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            navigationItems.forEachIndexed { index, navigationItem ->
                BottomNavigationItem(
                    selected = currentDestination?.hierarchy?.any {
                        it.route == navigationItem.screen.route
                    } == true,
                    onClick = {
                        navController.navigate(navigationItem.screen.route) {
                            // 弹出到图形的开始目的地，以避免在用户选择项目时在后堆栈上建立大量目的地堆栈
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // 在重新选择相同项目时，避免使用相同目标的多个副本
                            launchSingleTop = true
                            // 重新选择以前选中的项时恢复状态
                            restoreState = true
                        }
                    },
                    //直接考试结果页面，进入查看页面，返回直接回到列表？
                    icon = {
                        Icon(bitmap = navigationItem.icon, contentDescription = null)
                    },
                    label = {
                        Text(text = navigationItem.label)
                    },
                    selectedContentColor = colorResource(id = R.color.bg_blue),
                    unselectedContentColor = Color(0xFF999999)
                )
            }
        }
    }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.HomePage.route,
            modifier = Modifier.padding(bottom = 56.dp)
        ) {
            composable(Screen.HomePage.route) {
                HomePage(
                    articleDetail = articleDetail,
                    goWebPage = goWebPage,
                    goSearchPage = goSearchPage
                )
            }
            composable(Screen.QuestionPage.route) {
                QuestionPage(articleDetail = articleDetail)
            }
            composable(Screen.SystemPage.route) {
                SystemPage(toSystemDetail = toSystemDetail, articleDetail = articleDetail)
            }
            composable(Screen.MinePage.route) {
                MinePage(goMyInfo = goMyInfo,goMyCoinPage = goMyCoinPage,goMyShare = goMyShare)
            }
        }
    }
}

sealed class Screen(val route: String) {
    object HomePage : Screen("HomePage")
    object QuestionPage : Screen("QuestionPage")
    object SystemPage : Screen("SystemPage")
    object MinePage : Screen("MinePage")
}