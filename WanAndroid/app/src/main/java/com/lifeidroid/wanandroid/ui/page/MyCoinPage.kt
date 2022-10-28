package com.lifeidroid.wanandroid.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.model.entity.net.MyCoinHistoryEntity
import com.lifeidroid.wanandroid.ui.components.SwipeLazyColum
import com.lifeidroid.wanandroid.ui.components.TitileBar
import com.lifeidroid.wanandroid.viewmodel.MyCoinViewModel

/**
 * 我的积分
 * @param modifier Modifier
 * @param vm MyCoinViewModel
 * @param goBack Function0<Unit>
 */
@Composable
fun MyCoinPage(
    modifier: Modifier = Modifier,
    vm: MyCoinViewModel = hiltViewModel(),
    goBack: () -> Unit
) {

    LaunchedEffect(key1 = Unit, block = {
        vm.getMyCoinDetail()
        vm.getMyCoinHistory(true)
    })
    Column {
        TitileBar() {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_back),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            goBack()
                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_rule),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Icon(
                        bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_rank),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Text(
                    text = "我的积分",
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.bg_blue))
                .height(180.dp), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${vm.myCoinDetail.coinCount}",
                color = colorResource(id = R.color.text_white),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold, fontSize = 80.sp
            )
        }
        SwipeLazyColum(
            swipeLazyColumState = vm.swipeLazyColumState,
            onRefreshCallBack = { vm.getMyCoinHistory(true) },
            loadMoreCallBack = { vm.getMyCoinHistory(false) },
        ) {
            items(vm.myCoinHistoryData) { item ->
                MyCoinHistoryItem(modifier = Modifier, item)
            }
        }
    }


}

@Composable
fun MyCoinHistoryItem(modifier: Modifier, item: MyCoinHistoryEntity.Data) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "${item.desc?.substring(20)?.replace(",", "")}",
                fontSize = 14.sp,
                color = colorResource(id = R.color.text_black)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${item.desc?.substring(0, 19)}",
                fontSize = 12.sp,
                color = colorResource(id = R.color.text_gray)
            )
        }
        Text(
            text = "+${item.coinCount}",
            fontSize = 14.sp,
            color = colorResource(id = R.color.text_blue)
        )
    }
}
