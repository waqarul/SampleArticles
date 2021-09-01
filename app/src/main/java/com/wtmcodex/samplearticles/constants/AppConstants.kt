package com.wtmcodex.samplearticles.constants

import com.wtmcodex.samplearticles.R
import com.wtmcodex.samplearticles.base.Domain

class AppConstants {
    companion object {
        val SHOW_CONSOLE_LOGS = Domain.applicationContext.resources.getBoolean(R.bool.show_logs)
        const val SPLASH_DELAY_TIME: Long = 2 * 1000 // 2 sec
    }
}