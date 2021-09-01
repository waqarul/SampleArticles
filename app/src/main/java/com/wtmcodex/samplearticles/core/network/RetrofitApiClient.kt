package com.wtmcodex.samplearticles.core.network

import com.wtmcodex.samplearticles.BuildConfig
import com.wtmcodex.samplearticles.constants.ApiConstants
import com.wtmcodex.samplearticles.core.model.ArticleModel
import com.wtmcodex.samplearticles.core.model.BaseDataModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitApiClient : IApiClient {
    private val retrofit by lazy { getRetrofit(ApiConstants.BASE_URL) }

    private val articlesService by lazy {
        retrofit.create(
            ArticlesService::class.java
        )
    }

    override fun getRetrofitObject(): Retrofit = retrofit

    override suspend fun getArticlesList(
        period: String,
        apiKey: String
    ): Response<BaseDataModel<List<ArticleModel>>> {
        return articlesService.getArticlesList(period, apiKey)
    }

    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(getHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun getHttpClient(): OkHttpClient {

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient().newBuilder()
                .readTimeout(ApiConstants.READ_TIME_OUT_DELAY, TimeUnit.SECONDS)
                .connectTimeout(ApiConstants.CONNECT_TIME_OUT_DELAY, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()
        }

        return OkHttpClient().newBuilder()
            .readTimeout(ApiConstants.READ_TIME_OUT_DELAY, TimeUnit.SECONDS)
            .connectTimeout(ApiConstants.CONNECT_TIME_OUT_DELAY, TimeUnit.SECONDS)
            .addInterceptor { chain -> chain.proceed(chain.request()) }
            .build()
    }
}