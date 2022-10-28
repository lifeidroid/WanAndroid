package com.lifeidroid.wanandroid.model.entity

import androidx.lifecycle.LiveData
import com.lifeidroid.wanandroid.Constants
import com.lifeidroid.wanandroid.base.BaseModel
import com.lifeidroid.wanandroid.http.ResultData
import com.lifeidroid.wanandroid.http.customRequest
import com.lifeidroid.wanandroid.model.entity.net.MyCoinDetailEntity
import com.lifeidroid.wanandroid.model.entity.net.MyCoinHistoryEntity
import javax.inject.Inject

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/28
 *     desc   : 我的积分
 *     version: 1.0
 * </pre>
 */
class MyCoinModel @Inject constructor(): BaseModel() {

    /**
     * 获取积分详情
     * @return LiveData<ResultData<MyCoinDetailEntity>>
     */
    suspend fun getMyCoinDetail():LiveData<ResultData<MyCoinDetailEntity>>{
       return customRequest {
            api {
                httpApi.getMyCoinDetail()
            }
        }
    }
    /**
     * 获取积分详情
     * @return LiveData<ResultData<MyCoinDetailEntity>>
     */
    suspend fun getMyCoinHistory(isRefresh:Boolean):LiveData<ResultData<MyCoinHistoryEntity>>{
        if (isRefresh) {
            pageNumber = Constants.PAGE_START
        } else {
            pageNumber++
        }
       return customRequest {
            api {
                httpApi.getMyCoinHistory(pageNumber)
            }
           requestTag {
               isRefresh
           }
        }
    }
}