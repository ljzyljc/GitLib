package com.finance.forward.utils

import android.util.Log

/**
 * Created by efan on 2017/4/13.
 */

object Logger {

    //设为false关闭日志
    private val LOG_ENABLE = true

    fun i(tag: String, msg: String) {
        if (LOG_ENABLE) {
            Log.i(tag, msg)
        }
    }

    fun v(tag: String, msg: String) {
        if (LOG_ENABLE) {
            Log.v(tag, msg)
        }
    }

    fun d(tag: String, msg: String) {
        if (LOG_ENABLE) {
            Log.d(tag, msg)
        }
    }

    fun w(tag: String, msg: String) {
        if (LOG_ENABLE) {
            Log.w(tag, msg)
        }
    }

    fun e(tag: String, msg: String) {
        if (LOG_ENABLE) {
            Log.e(tag, msg)
        }
    }

}
