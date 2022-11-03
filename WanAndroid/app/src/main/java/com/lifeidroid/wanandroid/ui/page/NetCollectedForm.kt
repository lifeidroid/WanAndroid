package com.lifeidroid.wanandroid.ui.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.ext.getMValue
import com.lifeidroid.wanandroid.model.entity.net.CollectNetEntity
import com.lifeidroid.wanandroid.ui.components.LoadMoreState
import com.lifeidroid.wanandroid.ui.components.StatefulContent
import com.lifeidroid.wanandroid.ui.components.SwipeLazyColum
import com.lifeidroid.wanandroid.viewmodel.MyCollectedViewModel
import com.lifeidroid.wanandroid.viewmodel.NetCollectedViewModel

/**
 * 我收藏的网址
 * @param modifier Modifier
 * @param vm NetCollectedViewModel
 * @param goWebPage Function1<String, Unit>
 */
@Composable
fun NetCollectedForm(
    modifier: Modifier = Modifier,
    vm: NetCollectedViewModel = hiltViewModel(),
    goWebPage: (String) -> Unit
) {
    LaunchedEffect(key1 = Unit, block = {
        vm.getCollectedNet()
    })
    StatefulContent(state = vm.statefullState, content = {
        SwipeLazyColum(
            swipeLazyColumState = vm.swipeLazyColumState,
            onRefreshCallBack = { vm.getCollectedNet() },
            loadMoreCallBack = {},
            content = {
                items(vm.collectedNetList) {
                    CollectedItem(item = it, goWebPage = goWebPage)
                }
            }
        )
    }) {
        vm.getCollectedNet()
    }
}

@Composable
fun CollectedItem(item: CollectNetEntity, goWebPage: (String) -> Unit) {
    Column(modifier = Modifier
        .clickable {
            goWebPage(item.link.getMValue())
        }
        .padding(16.dp)) {
        Text(
            text = item.name.getMValue(),
            color = colorResource(id = R.color.text_black),
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.link.getMValue(),
            color = colorResource(id = R.color.text_gray),
            fontSize = 12.sp
        )
    }
}