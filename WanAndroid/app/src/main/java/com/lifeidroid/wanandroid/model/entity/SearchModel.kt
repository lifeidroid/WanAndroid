package com.lifeidroid.wanandroid.model.entity

import androidx.lifecycle.LiveData
import com.lifeidroid.wanandroid.Constants
import com.lifeidroid.wanandroid.base.BaseModel
import com.lifeidroid.wanandroid.http.ResultData
import com.lifeidroid.wanandroid.http.customRequest
import com.lifeidroid.wanandroid.model.entity.net.ArticleEntity
import com.lifeidroid.wanandroid.model.entity.net.HotKeyEntity
import javax.inject.Inject

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/11/02
 *     desc   : 搜索页面
 *     version: 1.0
 * </pre>
 */
class SearchModel @Inject constructor() : BaseModel() {

    /**
     * 搜索热词
     * @return LiveData<ResultData<MutableList<HotKeyEntity>>>
     */
    suspend fun getHotKey(): LiveData<ResultData<MutableList<HotKeyEntity>>> {
        return customRequest {
            api {
                httpApi.getHotKey()
            }
        }
    }

    /**
     * 搜索
     * @param key String
     * @param isRefresh Boolean
     * @return LiveData<ResultData<ArticleEntity>>
     */
    suspend fun doSearch(key: String, isRefresh: Boolean): LiveData<ResultData<ArticleEntity>> {
        if (isRefresh) {
            pageNumber = Constants.PAGE_START
        } else {
            pageNumber++
        }
        return customRequest<ArticleEntity> {
            api {
                httpApi.doSearch(pageNumber, key)
            }
            requestTag {
                isRefresh
            }
        }
    }
}