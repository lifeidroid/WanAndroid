package com.lifeidroid.wanandroid.base

/**
 * 页面常用操作
 */
interface ViewBehavior {
    /**
     * 完成刷新
     * @param boolean Boolean
     */
    fun finishRefresh()

    /**
     * 完成加载更多
     * @param boolean Boolean   是否为全部
     */
    fun finishLoadMore(isAll: Boolean)

    /**
     * 加载更多出错
     * @param error String  错误消息
     */
    fun doLoadMoreWithError(error: String)

    /**
     * 是否显示Loading视图
     */
    fun showLoadingUI(loadingMsg: String)

    /**
     * 是否显示空白视图
     */
    fun showEmptyUI(emptyMsg: String)

    /**
     * 显示错误页面
     * @param errorMsg String
     */
    fun showErrorUI(errorMsg: String)

    /**
     * 显示内容
     */
    fun showContentUI()

    /**
     * 通知页面内容发生改变
     * @param flag Boolean
     */
    fun notifyUIDataChanged(flag: Boolean)

    /**
     * 显示加载中对话框
     * @param loadingMsg String 消息内容
     * @param dismissOnBackPress Boolean    返回键是否可关闭对话框
     * @param dismissOnClickOutside Boolean    点击对话框外层是否可关闭对话框
     */
    fun showLoadingDlg(
        loadingMsg: String, dismissOnBackPress: Boolean = true,
        dismissOnClickOutside: Boolean = true
    )


    /**
     * 隐藏加载中对话框
     */
    fun hideLoadingDlg()

    /**
     * 显示单按钮提醒框
     * @param title String  标题
     * @param content String    内容
     * @param btnText String    按钮文本
     * @param dismissOnBackPress Boolean    返回键是否可关闭对话框
     * @param dismissOnClickOutside Boolean     点击对话框外层是否可关闭对话框
     */
    fun showAlertDlg(
        title: String = "", content: String = "", btnText: String = "确定", dismissOnBackPress: Boolean = true,
        dismissOnClickOutside: Boolean = true
    )

    /**
     * 关闭多按钮提醒框
     */
    fun hideAlertDlg()
    /**
     * 显示确认对话框
     * @param title String  标题
     * @param content String    内容
     * @param btnText String    按钮文本
     * @param dismissOnBackPress Boolean    返回键是否可关闭对话框
     * @param dismissOnClickOutside Boolean     点击对话框外层是否可关闭对话框
     */
    fun showConformDlg(
        title: String = "", content: String = "",btnText: List<String>, dismissOnBackPress: Boolean = true,
        dismissOnClickOutside: Boolean = true
    )

    /**
     * 关闭确认对话框
     */
    fun hideConformDlg()

//    /**
//     * 弹出Toast提示
//     */
//    fun showToast(event: ToastEvent)

    /**
     * 不带参数的页面跳转
     */
    fun navigate(page: Any)

    /**
     * 返回键点击
     */
    fun backPress(arg: Any?)

    /**
     * 关闭页面
     */
    fun finishPage(arg: Any?)
}