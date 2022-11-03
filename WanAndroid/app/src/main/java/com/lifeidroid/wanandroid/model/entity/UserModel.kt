package com.lifeidroid.wanandroid.model.entity

import androidx.lifecycle.LiveData
import com.lifeidroid.wanandroid.Constants
import com.lifeidroid.wanandroid.base.BaseModel
import com.lifeidroid.wanandroid.ext.toJsonString
import com.lifeidroid.wanandroid.ext.toObj
import com.lifeidroid.wanandroid.http.ResultData
import com.lifeidroid.wanandroid.http.customRequest
import com.lifeidroid.wanandroid.model.entity.net.UserInfoEntity
import com.lifeidroid.wanandroid.utils.SPUtils
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
class UserModel @Inject constructor() : BaseModel() {
    suspend fun getUserInfo(): LiveData<ResultData<UserInfoEntity>> {
        return customRequest<UserInfoEntity> {
            api {
                httpApi.getUserInfo()
            }

            if (SPUtils.instance.contains(Constants.SP_USER_INFO)) {
                loadCache {
                    SPUtils.instance.getString(Constants.SP_USER_INFO)!!
                        .toObj(UserInfoEntity::class.java)
                }
            }
            saveCache {
                SPUtils.instance.put(Constants.SP_USER_INFO, it.toJsonString())
            }
        }
    }
}