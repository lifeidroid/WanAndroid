package com.lifeidroid.wanandroid.model.entity

import androidx.lifecycle.LiveData
import com.lifeidroid.wanandroid.Constants
import com.lifeidroid.wanandroid.api.HttpApi
import com.lifeidroid.wanandroid.base.BaseModel
import com.lifeidroid.wanandroid.http.ResultData
import com.lifeidroid.wanandroid.http.customRequest
import com.lifeidroid.wanandroid.model.entity.net.ArticleEntity
import com.lifeidroid.wanandroid.model.entity.net.BannerEntity
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
class SystemArticleModel @Inject constructor() : BaseModel() {

    /**
     * 2.2 知识体系下的文章
     */
    suspend fun getArticle(isRefresh: Boolean, id: Int): LiveData<ResultData<ArticleEntity>> {
        if (isRefresh) {
            pageNumber = Constants.PAGE_START
        } else {
            pageNumber++
        }
        return customRequest<ArticleEntity> {
            api {
                httpApi.getSystemArticleList(pageNumber, id)
            }
            requestTag {
                isRefresh
            }
        }
    }
}