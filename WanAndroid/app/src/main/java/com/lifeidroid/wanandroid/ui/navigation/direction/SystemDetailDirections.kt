package com.lifeidroid.wanandroid.ui.navigation.direction

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.ext.formateDateStr
import com.lifeidroid.wanandroid.model.entity.net.ArticleEntity
import com.lifeidroid.wanandroid.ui.navigation.Screen

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/26
 *     desc   : 体系详情页面参数
 *     version: 1.0
 * </pre>
 */
class SystemDetailDirections {
    data class SystemDetailArgs(
        val index: Int,
        val title: String,
        val data: String
    )

    companion object {
        val route = "${Screen.SystemDetailPage.route}?index={index},title={title},data={data}"
        val argumentList: MutableList<NamedNavArgument>
            get() = mutableListOf(
                navArgument("index") {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument("title") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("data") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )

        fun parseArguments(backStackEntry: NavBackStackEntry): SystemDetailArgs {
            return SystemDetailArgs(
                index = backStackEntry.arguments?.getInt("index") ?: 0,
                title = backStackEntry.arguments?.getString("title") ?: "",
                data = backStackEntry.arguments?.getString("data") ?: ""
            )
        }

        fun actionToSystemDetailPage(
            index: Int,
            title: String,
            data: String
        ): String {
            return "${Screen.SystemDetailPage.route}?index=$index,title=$title,data=$data"
        }
    }
}

/**
 * 文章列表项
 * @param item Data
 */
@Composable
fun ArticleItem(item: ArticleEntity.Data, isTop: Boolean, itemClick: (String) -> Unit) {
    Column(modifier = Modifier
        .padding(vertical = 8.dp, horizontal = 10.dp)
        .clickable {
            itemClick(item.link!!)
        }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${item.author}${item.shareUser}",
                fontSize = 12.sp,
                color = colorResource(id = R.color.text_gray)
            )
            Row(modifier = Modifier.weight(1f)) {
                for (tag in item.tags) {
                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .border(
                                1.dp,
                                color = colorResource(id = R.color.bg_blue)
                            )
                            .padding(horizontal = 2.dp),

                        text = "${tag.name}",
                        color = colorResource(id = R.color.text_blue),
                        fontSize = 12.sp
                    )
                }
            }
            Text(
                text = "${item.publishTime!!.formateDateStr("yyyy-MM-dd hh:mm")}",
                fontSize = 12.sp,
                color = colorResource(
                    id = R.color.text_gray
                )
            )
        }
        Text(
            text = "${item.title}",
            color = colorResource(id = R.color.text_black),
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 6.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (isTop) {
                Text(text = "置顶", fontSize = 12.sp, color = Color.Red)
            }
            Text(
                text = "${item.chapterName} . ${item.superChapterName}",
                fontSize = 12.sp,
                color = colorResource(
                    id = R.color.text_gray
                ), modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )
            Icon(
                bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_collect),
                contentDescription = null,
                tint = Color.Red,
            )
        }
        Divider(
            modifier = Modifier.padding(top = 8.dp), thickness = 1.dp, color = colorResource(
                id = R.color.line_color
            )
        )
    }
}