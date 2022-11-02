package com.lifeidroid.wanandroid.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.viewmodel.WebViewModel
import kotlinx.coroutines.launch

@Composable
fun BottomMenuComonent(
    modifier: Modifier = Modifier,
    isCollected: Boolean,
    goHome: () -> Unit,
    exit: () -> Unit,
    close: () -> Unit,
    refresh: () -> Unit,
    goTop: () -> Unit,
    doCollect: () -> Unit,
    share:()->Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "网页由 www.wanandroid.com 提供",
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,
            color = colorResource(id = R.color.text_gray)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { goHome() },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_home),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(
                            CircleShape
                        )
                        .background(color = colorResource(id = R.color.bg_gray))
                        .padding(14.dp)

                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "回到首页",
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.text_black)
                )
            }
            Column(
                modifier = Modifier.weight(1f).clickable { share() },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_share_article),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(
                            CircleShape
                        )
                        .background(color = colorResource(id = R.color.bg_gray))
                        .padding(14.dp)

                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "分享到广场",
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.text_black)
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        doCollect()
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_collect),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(
                            CircleShape
                        )
                        .background(color = colorResource(id = if (isCollected) R.color.bg_blue else R.color.bg_gray))
                        .padding(14.dp),
                    tint = colorResource(id = if (isCollected) R.color.text_white else R.color.text_black)

                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "收藏",
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.text_black)
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_home),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(
                            CircleShape
                        )
                        .background(color = colorResource(id = R.color.bg_gray))
                        .padding(14.dp)

                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "书签",
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.text_black)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { refresh() },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_refresh),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(
                            CircleShape
                        )
                        .background(color = colorResource(id = R.color.bg_gray))
                        .padding(14.dp)

                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "刷新",
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.text_black)
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { goTop() },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_swipe_back),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(
                            CircleShape
                        )
                        .rotate(90f)
                        .background(color = colorResource(id = R.color.bg_gray))
                        .padding(14.dp)

                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "回到顶部",
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.text_black)
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_http_interrupt),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(
                            CircleShape
                        )
                        .background(color = colorResource(id = R.color.bg_gray))
                        .padding(14.dp)

                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "不拦截",
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.text_black)
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { exit() },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_exit),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(
                            CircleShape
                        )
                        .background(color = colorResource(id = R.color.bg_gray))
                        .padding(14.dp)

                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "退出",
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.text_black)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Row(
            modifier = Modifier
                .height(45.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_share),
                contentDescription = null, tint = colorResource(id = R.color.text_black),
                modifier = Modifier.clickable {

                }
            )
            Icon(
                bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_close),
                contentDescription = null, tint = colorResource(id = R.color.text_black),
                modifier = Modifier.clickable {
                    close()
                }
            )
            Icon(
                bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_setting),
                contentDescription = null, tint = colorResource(id = R.color.text_black),
                modifier = Modifier.clickable {
                }
            )
        }
    }

}

