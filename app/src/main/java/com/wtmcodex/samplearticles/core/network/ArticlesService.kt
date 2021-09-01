package com.wtmcodex.samplearticles.core.network

import com.wtmcodex.samplearticles.constants.ApiConstants
import com.wtmcodex.samplearticles.core.model.ArticleModel
import com.wtmcodex.samplearticles.core.model.BaseDataModel
import retrofit2.Response
import retrofit2.http.*


interface ArticlesService {
    @GET(ApiConstants.ARTICLES_VIEW)
    suspend fun getArticlesList(
        @Path(value = "period", encoded = true) period: String,
        @Query("api-key") apiKey: String
    ): Response<BaseDataModel<List<ArticleModel>>>
}