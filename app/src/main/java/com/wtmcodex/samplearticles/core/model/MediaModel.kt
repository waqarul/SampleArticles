package com.wtmcodex.samplearticles.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
class MediaModel(
    @Json(name = "type") var type: String? = null,
    @Json(name = "subtype") var subtype: String? = null,
    @Json(name = "caption") var caption: String? = null,
    @Json(name = "copyright") var copyright: String? = null,
    @Json(name = "approved_for_syndication") var approvedForSyndication: Long? = null,
    @Json(name = "media-metadata") var mediaMetaDataList: List<MediaMetaDataModel>? = null
) : Serializable
