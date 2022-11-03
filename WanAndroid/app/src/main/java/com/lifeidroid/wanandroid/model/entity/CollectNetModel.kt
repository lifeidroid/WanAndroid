package com.lifeidroid.wanandroid.model.entity

import androidx.lifecycle.LiveData
import com.lifeidroid.wanandroid.base.BaseModel
import com.lifeidroid.wanandroid.http.ResultData
import com.lifeidroid.wanandroid.http.customRequest
import com.lifeidroid.wanandroid.model.entity.net.CollectNetEntity
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
class CollectNetModel @Inject constructor() : BaseModel() {
    /**
     * 6.5 收藏网站列表
     */
    suspend fun getCollectedList(): LiveData<ResultData<MutableList<CollectNetEntity>>> {
        return customRequest {
            api {
                httpApi.getNetCollect()
            }
        }
    }

    /**
     * 6.8 删除收藏网站
     * @param id Int
     * @return LiveData<ResultData<String>>
     */
    suspend fun deleteCollected(id:Int):LiveData<ResultData<String>> {
        return customRequest {
            api {
                httpApi.deleteNetCollect(id)

            }
        }
    }
}