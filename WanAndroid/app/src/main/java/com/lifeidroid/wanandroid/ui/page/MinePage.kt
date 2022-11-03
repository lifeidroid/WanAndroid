package com.lifeidroid.wanandroid.ui.page

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.ui.components.StatefulContent
import com.lifeidroid.wanandroid.ui.components.TitileBar
import com.lifeidroid.wanandroid.viewmodel.MineViewModel

@Composable
fun MinePage(
    goMyInfo: () -> Unit,
    goMyCoinPage: () -> Unit,
    goMyShare:()->Unit,
    modifier: Modifier = Modifier,
    vm: MineViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit, block = {
        vm.getUserInfo()
    })

    StatefulContent(content = {
        Column {
            TitileBar(Modifier) {
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        bitmap = ImageBitmap.imageResource(R.mipmap.ic_notification),
                        contentDescription = null,
                        tint = Color.White, modifier = Modifier.clickable {
                        }
                    )
                }
            }
            LazyColumn() {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = colorResource(id = R.color.bg_blue))
                            .padding(bottom = 40.dp),
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
                                )
                                .clickable {
                                    goMyInfo()
                                },

                            )

                        Text(
                            text = "${vm.userInfo.userInfo?.nickname}",
                            color = colorResource(id = R.color.text_white),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Text(
                            text = "等级：${vm.userInfo.coinInfo?.level}   排名：${vm.userInfo.coinInfo?.rank}",
                            color = colorResource(id = R.color.text_white),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }

                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(50.dp)
                            .clickable { goMyCoinPage() }
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 8.dp)
                                .size(16.dp),
                            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_coin),
                            contentDescription = null,
                            tint = colorResource(
                                id = R.color.bg_blue
                            ),
                        )
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "我的积分",
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.text_black)
                        )
                        Icon(
                            modifier = Modifier.padding(end = 16.dp),
                            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_enter),
                            contentDescription = null,
                            tint = colorResource(
                                id = R.color.text_gray
                            )
                        )
                    }
                }
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.height(50.dp).clickable {
                            goMyShare()
                        }
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 8.dp)
                                .size(16.dp),
                            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_share_article),
                            contentDescription = null,
                            tint = colorResource(
                                id = R.color.bg_blue
                            ),
                        )
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "我的分享",
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.text_black)
                        )
                        Icon(
                            modifier = Modifier.padding(end = 16.dp),
                            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_enter),
                            contentDescription = null,
                            tint = colorResource(
                                id = R.color.text_gray
                            )
                        )
                    }
                }
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.height(50.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 8.dp)
                                .size(16.dp),
                            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_collect),
                            contentDescription = null,
                            tint = colorResource(
                                id = R.color.bg_blue
                            ),
                        )
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "我的收藏",
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.text_black)
                        )
                        Icon(
                            modifier = Modifier.padding(end = 16.dp),
                            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_enter),
                            contentDescription = null,
                            tint = colorResource(
                                id = R.color.text_gray
                            )
                        )
                    }
                }
//                item {
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        modifier = Modifier.height(50.dp)
//                    ) {
//                        Icon(
//                            modifier = Modifier
//                                .padding(start = 16.dp, end = 8.dp)
//                                .size(16.dp),
//                            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_read_later),
//                            contentDescription = null,
//                            tint = colorResource(
//                                id = R.color.bg_blue
//                            ),
//                        )
//                        Text(
//                            modifier = Modifier.weight(1f),
//                            text = "我的书签",
//                            fontSize = 14.sp,
//                            color = colorResource(id = R.color.text_black)
//                        )
//                        Icon(
//                            modifier = Modifier.padding(end = 16.dp),
//                            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_enter),
//                            contentDescription = null,
//                            tint = colorResource(
//                                id = R.color.text_gray
//                            )
//                        )
//                    }
//                }
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.height(50.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 8.dp)
                                .size(16.dp),
                            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_read_record),
                            contentDescription = null,
                            tint = colorResource(
                                id = R.color.bg_blue
                            ),
                        )
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "阅读历史",
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.text_black)
                        )
                        Icon(
                            modifier = Modifier.padding(end = 16.dp),
                            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_enter),
                            contentDescription = null,
                            tint = colorResource(
                                id = R.color.text_gray
                            )
                        )
                    }
                }
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.height(50.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 8.dp)
                                .size(16.dp),
                            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_github),
                            contentDescription = null,
                            tint = colorResource(
                                id = R.color.bg_blue
                            ),
                        )
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "开源项目",
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.text_black)
                        )
                        Icon(
                            modifier = Modifier.padding(end = 16.dp),
                            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_enter),
                            contentDescription = null,
                            tint = colorResource(
                                id = R.color.text_gray
                            )
                        )
                    }
                }
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.height(50.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 8.dp)
                                .size(16.dp),
                            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_about),
                            contentDescription = null,
                            tint = colorResource(
                                id = R.color.bg_blue
                            ),
                        )
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "关于作者",
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.text_black)
                        )
                        Icon(
                            modifier = Modifier.padding(end = 16.dp),
                            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_enter),
                            contentDescription = null,
                            tint = colorResource(
                                id = R.color.text_gray
                            )
                        )
                    }
                }
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.height(50.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 8.dp)
                                .size(16.dp),
                            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_setting),
                            contentDescription = null,
                            tint = colorResource(
                                id = R.color.bg_blue
                            ),
                        )
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "系统设置",
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.text_black)
                        )
                        Icon(
                            modifier = Modifier.padding(end = 16.dp),
                            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_enter),
                            contentDescription = null,
                            tint = colorResource(
                                id = R.color.text_gray
                            )
                        )
                    }
                }
            }

        }
    }) {
        vm.getUserInfo()
    }


}