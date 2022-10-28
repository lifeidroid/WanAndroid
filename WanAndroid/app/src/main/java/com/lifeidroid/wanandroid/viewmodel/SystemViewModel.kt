package com.lifeidroid.wanandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lifeidroid.wanandroid.base.BaseViewModel
import com.lifeidroid.wanandroid.model.entity.NavigationItem
import com.lifeidroid.wanandroid.model.entity.SystemModel
import com.lifeidroid.wanandroid.model.entity.net.NavigationEntity
import com.lifeidroid.wanandroid.model.entity.net.SystemEntity
import com.lifeidroid.wanandroid.utils.ResultDataUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@HiltViewModel
class SystemViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :BaseViewModel(savedStateHandle) {

    @Inject
    lateinit var model: SystemModel

    var selectedIndex by mutableStateOf(0)
        private set

    fun updateSelectedIndex(index:Int){
        selectedIndex = index

    }
    var datas by mutableStateOf(
        emptyList<SystemEntity>(
        )
    )
        private set


    /**
     * 2.1 体系数据
     */
    fun getSystemTree(){
        viewModelScope.launch {
            model.getSystemTree().observeForever(){
                ResultDataUtils.dellRefreshListData(it,this@SystemViewModel){ rData->
                    datas = rData
                }
            }
        }
    }

    var naviDatas by mutableStateOf(
        emptyList<NavigationEntity>(
        )
    )
        private set

    /**
     * 3.1 导航数据
     */
    fun getNaviTree(){
        viewModelScope.launch {
            model.getNaviTree().observeForever(){
                ResultDataUtils.dellRefreshListData(it,this@SystemViewModel){ rData->
                    naviDatas = rData
                }
            }
        }
    }
}