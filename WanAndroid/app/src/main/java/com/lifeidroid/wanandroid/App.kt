package com.lifeidroid.wanandroid

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@HiltAndroidApp
class App:Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}