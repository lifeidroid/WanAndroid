package com.lifeidroid.wanandroid.http

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

/**
 * @date：2020/5/6
 * @description:
 */

fun <T> MediatorLiveData<ResultData<T>>.addSource(liveData: LiveData<ResultData<T>>) {
    this.addSource(liveData) {
        if (it.requestStatus == RequestStatus.COMPLETE) {
            this.removeSource(liveData)
        }
        this.value = it
    }
}
//fun MediatorLiveData<DownloadStatus>.addDSource(liveData: LiveData<DownloadStatus>) {
//    this.addSource(liveData) {
//        if (it is DownloadStatus.DownloadError || it is DownloadStatus.DownloadSuccess) {
//            this.removeSource(liveData)
//        }
//        this.value = it
//    }
//}