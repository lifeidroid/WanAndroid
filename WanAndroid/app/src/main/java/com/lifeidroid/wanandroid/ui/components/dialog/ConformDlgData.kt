package com.lifeidroid.wanandroid.ui.components.dialog

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class ConformDlgData(
    var title: String = "",
    var content: String = "",
    var btnText: List<String> = listOf("确认", "取消"),
    showState: Boolean,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true
) : BaseDlgData(showState, dismissOnBackPress, dismissOnClickOutside) {
}