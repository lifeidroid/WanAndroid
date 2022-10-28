package com.lifeidroid.wanandroid.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.lifeidroid.wanandroid.R
import com.lifeidroid.wanandroid.base.BaseViewModel

@Composable
fun DialogComponent(
    model: BaseViewModel,
    loadingDismissCallback: (() -> Unit)? = null,//加载对话框回调事件
    alertDlgCallback: ((Int) -> Unit)? = null,//单按钮对话框回调事件
    conformDlgCallback: ((Int) -> Unit)? = null//确认对话框回调事件
) {
    LoadingDlgComponent(model, loadingDismissCallback = loadingDismissCallback)
    AlertDlgComponent(model, alertDlgCallback = alertDlgCallback)
    ConformDlgComponent(model, conformDlgCallback = conformDlgCallback)
}

/**
 * 加载对话框
 * @param model BaseViewModel
 */
@Composable
fun LoadingDlgComponent(model: BaseViewModel, loadingDismissCallback: (() -> Unit)? = null) {
    if (model.loadingDlgState.showState) {
        Dialog(
            onDismissRequest = {
                model.hideLoadingDlg()
                loadingDismissCallback?.invoke()
            },
            properties = DialogProperties(
                dismissOnBackPress = model.loadingDlgState.dismissOnBackPress,
                dismissOnClickOutside = model.loadingDlgState.dismissOnClickOutside
            )
        ) {
            //圆形进度条--无限循环
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .padding(horizontal = 30.dp, vertical = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = colorResource(id = R.color.bg_blue))
                Spacer(modifier = Modifier.requiredHeight(10.dp))
                Text(text = "${model.loadingDlgState.msg}", modifier = Modifier.clickable {
                    model.hideLoadingDlg()
                }, color = colorResource(id = R.color.text_gray))
            }

        }
    }
}

/**
 * 单按钮提醒对话框
 * @param model BaseViewModel
 */
@Composable
fun AlertDlgComponent(model: BaseViewModel, alertDlgCallback: ((Int) -> Unit)? = null) {
    if (model.alertDlgState.showState) {
        AlertDialog(
            onDismissRequest = { model.hideAlertDlg() },
            buttons = {
                Column {
                    Divider(
                        color = colorResource(
                            id = R.color.line_color
                        )
                    )
                    Text(
                        text = "${model.alertDlgState.btnText}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                alertDlgCallback?.invoke(0)
                                model.hideAlertDlg()
                            }
                            .padding(vertical = 10.dp),
                        textAlign = TextAlign.Center
                    )
                }

            },
            title = {
                Text(
                    text = "${model.alertDlgState.title}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            text = {
                Text(text = "${model.alertDlgState.content}")
            },
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color.White,
            contentColor = Color.Black,
            properties = DialogProperties(
                dismissOnBackPress = model.alertDlgState.dismissOnBackPress,
                dismissOnClickOutside = model.alertDlgState.dismissOnClickOutside
            )
        )
    }

}

@Composable
fun ConformDlgComponent(model: BaseViewModel, conformDlgCallback: ((Int) -> Unit)? = null) {
    if (model.conformDlgState.showState) {
        AlertDialog(
            onDismissRequest = { model.hideConformDlg() },
            buttons = {
                Column {
                    Divider(
                        color = colorResource(
                            id = R.color.line_color
                        )
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        model.conformDlgState.btnText.forEachIndexed { index, s ->
                            Text(
                                text = s,
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        conformDlgCallback?.invoke(index)
                                        model.hideConformDlg()
                                    }
                                    .padding(vertical = 10.dp),
                                textAlign = TextAlign.Center
                            )
                            if (index != model.conformDlgState.btnText.size -1){
                                Divider(
                                    color = colorResource(
                                        id = R.color.line_color
                                    ),
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(1.dp)
                                )
                            }

                        }
                    }


                }

            },
            title = {
                Text(
                    text = "${model.conformDlgState.title}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            text = {
                Text(text = "${model.conformDlgState.content}")
            },
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color.White,
            contentColor = Color.Black,
            properties = DialogProperties(
                dismissOnBackPress = model.conformDlgState.dismissOnBackPress,
                dismissOnClickOutside = model.conformDlgState.dismissOnClickOutside
            )
        )
    }
}
