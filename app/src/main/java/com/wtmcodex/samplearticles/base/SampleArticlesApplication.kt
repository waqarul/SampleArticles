package com.wtmcodex.samplearticles.base

import android.app.Application
import com.wtmcodex.samplearticles.core.navigation.NavigationAdapter
import com.wtmcodex.samplearticles.core.navigation.Navigator

class SampleArticlesApplication : Application() {
    private val TAG = this::class.simpleName

    override fun onCreate() {
        super.onCreate()

        initialize()
    }

    private fun initialize() {

        Domain.integrateWith(this)

        initializeNavigation()
    }

    private fun initializeNavigation() {
        Navigator.initialize(this, NavigationAdapter(this))
    }
}