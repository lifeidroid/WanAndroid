package com.lifeidroid.wanandroid.model.entity

import androidx.lifecycle.LiveData
import com.lifeidroid.wanandroid.base.BaseModel
import com.lifeidroid.wanandroid.http.ResultData
import com.lifeidroid.wanandroid.http.customRequest
import com.lifeidroid.wanandroid.model.entity.net.CollecteEntity
import javax.inject.Inject

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/27
 *     desc   : 文章操作
 *     version: 1.0
 * </pre>
 */
class ArticleModel @Inject constructor() : BaseModel() {
    /**
     * 6.2 收藏站内文章
     * @return CommonBean<String>
     */
    suspend fun collectArticle(
        id: Int
    ): LiveData<ResultData<String>> {
        return customRequest {
            api {
                httpApi.collectArticle(id)
            }
        }

    }

    /**
     * 6.3 收藏站外文章
     * @return CommonBean<String>
     */
    suspend fun collectArticle(
        title: String,
        author: String,
        link: String,
    ): LiveData<ResultData<CollecteEntity>> {
        return customRequest {
            api {
                httpApi.collectArticle(title, author, link)
            }
        }

    }

    /**
     * 6.4 取消收藏
     * 6.4.1 文章列表
     * @param id Int
     * @return CommonBean<String>
     */
    suspend fun unCollectArticle(
        id: Int
    ): LiveData<ResultData<String>> {
        return customRequest {
            api {
                httpApi.unCollectArticle(id)
            }
        }
    }
    /**
     * 6.4 取消收藏
     * 6.4.2 我的收藏页面（该页面包含自己录入的内容）
     * @param id Int
     * @return CommonBean<String>
     */
    suspend fun unCollectMyArticle(
        id: Int
    ): LiveData<ResultData<String>> {
        return customRequest {
            api {
                httpApi.unCollectMyArticle(id)
            }
        }
    }

    /**
     * 10.5 分享文章
     * @param title String
     * @param link String
     * @return LiveData<Reusable<String>>
     */
    suspend fun shareArticle(
        title: String,
        link: String
    ):LiveData<ResultData<String>>{
        return customRequest {
            api {
                httpApi.shareArticle(title,link)
            }
        }
    }

}