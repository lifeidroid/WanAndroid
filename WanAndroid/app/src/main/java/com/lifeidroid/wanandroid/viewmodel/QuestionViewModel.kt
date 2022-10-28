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
import com.lifeidroid.wanandroid.model.entity.net.QuestionEntity
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
class QuestionViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : BaseViewModel(
    savedStateHandle
) {
    @Inject
    lateinit var questionModel: QuestionModel
    @Inject
    lateinit var articleModel: ArticleModel


    var questionDataSource =  mutableStateListOf<QuestionEntity.Data>()
        private set


    /**
     * 更新文章列表收藏状态
     * @param index Int
     */
    fun updateQuestionDataSourceCollect(index: Int) {
        var collect = !questionDataSource[index].collect!!
        doCollect(questionDataSource[index].id!!,collect)
        questionDataSource[index] = questionDataSource[index].copy(
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
     * 加载文章列表
     * @param isRefresh Boolean
     */
    fun loadQuestion(isRefresh: Boolean) {
        if (isRefresh) {
            swipeLazyColumState.startRefresh()
        }
        viewModelScope.launch {
            questionModel.getWenDa(isRefresh).observeForever() {
                ResultDataUtils.dellRefreshAndLoadMoreCustom(
                    it,
                    questionDataSource,
                    { articleEntity ->
                        articleEntity.datas
                    },
                    this@QuestionViewModel,
                    questionModel
                )
            }
        }
    }

}