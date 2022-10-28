package com.lifeidroid.wanandroid.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.ui.components.TitileBar
import com.lifeidroid.wanandroid.ui.widget.SectorView
import com.lifeidroid.wanandroid.viewmodel.LoginViewModel

@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    vm: LoginViewModel = hiltViewModel(),
    goBack: () -> Unit,
    goMainPage: () -> Unit,
    goRegister: () -> Unit
) {

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = true
        )
        onDispose {}
    }

    LaunchedEffect(key1 = vm.loginState, block = {
        if (vm.loginState){
            goMainPage()
        }
    })

    Column() {
        Column(
            modifier = Modifier.background(color = colorResource(id = R.color.bg_blue)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitileBar {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_close),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            goBack()
                        }
                    )
                }
            }
            Image(
                bitmap = ImageBitmap.imageResource(id = R.mipmap.logo),
                contentDescription = null,
                modifier = Modifier.size(110.dp)
            )

            Text(text = "欢迎使用", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(
                text = "本APP由lifeidroid独立开发",
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )

            SectorView(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(50.dp)
            )
        }
        Column(
            Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .padding(horizontal = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "去注册", color = colorResource(id = R.color.text_blue), fontSize = 14.sp)
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_next),
                    contentDescription = null,
                    tint = colorResource(
                        id = R.color.text_blue
                    ), modifier = Modifier
                        .padding(start = 8.dp)
                        .size(15.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextField(
                    value = "${vm.userName}",
                    onValueChange = { vm.updateUserName(it) },
                    placeholder = {
                        if (vm.userName.isNullOrBlank()) {
                            Text(text = "请输入用户名")
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = colorResource(id = R.color.text_blue),
                        unfocusedIndicatorColor = colorResource(id = R.color.text_gray)
                    ),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_account_normal),
                            contentDescription = null,
                            tint = colorResource(id = R.color.text_gray),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                TextField(
                    value = "${vm.password}",
                    onValueChange = { vm.updatePassword(it) },
                    placeholder = {
                        if (vm.password.isNullOrBlank()) {
                            Text(text = "请输入密码")
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = colorResource(id = R.color.text_blue),
                        unfocusedIndicatorColor = colorResource(id = R.color.text_gray)
                    ),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_password_normal),
                            contentDescription = null,
                            tint = colorResource(id = R.color.text_gray),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                onClick = { vm.doLogin() },
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.bg_blue))
            ) {
                Text(text = "登录", color = colorResource(id = R.color.text_white), fontSize = 14.sp)
            }

        }
    }


}
