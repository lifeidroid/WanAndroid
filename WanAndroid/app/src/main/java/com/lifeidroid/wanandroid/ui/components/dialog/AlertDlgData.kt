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
class AlertDlgData(
    var title: String = "",
    var content: String = "",
    var btnText: String = "",
    showState: Boolean,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true
) : BaseDlgData(showState, dismissOnBackPress, dismissOnClickOutside) {
}