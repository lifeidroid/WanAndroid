package com.lifeidroid.wanandroid.base

import com.lifeidroid.wanandroid.Constants
import com.lifeidroid.wanandroid.api.HttpApi
import javax.inject.Inject

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/07/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
open class BaseModel {
    @Inject
    lateinit var httpApi: HttpApi
    var pageNumber = Constants.PAGE_START

    fun correctPageNumber() {
        if (pageNumber == Constants.PAGE_SIZE) {
            return
        }
        pageNumber--
    }

}