package com.lifeidroid.wanandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.lifeidroid.wanandroid.base.BaseViewModel
import com.lifeidroid.wanandroid.ext.clearAddAll
import com.lifeidroid.wanandroid.http.RequestStatus
import com.lifeidroid.wanandroid.model.entity.ArticleModel
import com.lifeidroid.wanandroid.model.entity.HomeModel
import com.lifeidroid.wanandroid.model.entity.net.ArticleEntity
import com.lifeidroid.wanandroid.model.entity.net.BannerEntity
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
class HomeViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : BaseViewModel(
    savedStateHandle
) {
    @Inject
    lateinit var homeModel: HomeModel

    @Inject
    lateinit var articleModel: ArticleModel

    var swiperData by mutableStateOf(
        listOf(
            BannerEntity(
                title = "",
                imagePath = "https://docs.bughub.icu/compose/assets/banner1.webp"
            ),
            BannerEntity(
                title = "",
                imagePath = "https://docs.bughub.icu/compose/assets/banner1.webp"
            )
        )
    )
        private set

    var articleDataSource = mutableStateListOf<ArticleEntity.Data>()
        private set

    fun loadBanner() {
        viewModelScope.launch {
            homeModel.getBanner().observeForever {
                when (it.requestStatus) {
                    RequestStatus.SUCCESS -> {
                        for (item in it.data!!) {
                            swiperData = it.data
                        }
                    }
                }
            }
        }
    }

    /**
     * 加载文章列表
     * @param isRefresh Boolean
     */
    fun loadArticle(isRefresh: Boolean) {
        if (isRefresh) {
            swipeLazyColumState.startRefresh()
        }
        viewModelScope.launch {
            homeModel.getArticle(isRefresh).observeForever() {
                ResultDataUtils.dellRefreshAndLoadMoreCustom(
                    it,
                    articleDataSource,
                    { articleEntity ->
                        articleEntity.datas
                    },
                    this@HomeViewModel,
                    homeModel
                )
            }
        }

    }

    var articleTopDataSource = mutableStateListOf<ArticleEntity.Data>()
        private set


    /**
     * 更新头条文章收藏状态
     * @param index Int
     */
    fun updateArtcleTopDataSourceCollect(index: Int) {
        var collect = !articleTopDataSource[index].collect!!
        doCollect(articleTopDataSource[index].id!!,collect)
        articleTopDataSource[index] = articleTopDataSource[index].copy(
            collect = collect
        )
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

    /**
     * 加载头条列表
     * @param isRefresh Boolean
     */
    fun loadTopArticle() {
        viewModelScope.launch {
            homeModel.getTopArticle().observeForever {
                when (it.requestStatus) {
                    RequestStatus.SUCCESS -> {
                        articleTopDataSource.clearAddAll(it.data!!)
//                        articleTopDataSource = it.data!!
                    }
                }
            }
        }

    }
}