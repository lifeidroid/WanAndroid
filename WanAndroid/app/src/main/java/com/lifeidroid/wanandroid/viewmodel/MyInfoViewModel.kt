package com.lifeidroid.wanandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import com.lifeidroid.wanandroid.Constants
import com.lifeidroid.wanandroid.base.BaseViewModel
import com.lifeidroid.wanandroid.ext.toObj
import com.lifeidroid.wanandroid.model.entity.net.UserInfoEntity
import com.lifeidroid.wanandroid.utils.SPUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/11/03
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@HiltViewModel
class MyInfoViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {

    var userInfo by mutableStateOf(UserInfoEntity())

    /**
     * 获取用户信息
     */
    fun getUserInfo() {
        if (SPUtils.instance.contains(Constants.SP_USER_INFO)) {
            userInfo = SPUtils.instance.getString(Constants.SP_USER_INFO)!!
                .toObj(UserInfoEntity::class.java)
        }
    }
}