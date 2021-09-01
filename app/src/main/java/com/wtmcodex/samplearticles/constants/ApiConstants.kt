package com.wtmcodex.samplearticles.constants

import com.wtmcodex.samplearticles.R
import com.wtmcodex.samplearticles.base.Domain

class ApiConstants {
    companion object {
        val context = Domain.applicationContext

        private val BASE_API_URL = context.getString(R.string.base_url)
        private val PATH = context.getString(R.string.path)

        private val SERVER_VERSION = context.getString(R.string.server_version)

        val BASE_URL = "$BASE_API_URL/$PATH/$SERVER_VERSION/"

        const val ARTICLES_VIEW = "viewed/{period}.json"

        const val READ_TIME_OUT_DELAY = 2L
        const val CONNECT_TIME_OUT_DELAY = 2L
    }
}