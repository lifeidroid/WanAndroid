package com.lifeidroid.wanandroid.utils

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.lifeidroid.wanandroid.Constants
import com.lifeidroid.wanandroid.base.BaseModel
import com.lifeidroid.wanandroid.base.ViewBehavior
import com.lifeidroid.wanandroid.ext.clearAddAll
import com.lifeidroid.wanandroid.http.RequestStatus
import com.lifeidroid.wanandroid.http.ResultData
import com.lifeidroid.wanandroid.model.entity.net.UserInfoEntity


/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/07/01
 *     desc   : 网络返回状态与页面联动工具类
 *     version: 1.0
 * </pre>
 */
object ResultDataUtils {
    /**
     * 处理下拉刷新+上拉加载更多的网络访问数据
     * @param result ResultData<MutableList<T>>
     * @param datas MutableList<T>
     * @param behavior ViewBehavior
     * @param model BaseModel
     */
    fun <T> dellRefreshAndLoadMoreListData(
        result: ResultData<MutableList<T>>,
        datas: List<T>,
        behavior: ViewBehavior,
        model: BaseModel,
        back: (List<T>) -> Unit
    ) {
        when (result.requestStatus) {
            RequestStatus.ERROR -> {
                if ((result.tag as Boolean)) {//刷新失败
                    behavior.finishRefresh()
                    behavior.showErrorUI(result.error!!.message!!)
                } else {//加载更多失败
                    model.correctPageNumber()
                    behavior.doLoadMoreWithError(result.error!!.message!!)
//FIXME                    behavior.showToast(ToastEvent(result.error!!.message))
                }
            }
            RequestStatus.SUCCESS -> {
                var temp = mutableListOf<T>()
                if ((result.tag as Boolean)) {//刷新
                    if (result.data.isNullOrEmpty()) {
                        behavior.showEmptyUI("")
                    } else {
                        temp.addAll(result.data!!)
                        behavior.showContentUI()
                    }
                    if (result.data!!.size < Constants.PAGE_SIZE){
                        behavior.finishLoadMore(true)
                    }
                    behavior.finishRefresh()
                } else {//加载更多
                    temp.addAll(datas)
                    if (result.data.isNullOrEmpty()) {
                        model.correctPageNumber()
                        behavior.finishLoadMore(true)
                    } else {
                        temp.addAll(result.data!!)
                        if (result.data!!.size < Constants.PAGE_SIZE){
                            behavior.finishLoadMore(true)
                        }else{
                            behavior.finishLoadMore(false)
                        }

                    }
                }
                back(temp)
            }
        }
    }

//    /**
//     * 处理下拉刷新+上拉加载更多的网络访问数据
//     * @param result ResultData<MutableList<T>>
//     * @param datas MutableList<T>
//     * @param behavior ViewBehavior
//     * @param model BaseModel
//     */
//    fun <T, D> dellRefreshAndLoadMoreCustom(
//        result: ResultData<D>,
//        datas: List<T>,
//        conversion: (D) -> List<T>?,
//        behavior: ViewBehavior,
//        model: BaseModel,
//        back: (List<T>) -> Unit
//    ) {
//        when (result.requestStatus) {
//            RequestStatus.ERROR -> {
//                if ((result.tag as Boolean)) {//刷新失败
//                    behavior.finishRefresh()
//                    behavior.showErrorUI(result.error!!.message!!)
//                } else {//加载更多失败
//                    model.correctPageNumber()
//                    behavior.doLoadMoreWithError(result.error!!.message!!)
////FIXME             behavior.showToast(ToastEvent(result.error!!.message))
//                }
//            }
//            RequestStatus.SUCCESS -> {
//                var temp = mutableListOf<T>()
//                var resultData = conversion(result.data!!)
//                if ((result.tag as Boolean)) {//刷新
//                    if (resultData.isNullOrEmpty()) {
//                        behavior.showEmptyUI("")
//                    } else {
//                        temp.addAll(resultData)
//                        behavior.showContentUI()
//                    }
//                    if (resultData!!.size < Constants.PAGE_SIZE){
//                        behavior.finishLoadMore(true)
//                    }
//                    behavior.finishRefresh()
//                } else {//加载更多
//                    temp.addAll(datas)
//                    if (resultData.isNullOrEmpty()) {
//                        model.correctPageNumber()
//                        behavior.finishLoadMore(true)
//                    } else {
//                        temp.addAll(resultData)
//                        if (resultData!!.size < Constants.PAGE_SIZE){
//                            behavior.finishLoadMore(true)
//                        }else{
//                            behavior.finishLoadMore(false)
//                        }
//
//                    }
//                }
//                back(temp)
//            }
//        }
//    }

    /**
     * 处理下拉刷新+上拉加载更多的网络访问数据
     * @param result ResultData<MutableList<T>>
     * @param datas MutableList<T>
     * @param behavior ViewBehavior
     * @param model BaseModel
     */
    fun <T, D> dellRefreshAndLoadMoreCustom(
        result: ResultData<D>,
        datas: SnapshotStateList<T>,
        conversion: (D) -> List<T>?,
        behavior: ViewBehavior,
        model: BaseModel
    ) {
        when (result.requestStatus) {
            RequestStatus.ERROR -> {
                if ((result.tag as Boolean)) {//刷新失败
                    behavior.finishRefresh()
                    behavior.showErrorUI(result.error!!.message!!)
                } else {//加载更多失败
                    model.correctPageNumber()
                    behavior.doLoadMoreWithError(result.error!!.message!!)
//FIXME             behavior.showToast(ToastEvent(result.error!!.message))
                }
            }
            RequestStatus.SUCCESS -> {
                var resultData = conversion(result.data!!)
                if ((result.tag as Boolean)) {//刷新
                    if (resultData.isNullOrEmpty()) {
                        datas.clear()
                        behavior.showEmptyUI("")
                    } else {
                        datas.clearAddAll(resultData)
                        behavior.showContentUI()
                    }
                    if (resultData!!.size < Constants.PAGE_SIZE){
                        behavior.finishLoadMore(true)
                    }
                    behavior.finishRefresh()
                } else {//加载更多
                    if (resultData.isNullOrEmpty()) {
                        model.correctPageNumber()
                        behavior.finishLoadMore(true)
                    } else {
                        datas.addAll(resultData)
                        if (resultData!!.size < Constants.PAGE_SIZE){
                            behavior.finishLoadMore(true)
                        }else{
                            behavior.finishLoadMore(false)
                        }

                    }
                }
            }
        }
    }

    /**
     *  仅包含下拉刷新的网络访问处理
     * @param result ResultData<MutableList<T>>
     * @param datas MutableList<T>
     * @param behavior ViewBehavior
     */
    fun <T> dellRefreshListData(
        result: ResultData<MutableList<T>>,
        behavior: ViewBehavior,
        back: (List<T>) -> Unit
    ) {
        when (result.requestStatus) {
            RequestStatus.ERROR -> {
                behavior.finishRefresh()
                behavior.showErrorUI(result.error!!.message!!)
            }
            RequestStatus.SUCCESS -> {
                var temp = mutableListOf<T>()
                if (result.data.isNullOrEmpty()) {
                    behavior.showEmptyUI("")
                } else {
                    temp.addAll(result.data!!)
                    behavior.showContentUI()
                }
                back(temp)
                behavior.finishRefresh()
            }
            RequestStatus.COMPLETE -> {
                behavior.notifyUIDataChanged(true)
            }
        }
    }

    /**
     * 处理加载实体类的网络访问数据
     * @param result ResultData<MutableList<T>>
     * @param datas MutableList<T>
     * @param behavior ViewBehavior
     */
    fun <T> dellObjectData(
        result: ResultData<T>,
        behavior: ViewBehavior,
        block: ((T) -> Unit)?
    ) {
        when (result.requestStatus) {
            RequestStatus.ERROR -> {
                behavior.finishRefresh()
                behavior.showErrorUI(result.error!!.message!!)
            }
            RequestStatus.SUCCESS -> {
                block?.invoke(result.data!!)
                behavior.showContentUI()
                behavior.finishRefresh()
            }
            RequestStatus.COMPLETE -> {
                behavior.notifyUIDataChanged(true)
            }
        }
    }
}