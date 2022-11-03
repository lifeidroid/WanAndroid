package com.lifeidroid.wanandroid.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lifeidroid.wanandroid.base.BaseViewModel
import com.lifeidroid.wanandroid.ext.clearAddAll
import com.lifeidroid.wanandroid.model.entity.CollectNetModel
import com.lifeidroid.wanandroid.model.entity.net.CollectNetEntity
import com.lifeidroid.wanandroid.utils.ResultDataUtils
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
class NetCollectedViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {
    @Inject
    lateinit var collectedNetModel: CollectNetModel

    var collectedNetList = mutableStateListOf<CollectNetEntity>()

    /**
     * 获取收藏的网站列表
     */
    fun getCollectedNet() {
        viewModelScope.launch {
            collectedNetModel.getCollectedList().observeForever {
                ResultDataUtils.dellRefreshListData(it, this@NetCollectedViewModel) {
                    collectedNetList.clearAddAll(it)
                    swipeLazyColumState.finishLoadMore(true)
                }
            }
        }
    }
}