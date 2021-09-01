package com.wtmcodex.samplearticles.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
class ArticleModel(
    @Json(name = "id") var id: Long,
    @Json(name = "asset_id") var assetId: Long,
    @Json(name = "uri") var uri: String?,
    @Json(name = "url") var url: String?,
    @Json(name = "source") var source: String?,
    @Json(name = "published_date") var publishedDate: String?,
    @Json(name = "updated") var updated: String?,
    @Json(name = "section") var section: String?,
    @Json(name = "subsection") var subSection: String?,
    @Json(name = "nytdsection") var nytdSection: String?,
    @Json(name = "adx_keywords") var adxKeywords: String?,
    @Json(name = "column") var column: Any?,
    @Json(name = "byline") var byline: String?,
    @Json(name = "type") var type: String?,
    @Json(name = "title") var title: String?,
    @Json(name = "abstract") var abstract: String?,
    @Json(name = "des_facet") var desFacetList: List<String>?,
    @Json(name = "org_facet") var orgFacetList: List<String>?,
    @Json(name = "per_facet") var perFacetList: List<String>?,
    @Json(name = "geo_facet") var geoFacetList: List<String>?,
    @Json(name = "media") var mediaList: List<MediaModel>?,
    @Json(name = "eta_id") var etaId: Long? = null
) : Serializable