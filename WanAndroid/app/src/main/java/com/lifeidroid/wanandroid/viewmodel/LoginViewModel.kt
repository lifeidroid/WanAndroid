package com.lifeidroid.wanandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lifeidroid.wanandroid.Constants
import com.lifeidroid.wanandroid.base.BaseViewModel
import com.lifeidroid.wanandroid.ext.toJsonString
import com.lifeidroid.wanandroid.http.RequestStatus
import com.lifeidroid.wanandroid.model.entity.LoginModel
import com.lifeidroid.wanandroid.utils.SPUtils
import com.lifeidroid.wanandroid.utils.T
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
class LoginViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {

    @Inject
    lateinit var model: LoginModel

    var userName by mutableStateOf(
        ""
    )
        private set

    fun updateUserName(temp: String) {
        userName = temp
    }

    var password by mutableStateOf(
        ""
    )
        private set

    fun updatePassword(temp: String) {
        password = temp
    }

    //登录状态
    var loginState by mutableStateOf(
        false
    )
        private set

    /**
     * 检查输入项
     * @return String
     */
    fun checkParams(): String {
        if (userName.isNullOrBlank()) {
            return "请输入用户名"
        }
        if (password.isNullOrBlank()) {
            return "请输入密码"
        }
        return ""
    }


    fun doLogin() {
        var msg = checkParams()
        if (!msg.isNullOrBlank()) {
            T.showToast(msg)
            return
        }
        viewModelScope.launch {
            model.doLogin(userName, password).observeForever {
                when (it.requestStatus) {
                    RequestStatus.SUCCESS -> {
                        loginState = true
                        SPUtils.instance?.put(Constants.SP_LOGIN,true)
                    }
                    RequestStatus.ERROR -> {
                        T.showToast(it.error!!.message!!)
                    }
                }
            }
        }
    }
}