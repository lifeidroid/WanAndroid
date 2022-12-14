package com.lifeidroid.wanandroid.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lifeidroid.wanandroid.base.BaseViewModel
import com.lifeidroid.wanandroid.ext.clearAddAll
import com.lifeidroid.wanandroid.http.RequestStatus
import com.lifeidroid.wanandroid.model.entity.ArticleModel
import com.lifeidroid.wanandroid.model.entity.SearchModel
import com.lifeidroid.wanandroid.model.entity.net.ArticleEntity
import com.lifeidroid.wanandroid.model.entity.net.HotKeyEntity
import com.lifeidroid.wanandroid.utils.ResultDataUtils
import com.lifeidroid.wanandroid.utils.T
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/11/02
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@HiltViewModel
class SearchViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {

    @Inject
    lateinit var model: SearchModel

    @Inject
    lateinit var articleModel:ArticleModel

    var key by mutableStateOf("")
        private set

    fun updateKey(temp: String) {
        key = temp
    }

    //热词
    var hotkeyDatas = mutableStateListOf<HotKeyEntity>()

    //搜索结果
    var searchResult = mutableStateListOf<ArticleEntity.Data>()

    /**
     * 搜索
     */
    fun doSearch(isRefresh: Boolean) {
        if (key.isNullOrEmpty()) {
            T.showToast("请输入关键词")
            return
        }
        viewModelScope.launch {
            model.doSearch(key, isRefresh).observeForever {
                ResultDataUtils.dellRefreshAndLoadMoreCustom(
                    it,
                    searchResult,
                    conversion = { temp ->
                        temp.datas
                    },
                    this@SearchViewModel,
                    model
                )
            }
        }
    }

    /**
     * 获取热词
     */
    fun getHotKey() {
        viewModelScope.launch {
            model.getHotKey().observeForever {
                when (it.requestStatus) {
                    RequestStatus.SUCCESS -> {
                        hotkeyDatas.clearAddAll(it.data!!)
                    }
                }
            }
        }
    }

    /**
     * 更新文章列表收藏状态
     * @param index Int
     */
    fun updateArtcleDataSourceCollect(index: Int) {
        var collect = !searchResult[index].collect!!
        doCollect(searchResult[index].id!!,collect)
        searchResult[index] = searchResult[index].copy(
            collect = collect
        )
    }

    fun doCollect(id:Int,collect:Boolean){
        if (collect) {//收藏
            viewModelScope.launch {
                articleModel.collectArticle(id).observeForever {
                    when (it.requestStatus) {
                        RequestStatus.ERROR -> {
                            T.showToast(it.error!!.message!!)
                        }
                    }
                }
            }

        } else {//取消收藏
            viewModelScope.launch {
                articleModel.unCollectArticle(id).observeForever {
                    when (it.requestStatus) {
                        RequestStatus.ERROR -> {
                            T.showToast(it.error!!.message!!)
                        }
                    }
                }
            }
        }
    }


}