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
class HomeModel @Inject constructor() :BaseModel(){

    /**
     * 1.2 首页banner
     */
    suspend fun getBanner(): LiveData<ResultData<MutableList<BannerEntity>>> {
        return customRequest<MutableList<BannerEntity>> {
            api {
                httpApi.getBanner()
            }
        }
    }
    /**
     * 1.5 置顶文章
     */
    suspend fun getTopArticle(): LiveData<ResultData<MutableList<ArticleEntity.Data>>> {
        return customRequest<MutableList<ArticleEntity.Data>> {
            api {
                httpApi.getTopArticles()
            }
        }
    }
    /**
     * 1.1 首页文章列表
     */
    suspend fun getArticle(isRefresh:Boolean): LiveData<ResultData<ArticleEntity>> {
        if (isRefresh) {
            pageNumber = Constants.PAGE_START
        } else {
            pageNumber++
        }
        return customRequest<ArticleEntity> {
            api {
                httpApi.getArticles(pageNumber)
            }
            requestTag {
                isRefresh
            }
        }
    }
}