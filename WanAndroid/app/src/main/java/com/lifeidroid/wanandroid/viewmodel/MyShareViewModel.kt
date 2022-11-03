package com.lifeidroid.wanandroid.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lifeidroid.wanandroid.base.BaseViewModel
import com.lifeidroid.wanandroid.http.RequestStatus
import com.lifeidroid.wanandroid.model.entity.ArticleModel
import com.lifeidroid.wanandroid.model.entity.net.ArticleEntity
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
class MyShareViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : BaseViewModel(savedStateHandle) {

    @Inject
    lateinit var model:ArticleModel

    val datas = mutableStateListOf<ArticleEntity.Data>()

    /**
     * 搜索
     */
    fun getData(isRefresh: Boolean) {
        viewModelScope.launch {
            model.getMyShare(isRefresh).observeForever {
                ResultDataUtils.dellRefreshAndLoadMoreCustom(
                    it,
                    datas,
                    conversion = { temp ->
                        temp.shareArticles!!.datas
                    },
                    this@MyShareViewModel,
                    model
                )
            }
        }
    }

    /**
     * 更新文章列表收藏状态
     * @param index Int
     */
    fun updateArtcleDataSourceCollect(index: Int) {
        var collect = !datas[index].collect!!
        doCollect(datas[index].id!!,collect)
        datas[index] = datas[index].copy(
            collect = collect
        )
    }

    fun doCollect(id:Int,collect:Boolean){
        if (collect) {//收藏
            viewModelScope.launch {
                model.collectArticle(id).observeForever {
                    when (it.requestStatus) {
                        RequestStatus.ERROR -> {
                            T.showToast(it.error!!.message!!)
                        }
                    }
                }
            }

        } else {//取消收藏
            viewModelScope.launch {
                model.unCollectArticle(id).observeForever {
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