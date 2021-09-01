package com.wtmcodex.samplearticles.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
class MediaMetaDataModel(
    @Json(name = "url") var url: String? = null,
    @Json(name = "format") var format: String? = null,
    @Json(name = "height") var height: Long? = null,
    @Json(name = "width") var width: Long? = null
) : Serializable
