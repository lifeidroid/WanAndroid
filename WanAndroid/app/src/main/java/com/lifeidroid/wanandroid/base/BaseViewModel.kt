package com.lifeidroid.wanandroid.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.lifeidroid.wanandroid.ui.components.StatefulContentState
import com.lifeidroid.wanandroid.ui.components.StatefulStateBean
import com.lifeidroid.wanandroid.ui.components.SwipeLazyColumState
import com.lifeidroid.wanandroid.ui.components.dialog.AlertDlgData
import com.lifeidroid.wanandroid.ui.components.dialog.ConformDlgData
import com.lifeidroid.wanandroid.ui.components.dialog.LoadingDlgData

open class BaseViewModel(open var savedStateHandle: SavedStateHandle) : ViewModel(),
    ViewBehavior {

    /**
     * 下拉刷新上拉加载状态管理
     */
    val swipeLazyColumState by mutableStateOf(
        SwipeLazyColumState()
    )

    /**
     * 多状态控件状态管理
     */
    var statefullState by mutableStateOf(
        StatefulStateBean(StatefulContentState.loading)
    )
        private set

    /**
     * 加载中状态管理
     */
    var loadingDlgState by mutableStateOf(
        LoadingDlgData(showState = false)
    )

    /**
     * 单按钮提示框状态管理
     */
    var alertDlgState by mutableStateOf(
        AlertDlgData(showState = false)
    )

    /**
     * 确认对话框状态管理
     */
    var conformDlgState by mutableStateOf(
        ConformDlgData(showState = false)
    )

    // 数据内容视图显示Event
    var notifyUIDataEvent = MutableLiveData<Boolean>()
        private set

//    // 吐司显示Event
//    var showToastEvent = MutableLiveData<ToastEvent>()
//        private set

    // 不带参数的页面跳转Event
    var pageNavigationEvent = MutableLiveData<Any>()
        private set

    // 点击系统返回键Event
    var backPressEvent = MutableLiveData<Any?>()
        private set

    // 关闭页面Event
    var finishPageEvent = MutableLiveData<Any?>()
        private set

    /**
     * 完成刷新
     * @param flag Boolean
     */
    override fun finishRefresh() {
        swipeLazyColumState.finishRefresh()
    }

    /**
     * 完成加载更多
     * @param isAll Boolean
     */
    override fun finishLoadMore(isAll: Boolean) {
        swipeLazyColumState.finishLoadMore(isAll)
    }

    /**
     * 加载更多出错
     * @param error String  错误消息
     */
    override fun doLoadMoreWithError(error: String) {
        swipeLazyColumState.doLoadMoreWithError()
    }


    /**
     * 是否显示Loading视图
     */
    override fun showLoadingUI(loadingMsg: String) {
        statefullState = StatefulStateBean(StatefulContentState.loading, loadingMsg)
    }

    /**
     * 是否显示空白视图
     */
    override fun showEmptyUI(emptyMsg: String) {
        statefullState = StatefulStateBean(StatefulContentState.empty, emptyMsg)
    }

    /**
     * 显示错误页面
     * @param errorMsg String
     */
    override fun showErrorUI(errorMsg: String) {
        statefullState = StatefulStateBean(StatefulContentState.error, errorMsg)
    }

    /**
     * 显示内容
     */
    override fun showContentUI() {
        statefullState = StatefulStateBean(StatefulContentState.content)
    }

    /**
     * 通知页面内容发生改变
     * @param flag Boolean
     */
    override fun notifyUIDataChanged(flag: Boolean) {
        notifyUIDataEvent.postValue(flag)
    }

    /**
     * 显示加载中对话框
     */
    override fun showLoadingDlg(
        loadingMsg: String,
        dismissOnBackPress: Boolean,
        dismissOnClickOutside: Boolean
    ) {
        loadingDlgState =
            LoadingDlgData(loadingMsg, true, dismissOnBackPress, dismissOnClickOutside)
    }

    /**
     * 隐藏加载中对话框
     */
    override fun hideLoadingDlg() {
        loadingDlgState = LoadingDlgData(showState = false)
    }

    /**
     * 显示单按钮提醒对话框
     * @param title String  标题
     * @param content String    内容
     * @param btnText String    按钮文本
     * @param dismissOnBackPress Boolean    返回键是否可关闭对话框
     * @param dismissOnClickOutside Boolean     点击对话框外层是否可关闭对话框
     */
    override fun showAlertDlg(
        title: String,
        content: String,
        btnText: String,
        dismissOnBackPress: Boolean,
        dismissOnClickOutside: Boolean
    ) {
        alertDlgState = AlertDlgData(
            title,
            content,
            btnText,
            showState = true,
            dismissOnBackPress = dismissOnBackPress,
            dismissOnClickOutside = dismissOnClickOutside
        )
    }

    /**
     * 关闭单按钮提醒对话框
     */
    override fun hideAlertDlg() {
        alertDlgState = AlertDlgData(showState = false)
    }

    /**
     * 显示确认对话框
     * @param title String  标题
     * @param content String    内容
     * @param btnText String    按钮文本
     * @param dismissOnBackPress Boolean    返回键是否可关闭对话框
     * @param dismissOnClickOutside Boolean     点击对话框外层是否可关闭对话框
     */
    override fun showConformDlg(
        title: String,
        content: String,
        btnText: List<String>,
        dismissOnBackPress: Boolean,
        dismissOnClickOutside: Boolean
    ) {
        conformDlgState = ConformDlgData(
            title,
            content,
            btnText = btnText,
            showState = true,
            dismissOnBackPress = dismissOnBackPress,
            dismissOnClickOutside = dismissOnClickOutside
        )
    }

    /**
     * 关闭确认对话框
     */
    override fun hideConformDlg() {
        conformDlgState = ConformDlgData(showState = false)
    }


//    /**
//     * 弹出Toast提示
//     */
//    override fun showToast(event: ToastEvent) {
//        showToastEvent.postValue(event)
//    }

    /**
     * 不带参数的页面跳转
     */
    override fun navigate(page: Any) {
        pageNavigationEvent.postValue(page)
    }

    /**
     * 返回键点击
     */
    override fun backPress(arg: Any?) {
        backPressEvent.postValue(arg)
    }

    /**
     * 关闭页面
     */
    override fun finishPage(arg: Any?) {
        finishPageEvent.postValue(arg)
    }


}