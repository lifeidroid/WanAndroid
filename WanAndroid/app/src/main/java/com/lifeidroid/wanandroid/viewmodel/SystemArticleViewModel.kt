package com.lifeidroid.wanandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lifeidroid.wanandroid.base.BaseViewModel
import com.lifeidroid.wanandroid.http.RequestStatus
import com.lifeidroid.wanandroid.model.entity.ArticleModel
import com.lifeidroid.wanandroid.model.entity.QuestionModel
import com.lifeidroid.wanandroid.model.entity.SystemArticleModel
import com.lifeidroid.wanandroid.model.entity.net.ArticleEntity
import com.lifeidroid.wanandroid.model.entity.net.QuestionEntity
import com.lifeidroid.wanandroid.model.entity.net.SystemEntity
import com.lifeidroid.wanandroid.utils.ResultDataUtils
import com.lifeidroid.wanandroid.utils.T
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@HiltViewModel
class SystemArticleViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : BaseViewModel(
    savedStateHandle
) {

    var id by mutableStateOf(0)
        private set

    var datas = mutableListOf<SystemEntity>()

    fun updateId(index:Int){
        id = datas[index].id!!
    }

    @Inject
    lateinit var model: SystemArticleModel

    @Inject
    lateinit var articleModel: ArticleModel

    var articleDataSource = mutableStateListOf<ArticleEntity.Data>(
    )
        private set


    /**
     * 加载文章列表
     * @param isRefresh Boolean
     */
    fun loadSystemArticle(isRefresh: Boolean) {
        if (isRefresh) {
            swipeLazyColumState.startRefresh()
        }
        viewModelScope.launch {
            model.getArticle(isRefresh,id).observeForever() {
                ResultDataUtils.dellRefreshAndLoadMoreCustom(
                    it,
                    articleDataSource,
                    { articleEntity ->
                        articleEntity.datas
                    },
                    this@SystemArticleViewModel,
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
        var collect = !articleDataSource[index].collect!!
        doCollect(articleDataSource[index].id!!,collect)
        articleDataSource[index] = articleDataSource[index].copy(
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