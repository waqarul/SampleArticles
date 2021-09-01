package com.wtmcodex.samplearticles.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class BaseDataModel<T>(
    @Json(name = "status") var status: String? = null,
    @Json(name = "copyright") var copyright: String? = null,
    @Json(name = "num_results") var numResults: Int? = null,
    @Json(name = "results") var results: T? = null,
)

@JsonClass(generateAdapter = true)
class ErrorBody(
    @Json(name = "message") var message: String? = null,
    @Json(name = "status") var status: String? = null,
    @Json(name = "code") var code: Int? = null
) {
}