package com.wtmcodex.samplearticles.extentions

import com.squareup.moshi.Moshi

inline fun <reified T> T.toJson(): String {
    return Moshi.Builder().build().adapter<T>(T::class.java).toJson(this)
}

inline fun <reified T> Any.getObjectFromJson(json: String, c: Class<T>): T? {
    return Moshi.Builder().build().adapter<T>(T::class.java).fromJson(json)
}