package com.lifeidroid.wanandroid.model.entity

import androidx.lifecycle.LiveData
import com.lifeidroid.wanandroid.Constants
import com.lifeidroid.wanandroid.api.HttpApi
import com.lifeidroid.wanandroid.base.BaseModel
import com.lifeidroid.wanandroid.http.ResultData
import com.lifeidroid.wanandroid.http.customRequest
import com.lifeidroid.wanandroid.model.entity.net.ArticleEntity
import com.lifeidroid.wanandroid.model.entity.net.BannerEntity
import com.lifeidroid.wanandroid.model.entity.net.QuestionEntity
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/18
 *     desc   : 主页面model
 *     version: 1.0
 * </pre>
 */
class QuestionModel @Inject constructor() :BaseModel(){

    /**
     * 11 问答
     */
    suspend fun getWenDa(isRefresh:Boolean): LiveData<ResultData<QuestionEntity>> {
        if (isRefresh) {
            pageNumber = Constants.PAGE_START
        } else {
            pageNumber++
        }
        return customRequest<QuestionEntity> {
            api {
                httpApi.getWenDa(pageNumber)
            }
            requestTag {
                isRefresh
            }
        }
    }
}