package com.wtmcodex.samplearticles.extentions

import com.wtmcodex.samplearticles.core.model.ErrorBody
import com.wtmcodex.samplearticles.core.model.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit


inline fun <reified T> Response<T>.getErrorMessage(retrofit: Retrofit): ErrorBody? {
    return ErrorUtils.parseError(
        this,
        retrofit
    )
}
