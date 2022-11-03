package com.lifeidroid.wanandroid.ui.page

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.ui.components.TitileBar
import com.lifeidroid.wanandroid.viewmodel.MyInfoViewModel

/**
 * 个人资料页面
 * @param modifier Modifier
 * @param goBack Function0<Unit>
 */
@Composable
fun MyInfoPage(
    modifier: Modifier = Modifier,
    vm: MyInfoViewModel = hiltViewModel(),
    goBack: () -> Unit
) {

    LaunchedEffect(key1 = Unit, block = {
        vm.getUserInfo()
    })

    Column() {
        TitileBar {
            Box(contentAlignment = Alignment.CenterStart) {
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_back),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        goBack()
                    }
                )
                Text(
                    text = "个人资料",
                    color = colorResource(id = R.color.text_white),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.bg_blue))
                .padding(vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.mipmap.ic_icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .border(
                        border = BorderStroke(2.dp, Color.White),
                        shape = CircleShape
                    ),

                )

        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "玩ID", color = colorResource(id = R.color.text_black), fontSize = 14.sp)
            Text(
                text = "${vm.userInfo.userInfo!!.id}",
                color = colorResource(id = R.color.text_gray),
                fontSize = 14.sp
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "用户名", color = colorResource(id = R.color.text_black), fontSize = 14.sp)
            Text(
                text = "${vm.userInfo.userInfo!!.nickname}",
                color = colorResource(id = R.color.text_gray),
                fontSize = 14.sp
            )
        }
    }

}
