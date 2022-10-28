package com.lifeidroid.wanandroid.ui.page

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavController
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.ext.encode
import com.lifeidroid.wanandroid.ext.formateDateStr
import com.lifeidroid.wanandroid.model.entity.net.QuestionEntity
import com.lifeidroid.wanandroid.ui.components.StatefulContent
import com.lifeidroid.wanandroid.ui.components.SwipeLazyColum
import com.lifeidroid.wanandroid.ui.components.TitileBar
import com.lifeidroid.wanandroid.ui.navigation.Screen
import com.lifeidroid.wanandroid.viewmodel.QuestionViewModel

/**
 * 问答页面
 * @param vm QuestionViewModel
 * @param modifier Modifier
 */
@Composable
fun QuestionPage(
    articleDetail: (String) -> Unit,
    vm: QuestionViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        vm.loadQuestion(true)
    }


    Column {
        TitileBar(Modifier) {
            Text(
                text = "问答",
                color = colorResource(id = R.color.white),
                textAlign = TextAlign.Center
            )
        }

        StatefulContent(content = {
            SwipeLazyColum(
                swipeLazyColumState = vm.swipeLazyColumState,
                onRefreshCallBack = {
                    vm.loadQuestion(true)
                },
                loadMoreCallBack = { vm.loadQuestion(false) },
            ) {
                itemsIndexed(vm.questionDataSource) { index, temp ->
                    QuestionItem(item = temp, itemClick = {
                        articleDetail(it)
                    }, collectClick = {
                        vm.updateQuestionDataSourceCollect(index)
                    })
                }
            }
        }) {
            vm.loadQuestion(true)
        }

    }

}


/**
 * 文章列表项
 * @param item Data
 */
@Composable
fun QuestionItem(
    item: QuestionEntity.Data,
    itemClick: (String) -> Unit,
    collectClick: (() -> Unit)?
) {
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
                bitmap = ImageBitmap.imageResource(id = if (item.collect == true) R.mipmap.ic_collect_s else R.mipmap.ic_collect),
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
