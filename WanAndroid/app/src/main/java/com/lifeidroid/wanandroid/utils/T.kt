package com.lifeidroid.wanandroid.utils

import android.widget.Toast
import com.lifeidroid.wanandroid.App

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */
object T {
    fun showToast(msg: String) {
        Toast.makeText(App.instance, msg, Toast.LENGTH_SHORT).show()
    }
}