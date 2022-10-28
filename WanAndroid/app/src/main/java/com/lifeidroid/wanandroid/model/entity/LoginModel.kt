package com.lifeidroid.wanandroid.model.entity

import androidx.lifecycle.LiveData
import com.lifeidroid.wanandroid.base.BaseModel
import com.lifeidroid.wanandroid.http.ResultData
import com.lifeidroid.wanandroid.http.customRequest
import com.lifeidroid.wanandroid.model.entity.net.BannerEntity
import com.lifeidroid.wanandroid.model.entity.net.LoginEntity
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
class LoginModel @Inject constructor() : BaseModel() {
    /**
     * 登录
     * @param userName String
     * @param password String
     * @return LiveData<ResultData<LoginEntity>>
     */
    suspend fun doLogin(userName: String, password: String): LiveData<ResultData<LoginEntity>> {
        return customRequest<LoginEntity> {
            api {
                httpApi.login(userName, password)
            }
        }
    }
}