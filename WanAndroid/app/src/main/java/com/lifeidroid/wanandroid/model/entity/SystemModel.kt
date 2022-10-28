package com.lifeidroid.wanandroid.model.entity

import androidx.lifecycle.LiveData
import com.lifeidroid.wanandroid.base.BaseModel
import com.lifeidroid.wanandroid.http.ResultData
import com.lifeidroid.wanandroid.http.customRequest
import com.lifeidroid.wanandroid.model.entity.net.NavigationEntity
import com.lifeidroid.wanandroid.model.entity.net.SystemEntity
import javax.inject.Inject

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class SystemModel @Inject constructor(): BaseModel(){
    /**
     * 2.1 体系数据
     */
    suspend fun getSystemTree(): LiveData<ResultData<MutableList<SystemEntity>>> {
        return customRequest<MutableList<SystemEntity>> {
            api {
                httpApi.getSystemTree()
            }
        }
    }
    /**
     * 3.1 导航数据
     */
    suspend fun getNaviTree(): LiveData<ResultData<MutableList<NavigationEntity>>> {
        return customRequest<MutableList<NavigationEntity>> {
            api {
                httpApi.getNaviTree()
            }
        }
    }
}