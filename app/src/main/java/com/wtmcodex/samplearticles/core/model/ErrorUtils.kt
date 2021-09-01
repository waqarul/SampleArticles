package com.wtmcodex.samplearticles.core.model


import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException


object ErrorUtils {
    fun parseError(response: Response<*>, retrofit: Retrofit): ErrorBody? {
        val converter: Converter<ResponseBody, ErrorBody> = retrofit
            .responseBodyConverter(ErrorBody::class.java, arrayOfNulls<Annotation>(0))
        return try {
            converter.convert(response.errorBody())
        } catch (e: IOException) {
            return null
        }
    }
}