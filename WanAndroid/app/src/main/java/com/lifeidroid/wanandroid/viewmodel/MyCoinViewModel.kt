package com.lifeidroid.wanandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lifeidroid.wanandroid.base.BaseViewModel
import com.lifeidroid.wanandroid.model.entity.MyCoinModel
import com.lifeidroid.wanandroid.model.entity.net.MyCoinDetailEntity
import com.lifeidroid.wanandroid.model.entity.net.MyCoinHistoryEntity
import com.lifeidroid.wanandroid.utils.ResultDataUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@HiltViewModel
class MyCoinViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {

    @Inject
    lateinit var model: MyCoinModel


    var myCoinDetail by mutableStateOf(MyCoinDetailEntity())
        private set

    var myCoinHistoryData = mutableStateListOf<MyCoinHistoryEntity.Data>()

    /**
     * 获取积分详情
     */
    fun getMyCoinDetail() {
        viewModelScope.launch {
            model.getMyCoinDetail().observeForever {
                ResultDataUtils.dellObjectData(it, this@MyCoinViewModel) { temp ->
                    myCoinDetail = temp
                }
            }
            model.getMyCoinHistory(true).observeForever {

            }
        }
    }

    /**
     * 获取积分历史
     * @param refresh Boolean
     */
    fun getMyCoinHistory(refresh:Boolean){
        viewModelScope.launch {
            model.getMyCoinHistory(refresh).observeForever {
                ResultDataUtils.dellRefreshAndLoadMoreCustom(it,myCoinHistoryData, conversion = {data->
                    data.datas
                },this@MyCoinViewModel,model)
            }
        }
    }
}