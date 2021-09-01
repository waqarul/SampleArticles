package com.wtmcodex.samplearticles.core.interactor

import com.wtmcodex.samplearticles.R
import com.wtmcodex.samplearticles.base.Domain
import com.wtmcodex.samplearticles.base.interactor.BaseInteractor
import com.wtmcodex.samplearticles.core.model.ArticleModel
import com.wtmcodex.samplearticles.core.model.BaseDataModel
import com.wtmcodex.samplearticles.core.model.ErrorBody
import com.wtmcodex.samplearticles.extentions.getErrorMessage
import io.reactivex.Observable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ArticlesInteractor : BaseInteractor() {
    fun getArticles(period: String): Observable<BaseDataModel<List<ArticleModel>>> {
        return Observable.create { emitter ->
            GlobalScope.launch {
                try {
                    val response = apiClient.getArticlesList(
                        period,
                        Domain.applicationContext.getString(R.string.api_key)
                    )
                    if (!response.isSuccessful) {
                        val apiError: ErrorBody? =
                            response.getErrorMessage(apiClient.getRetrofitObject())
                        emitter.onError(
                            Exception(
                                apiError?.message
                                    ?: context.getString(R.string.message_error_in_request)
                            )
                        )
                        return@launch
                    }
                    emitter.onNext(response.body()!!)
                    emitter.onComplete()
                } catch (ex: Exception) {
                    emitter.onError(Exception(context.getString(R.string.message_error_in_request)))
                }
            }
        }
    }
}