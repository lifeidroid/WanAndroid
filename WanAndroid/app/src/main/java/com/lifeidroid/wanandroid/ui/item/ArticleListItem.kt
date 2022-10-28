package com.lifeidroid.wanandroid.ui.item

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.ext.formateDateStr
import com.lifeidroid.wanandroid.model.entity.net.ArticleEntity

/**
 * 文章列表项
 * @param item Data
 */
@Composable
fun ArticleListItem(
    item: ArticleEntity.Data,
    isTop: Boolean,
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