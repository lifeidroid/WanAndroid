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
 *     time   : 2022/11/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@HiltViewModel
class ShareViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {
    var title by mutableStateOf("")
        private set

    fun updateTitle(temp: String) {
        title = temp
    }

    var link by mutableStateOf("")
        private set

    fun updateLink(temp: String){
        link = temp
    }

    @Inject
    lateinit var model:ArticleModel

    fun share(){
        showLoadingDlg("正在分享..")
        viewModelScope.launch {
            model.shareArticle(title, link).observeForever {
                when(it.requestStatus){
                    RequestStatus.SUCCESS->{
                        T.showToast("分享成功")
                    }
                    RequestStatus.ERROR->{
                        T.showToast(it.error!!.message!!)
                    }
                    RequestStatus.COMPLETE->{
                        hideLoadingDlg()
                    }
                }
            }
        }
    }
}