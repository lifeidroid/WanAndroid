package com.lifeidroid.wanandroid.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.ui.components.StatefulContent
import com.lifeidroid.wanandroid.viewmodel.SystemViewModel

@Composable
fun NavigationForm(
    modifier: Modifier = Modifier,
    vm:SystemViewModel,
    articleDetail: (String) -> Unit
) {
    LaunchedEffect(key1 = true, block = {
        vm.getNaviTree()
    })

    StatefulContent(content = {
        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            vm.naviDatas.forEach { temp ->
                item {
                    Text(
                        text = "${temp.name}",
                        color = colorResource(id = R.color.text_black),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                    )
                }

                if (!temp.articles.isNullOrEmpty()) {
                    item {
                        FlowRow(
                            mainAxisSpacing = 8.dp,
                            crossAxisSpacing = 8.dp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            for (child in temp.articles!!) {
                                Box(
                                    modifier = Modifier
                                        .height(30.dp)
                                        .clip(
                                            RoundedCornerShape(16.dp)
                                        ).clickable {
                                            articleDetail(child.link!!)
                                        }
                                        .background(color = colorResource(id = R.color.line_color))
                                        .padding(horizontal = 12.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "${child.title}",
                                        color = colorResource(id = R.color.text_black),
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }
                }

            }
        }
    }) {
        vm.getSystemTree()
    }
}