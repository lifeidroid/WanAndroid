package com.lifeidroid.wanandroid.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lifeidroid.wanandroid.Constants
import com.lifeidroid.wanandroid.ext.decode
import com.lifeidroid.wanandroid.ext.encode
import com.lifeidroid.wanandroid.ext.getMValue
import com.lifeidroid.wanandroid.ext.toArrayList
import com.lifeidroid.wanandroid.model.entity.net.SystemEntity
import com.lifeidroid.wanandroid.ui.navi.HomeComposeDirections
import com.lifeidroid.wanandroid.ui.navigation.direction.SystemDetailDirections
import com.lifeidroid.wanandroid.ui.page.*
import com.lifeidroid.wanandroid.utils.SPUtils

@Composable
fun NavHostApp(
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = if (SPUtils.instance!!.getBoolean(Constants.SP_LOGIN)) Screen.MainPage.route else Screen.LoginPage.route
    ) {
        composable(Screen.LoginPage.route) {
            LoginPage(goBack = {
                navController.popBackStack()
            }, goMainPage = {
                navController.navigate(Screen.MainPage.route)
            }, goRegister = {
                //TODO
            })
        }
        composable(Screen.MainPage.route) {
            MainPage(articleDetail = { url ->
                navController.navigate("${Screen.ArticleDetailPage.route}/${url.encode()}")
            }, toSystemDetail = { args ->
                navController.navigate(
                    SystemDetailDirections.actionToSystemDetailPage(
                        args.index,
                        args.title,
                        args.data.encode()
                    )
                )
            }, goMyCoinPage = {
                navController.navigate(Screen.MyCoinPage.route)
            }, goWebPage = { url ->
                navController.navigate("${Screen.WebPage.route}/${url.encode()}")
            }, goSearchPage = {
                navController.navigate(Screen.SearchPage.route)
            }, goMyInfo = {
                navController.navigate(Screen.MyInfoPage.route)
            }, goMyShare = {
                navController.navigate(Screen.MySharePage.route)
            }, goMyCollected = {
                navController.navigate(Screen.MyCollectedPage.route)
            })
        }
        /**
         * 分享页面
         */
        composable("${Screen.SharePage.route}?${Param.SharePageParam1.name}={${Param.SharePageParam1.name}}&${Param.SharePageParam2.name}={${Param.SharePageParam2.name}}") {
            val title = it.arguments?.getString(Param.SharePageParam1.name, "")?.decode()
            val link = it.arguments?.getString(Param.SharePageParam2.name, "")?.decode()
            Log.d("====", "11111title:$title    link:$link")
            SharePage(title.getMValue(), link.getMValue()) {
                navController.popBackStack()
            }
        }
        /**
         * 文章详情
         */
        composable("${Screen.ArticleDetailPage.route}/{${Param.ArticleDetailPageParam1.name}}") {
            val value1 = it.arguments!!.getString(Param.ArticleDetailPageParam1.name, "").decode()
            ArticleDetailPage(value1) {
                navController.popBackStack()
            }
        }
        /**
         * web文章详情
         */
        composable("${Screen.WebPage.route}/{${Param.ArticleDetailPageParam1.name}}") {
            val value1 = it.arguments!!.getString(Param.ArticleDetailPageParam1.name, "").decode()
            WebPage(value1, share = { title, url ->
                navController.navigate(
                    "${Screen.SharePage.route}?${Param.SharePageParam1.name}=${title.encode()}&${Param.SharePageParam2.name}=${url.encode()}"
                )
            }) {
                navController.popBackStack()
            }
        }
        /**
         * 体系详情
         */
        composable(SystemDetailDirections.route, arguments = SystemDetailDirections.argumentList) {
            var (index, title, data) = SystemDetailDirections.parseArguments(it)
            SystemDetailPage(
                index,
                title,
                data.decode().toArrayList(SystemEntity::class.java),
                goBack = {
                    navController.popBackStack()
                },
                articleDetail = { url ->
                    navController.navigate("${Screen.ArticleDetailPage.route}/${url.encode()}")
                })
        }

        /**
         * 我的积分
         */
        composable(Screen.MyCoinPage.route) {
            MyCoinPage(goBack = {
                navController.popBackStack()
            })
        }

        composable(HomeComposeDirections.route, arguments = HomeComposeDirections.argumentList) {
            val (email, vip) = HomeComposeDirections.parseArguments(it)
        }

        /**
         * 搜索页面
         */
        composable(Screen.SearchPage.route) {
            SearchPage(articleDetail = { url ->
                navController.navigate("${Screen.ArticleDetailPage.route}/${url.encode()}")
            }) {
                navController.popBackStack()
            }
        }
        /**
         * 个人信息
         */
        composable(Screen.MyInfoPage.route) {
            MyInfoPage() {
                navController.popBackStack()
            }
        }
        /**
         * 我的分享
         */
        composable(Screen.MySharePage.route) {
            MySharePage(goBack = {
                navController.popBackStack()
            }, doAdd = {
                navController.navigate(
                    "${Screen.SharePage.route}"
                )
            }, articleDetail = { url ->
                navController.navigate("${Screen.ArticleDetailPage.route}/${url.encode()}")
            })
        }
        /**
         * 我的收藏
         */
        composable(Screen.MyCollectedPage.route) {
            MyCollectedPage(goBack = {
                navController.popBackStack()
            }, webDetail = { url ->
                navController.navigate("${Screen.WebPage.route}/${url.encode()}")
            }, articleDetail = { url ->
                navController.navigate("${Screen.ArticleDetailPage.route}/${url.encode()}")
            })
        }
    }
}

sealed class Screen(val route: String) {
    object LoginPage : Screen("LoginPage")
    object MainPage : Screen("MainPage")
    object ArticleDetailPage : Screen("ArticleDetailPage")
    object SystemDetailPage : Screen("SystemDetailPage")
    object MyCoinPage : Screen("MyCoinPage")
    object WebPage : Screen("WebPage")
    object SharePage : Screen("SharePage")
    object SearchPage : Screen("SearchPage")
    object MyInfoPage : Screen("MyInfoPage")
    object MySharePage : Screen("MySharePage")
    object MyCollectedPage : Screen("MyCollectedPage")
}

sealed class Param(val name: String) {
    object ArticleDetailPageParam1 : Param("param1")
    object SharePageParam1 : Param("title")
    object SharePageParam2 : Param("link")
}

@Preview(name = "NavHostApp")
@Composable
private fun PreviewNavHostApp() {
    NavHostApp()
}