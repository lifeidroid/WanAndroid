package com.lifeidroid.wanandroid.ui.page

import android.os.Handler
import android.util.Log
import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.ext.encode
import com.lifeidroid.wanandroid.ui.components.BottomMenuComonent
import com.lifeidroid.wanandroid.ui.components.TitileBar
import com.lifeidroid.wanandroid.ui.components.dialog.DialogComponent
import com.lifeidroid.wanandroid.viewmodel.WebViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * 浏览器页面
 * @param modifier Modifier
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WebPage(
    url: String,
    modifier: Modifier = Modifier,
    wm: WebViewModel = hiltViewModel(),
    share: (String, String) -> Unit,
    back: () -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }
    val state = rememberWebViewState(url = url)
    val webViewNavigator = rememberWebViewNavigator()

    val coroutineScope = rememberCoroutineScope()

    val client: AccompanistWebViewClient = remember {
        object : AccompanistWebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                title = "${view?.title}"
                super.onPageFinished(view, url)
            }
        }
    }
    

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            BottomMenuComonent(
                isCollected = wm.isCollected(state.content.getCurrentUrl()!!),
                goHome = {
                    wm.webView?.loadUrl(url)
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                },
                exit = {
                    back()
                },
                close = {
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                },
                refresh = {
                    webViewNavigator.reload()
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                },
                goTop = {
                    wm.webView.let {
                        Handler().postDelayed({ it.scrollTo(0, 0) }, 500)
                    }
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                },
                doCollect = {
                    wm.operatArticle("${state.content.getCurrentUrl()}", title)
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                }, share = {
                    share(title, state.content.getCurrentUrl()!!)
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                })
        }) {
        BackHandler(sheetState.isVisible) {
            coroutineScope.launch {
                sheetState.hide()
            }
        }
        Column {
            TitileBar {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(color = colorResource(id = R.color.bg_search))
                        .padding(vertical = 4.dp, horizontal = 8.dp),

                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        bitmap = ImageBitmap.imageResource(id = if (wm.isCollected(state.content.getCurrentUrl()!!)) R.mipmap.ic_collect_s else R.mipmap.ic_collect),
                        contentDescription = null,
                        modifier = Modifier
                            .size(17.dp)
                            .clickable {
                                wm.operatArticle("${state.content.getCurrentUrl()}", title)
                            },
                        tint = Color.Red,

                        )
                    Text(
                        text = "$title",
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        maxLines = 1,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Icon(
                        bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_into),
                        contentDescription = null,
                        modifier = Modifier.size(17.dp),
                        tint = Color.White
                    )
                }
            }
            WebView(modifier = Modifier.weight(1f), state = state, onCreated = {
                wm.webView = it
                it.settings.javaScriptEnabled = true
            }, captureBackPresses = true, navigator = webViewNavigator, client = client)
            Divider()
            Row(
                modifier = Modifier
                    .height(45.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    bitmap = ImageBitmap.imageResource(id = if (webViewNavigator.canGoBack) R.mipmap.ic_back else R.mipmap.ic_close),
                    contentDescription = null, tint = colorResource(id = R.color.text_black),
                    modifier = Modifier.clickable {
                        if (webViewNavigator.canGoBack) {
                            webViewNavigator.navigateBack()
                        } else {
                            back()
                        }
                    }
                )
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_menu),
                    contentDescription = null, tint = colorResource(id = R.color.text_black),
                    modifier = Modifier.clickable {
                        coroutineScope.launch {
                            sheetState.show()
                        }
                    }
                )
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_enter),
                    contentDescription = null,
                    tint = if (webViewNavigator.canGoForward) colorResource(
                        id = R.color.text_gray
                    ) else colorResource(id = R.color.text_black),
                    modifier = Modifier.clickable {
                        if (webViewNavigator.canGoForward) {
                            webViewNavigator.navigateForward()
                        }
                    }
                )
            }
        }

    }

}
