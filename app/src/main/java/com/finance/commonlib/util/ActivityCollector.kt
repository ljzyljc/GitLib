package com.finance.forward.utils

import android.app.Activity

import java.util.ArrayList

/**
 * Activity管理类
 */

object ActivityCollector {
    private val activityList = ArrayList<Activity>()
    val topActivity: Activity?
        get() = if (activityList.isEmpty()) {
            null
        } else {
            activityList[activityList.size - 1]
        }

    fun addActivity(activity: Activity) {
        activityList.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activityList.remove(activity)
    }

    //销毁所有Activity
    fun finishAllActivity() {
        for (activity in activityList) {
            activity?.finish()
        }
        activityList.clear()
    }


}
