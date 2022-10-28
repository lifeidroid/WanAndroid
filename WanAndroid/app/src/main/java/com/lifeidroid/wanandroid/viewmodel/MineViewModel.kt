package com.lifeidroid.wanandroid.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lifeidroid.wanandroid.base.BaseViewModel
import com.lifeidroid.wanandroid.model.entity.UserModel
import com.lifeidroid.wanandroid.model.entity.net.UserInfoEntity
import com.lifeidroid.wanandroid.utils.ResultDataUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@HiltViewModel
class MineViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {


    var userInfo by mutableStateOf(UserInfoEntity())
        private set
    @Inject
    lateinit var userModel: UserModel

    fun getUserInfo() {
        Log.d("===", "个人信息：getUserInfo")
        viewModelScope.launch {
            userModel.getUserInfo().observeForever {
                ResultDataUtils.dellObjectData(it,this@MineViewModel){ info->
                    userInfo = info
                }
            }
        }
    }
}