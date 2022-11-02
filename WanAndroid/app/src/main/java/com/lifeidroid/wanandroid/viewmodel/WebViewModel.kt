package com.lifeidroid.wanandroid.viewmodel

import android.util.Log
import android.webkit.WebView
import androidx.compose.runtime.mutableStateMapOf
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
 *     time   : 2022/11/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@HiltViewModel
class WebViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {
    lateinit var webView: WebView

    @Inject
    lateinit var collectModel: ArticleModel

    var collectedMap = mutableStateMapOf<String, Int>()

    fun operatArticle(url: String, title: String) {
        if (collectedMap.contains(url) && collectedMap[url] != 0) {
            unCollectArticle(url, collectedMap[url]!!)
        } else {
            collectAticle(url, title)
        }
    }

    fun isCollected(url: String):Boolean{
        return collectedMap.contains(url) && collectedMap[url] != 0
    }

    private fun collectAticle(url: String, title: String) {
        viewModelScope.launch {
            collectModel.collectArticle(title, "", url).observeForever {
                when (it.requestStatus) {
                    RequestStatus.SUCCESS -> {
                        collectedMap[url] = it.data!!.id!!
                    }
                    RequestStatus.ERROR -> {
                        Log.d("===", "${it.error!!.message}")
                        T.showToast(it.error!!.message!!)
                    }
                }
            }
        }
    }

    private fun unCollectArticle(url: String, id: Int) {
        viewModelScope.launch {
            collectModel.unCollectMyArticle(id).observeForever {
                when (it.requestStatus) {
                    RequestStatus.SUCCESS -> {
                        collectedMap[url] = 0
                    }
                    RequestStatus.ERROR -> {
                        Log.d("===", "${it.error!!.message}")
                        T.showToast(it.error!!.message!!)
                    }
                }
            }
        }
    }
}