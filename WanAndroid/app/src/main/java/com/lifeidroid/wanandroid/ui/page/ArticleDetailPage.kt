package com.lifeidroid.wanandroid.ui.page

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.lifeidroid.wanandroid.R

@Composable
fun ArticleDetailPage(
    url: String,
    modifier: Modifier = Modifier,
    back: () -> Unit
) {

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = Color.White,
            darkIcons = true
        )
        onDispose {}
    }
    Log.d("===","articleDetail:$url")
//    val state = rememberWebViewState("http://www.baidu.com")
    val state = rememberWebViewState(url = url)
    Column(modifier = Modifier.systemBarsPadding()) {
        Box(contentAlignment = Alignment.BottomStart) {
            WebView(state = state, onCreated = {
                it.settings.javaScriptEnabled = true
            }, captureBackPresses = true)
            Button(
                onClick = { back() },
                shape = CircleShape,
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 60.dp)
                    .size(45.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_back),
                    contentDescription = null
                )
            }
        }
    }


}
