package com.lifeidroid.wanandroid.model.entity

import androidx.lifecycle.LiveData
import com.lifeidroid.wanandroid.base.BaseModel
import com.lifeidroid.wanandroid.http.ResultData
import com.lifeidroid.wanandroid.http.customRequest
import com.lifeidroid.wanandroid.model.entity.net.UserInfoEntity
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
class UserModel @Inject constructor(): BaseModel() {
    suspend fun getUserInfo(): LiveData<ResultData<UserInfoEntity>> {
        return customRequest<UserInfoEntity> {
            api {
                httpApi.getUserInfo()
            }
        }
    }
}