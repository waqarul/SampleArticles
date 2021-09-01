package com.wtmcodex.samplearticles.core.network

import com.wtmcodex.samplearticles.core.model.ArticleModel
import com.wtmcodex.samplearticles.core.model.BaseDataModel
import retrofit2.Response
import retrofit2.Retrofit

interface IApiClient {
    fun getRetrofitObject(): Retrofit
    suspend fun getArticlesList(
        period: String,
        apiKey: String
    ): Response<BaseDataModel<List<ArticleModel>>>
}