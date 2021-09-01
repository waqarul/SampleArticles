package com.wtmcodex.samplearticles.base.interactor

import com.wtmcodex.samplearticles.base.Domain
import com.wtmcodex.samplearticles.core.network.IApiClient
import com.wtmcodex.samplearticles.core.network.RetrofitApiClient

abstract class BaseInteractor {
    val context = Domain.applicationContext
    protected val apiClient: IApiClient = RetrofitApiClient()
}