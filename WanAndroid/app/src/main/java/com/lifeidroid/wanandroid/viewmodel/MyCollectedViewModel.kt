package com.lifeidroid.wanandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lifeidroid.wanandroid.base.BaseViewModel
import com.lifeidroid.wanandroid.ext.clearAddAll
import com.lifeidroid.wanandroid.http.RequestStatus
import com.lifeidroid.wanandroid.model.entity.CollectArticleModel
import com.lifeidroid.wanandroid.model.entity.CollectNetModel
import com.lifeidroid.wanandroid.model.entity.net.CollectArticleEnity
import com.lifeidroid.wanandroid.model.entity.net.CollectNetEntity
import com.lifeidroid.wanandroid.utils.ResultDataUtils
import com.lifeidroid.wanandroid.utils.T
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
@HiltViewModel
class MyCollectedViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {

    @Inject
    lateinit var model: CollectArticleModel

    var selectedIndex by mutableStateOf(0)
        private set

    fun updateSelectedIndex(index: Int) {
        selectedIndex = index
    }

    var collectedArticleList = mutableStateListOf<CollectArticleEnity.Data>()

    /**
     * 获取收藏的网站列表
     */
    fun getCollectedArticle(isRefresh: Boolean) {
        viewModelScope.launch {
            model.getCollectedArticles(isRefresh).observeForever {
                ResultDataUtils.dellRefreshAndLoadMoreCustom(it, collectedArticleList, { temp ->
                    temp.datas
                }, this@MyCollectedViewModel, model)
            }
        }
    }

    /**
     * 取消收藏
     * @param index Int
     */
    fun unCollect(index: Int) {
        viewModelScope.launch {
            model.unCollectArticle(
                collectedArticleList[index].id!!,
                collectedArticleList[index].originId!!
            ).observeForever {
                when (it.requestStatus) {
                    RequestStatus.SUCCESS -> {
                        collectedArticleList.removeAt(index)
                    }
                    RequestStatus.ERROR -> {
                        T.showToast(it.error!!.message!!)
                    }
                }
            }
        }
    }
}