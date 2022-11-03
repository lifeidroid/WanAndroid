package com.lifeidroid.wanandroid.ui.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.ext.getMValue
import com.lifeidroid.wanandroid.model.entity.net.CollectArticleEnity
import com.lifeidroid.wanandroid.ui.components.StatefulContent
import com.lifeidroid.wanandroid.ui.components.SwipeLazyColum
import com.lifeidroid.wanandroid.viewmodel.MyCollectedViewModel

@Composable
fun ArticleCollectedPage(
    modifier: Modifier = Modifier,
    vm: MyCollectedViewModel,
    articleDetail: (String) -> Unit
) {

    LaunchedEffect(key1 = Unit, block = {
        vm.getCollectedArticle(true)
    })

    StatefulContent(content = {
        SwipeLazyColum(
            swipeLazyColumState = vm.swipeLazyColumState,
            onRefreshCallBack = {
                vm.getCollectedArticle(true)
            },
            loadMoreCallBack = { vm.getCollectedArticle(false) },
        ) {
            itemsIndexed(vm.collectedArticleList) { index, temp ->
                CollecteItem(item = temp, itemClick = { url ->
                    articleDetail(url)
                }, collectClick = {
                    vm.unCollect(index)
                })
            }
        }
    }, state = vm.statefullState) {
        vm.getCollectedArticle(true)
    }
}


/**
 * 文章列表项
 * @param item Data
 */
@Composable
fun CollecteItem(
    item: CollectArticleEnity.Data,
    itemClick: (String) -> Unit,
    collectClick: (() -> Unit)?
) {

    Column(modifier = Modifier
        .clickable {
            itemClick(item.link!!)
        }
        .padding(vertical = 8.dp, horizontal = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${item.author}${item.author}",
                fontSize = 12.sp,
                color = colorResource(id = R.color.text_gray)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = item.niceDate.getMValue(),
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
            Text(
                text = "${item.chapterName}",
                fontSize = 12.sp,
                color = colorResource(
                    id = R.color.text_gray
                ), modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )
            Icon(
                bitmap = ImageBitmap.imageResource(R.mipmap.ic_collect_s),
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.clickable {
                    collectClick?.invoke()
                }
            )
        }
        Divider(
            modifier = Modifier.padding(top = 8.dp), thickness = 1.dp, color = colorResource(
                id = R.color.line_color
            )
        )
    }
}