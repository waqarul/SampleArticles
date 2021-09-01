package com.wtmcodex.samplearticles.core.utils

import android.util.Log
import com.wtmcodex.samplearticles.constants.AppConstants

object LoggerUtils {

    fun debug(tag: String?, message: String) {
        if (AppConstants.SHOW_CONSOLE_LOGS) {
            Log.d(tag, message)
        }
    }

    fun error(tag: String?, message: String) {
        error(tag, message, null)
    }

    fun error(tag: String?, message: String, th: Throwable?) {
        if (AppConstants.SHOW_CONSOLE_LOGS) {
            Log.e(tag, message, th)
        }
    }

    fun verbose(tag: String?, message: String) {
        if (AppConstants.SHOW_CONSOLE_LOGS) {
            Log.v(tag, message)
        }
    }

}