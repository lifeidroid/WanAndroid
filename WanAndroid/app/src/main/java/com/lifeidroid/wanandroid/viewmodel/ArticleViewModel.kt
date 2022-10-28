package com.lifeidroid.wanandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lifeidroid.wanandroid.base.BaseViewModel
import com.lifeidroid.wanandroid.http.RequestStatus
import com.lifeidroid.wanandroid.model.entity.ArticleModel
import com.lifeidroid.wanandroid.utils.T
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
@HiltViewModel
class ArticleViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {
    @Inject
    lateinit var model: ArticleModel

    var collectedId by mutableStateOf(
        0
    )
        private set

    /**
     * 6.2 收藏站内文章
     * @return CommonBean<String>
     */
    fun collectArticle(
        id: Int
    ) {
        viewModelScope.launch {
            model.collectArticle(id).observeForever {
                when (it.requestStatus) {
                    RequestStatus.SUCCESS -> {
                        collectedId = id
                    }
                    RequestStatus.ERROR -> {
                        T.showToast(it.error!!.message!!)
                    }
                }
            }
        }
    }

    /**
     * 6.3 收藏站外文章
     * @return CommonBean<String>
     */
    fun collectArticle(
        title: String,
        author: String,
        link: String,
    ) {
        viewModelScope.launch {
            model.collectArticle(title, author, link).observeForever {
                when (it.requestStatus) {
                    RequestStatus.SUCCESS -> {
                        collectedId = 0
                    }
                    RequestStatus.ERROR -> {
                        T.showToast(it.error!!.message!!)
                    }
                }
            }
        }

    }

    /**
     * 6.4 取消收藏
     * 6.4.1 文章列表
     * @param id Int
     * @return CommonBean<String>
     */
    fun unCollectArticle(
        id: Int
    ) {
        viewModelScope.launch {
            model.unCollectArticle(id).observeForever {
                when (it.requestStatus) {
                    RequestStatus.SUCCESS -> {
                        collectedId = id
                    }
                    RequestStatus.ERROR -> {
                        T.showToast(it.error!!.message!!)
                    }
                }
            }
        }
    }
}