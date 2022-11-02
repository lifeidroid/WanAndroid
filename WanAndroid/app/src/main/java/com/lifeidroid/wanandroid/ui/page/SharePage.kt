package com.lifeidroid.wanandroid.ui.page

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.ui.components.TitileBar
import com.lifeidroid.wanandroid.ui.components.dialog.DialogComponent
import com.lifeidroid.wanandroid.viewmodel.ShareViewModel

@Composable
fun SharePage(
    title: String,
    link: String,
    vm: ShareViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    goBack:()->Unit
) {
    LaunchedEffect(key1 = Unit, block = {
        vm.updateTitle(title)
        vm.updateLink(link)
    })
    DialogComponent(model = vm)
    Column(modifier) {
        TitileBar() {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_back),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        goBack()
                    }
                )
                Text(
                    text = "分享文章",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.text_white)
                )
            }
        }
        Column(modifier = Modifier.padding(horizontal = 16.dp).systemBarsPadding()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "文章标题", color = colorResource(id = R.color.text_black), fontSize = 12.sp)
            TextField(
                value = vm.title, onValueChange = {
                    vm.updateTitle(it)
                }, colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "文章链接", color = colorResource(id = R.color.text_black), fontSize = 12.sp)
            TextField(
                value = vm.link, onValueChange = {
                    vm.updateLink(it)
                }, colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    vm.share()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .heightIn(min = 45.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(
                        id = R.color.bg_blue
                    ),

                    )
            ) {
                Text(text = "分享", fontSize = 14.sp, color = colorResource(id = R.color.text_white))
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "1. 只要是任何好文都可以分享哈，并不一定要是原创！投递的文章会进入广场 tab;\n" +
                        "2. CSDN，掘金，简书等官方博客站点会直接通过，不需要审核;\n" +
                        "3. 其他个人站点会进入审核阶段，不要投递任何无效链接，测试的请尽快删除，否则可能会对你的账号产生一定影响;\n" +
                        "4. 目前处于测试阶段，如果你发现500等错误，可以向我提交日志，让我们一起使网站变得更好。\n" +
                        "5. 由于本站只有我一个人开发与维护，会尽力保证24小时内审核，当然有可能哪天太累，会延期，请保持佛系...",
                fontSize = 12.sp,
                color = colorResource(
                    id = R.color.text_gray
                ), lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.heightIn(16.dp))
        }

    }
}
