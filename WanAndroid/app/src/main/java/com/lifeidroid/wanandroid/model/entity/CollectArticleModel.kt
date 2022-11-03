package com.lifeidroid.wanandroid.model.entity

import androidx.lifecycle.LiveData
import com.lifeidroid.wanandroid.Constants
import com.lifeidroid.wanandroid.base.BaseModel
import com.lifeidroid.wanandroid.http.ResultData
import com.lifeidroid.wanandroid.http.customRequest
import com.lifeidroid.wanandroid.model.entity.net.CollectArticleEnity
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
class CollectArticleModel @Inject constructor() : BaseModel() {


    /**
     * 6.1 收藏文章列表
     * @param isRefresh Boolean
     * @return LiveData<ResultData<CollectArticleEnity>>
     */
    suspend fun getCollectedArticles(isRefresh: Boolean): LiveData<ResultData<CollectArticleEnity>> {
        if (isRefresh) {
            pageNumber = Constants.PAGE_START
        } else {
            pageNumber++
        }
        return customRequest {
            api {
                httpApi.getArticleCollect(pageNumber)
            }
            requestTag {
                isRefresh
            }
        }
    }

    /**
     * 6.4.2 我的收藏页面（该页面包含自己录入的内容）
     * @param id Int
     * @param originId Int
     * @return LiveData<ResultData<String>>
     */
    suspend fun unCollectArticle(id: Int, originId: Int): LiveData<ResultData<String>> {
        return customRequest {
            api {
                httpApi.unCollectMyArticle(id, originId)
            }
        }
    }
}