package com.wtmcodex.samplearticles.core.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.wtmcodex.samplearticles.base.view.MainActivity
import com.wtmcodex.samplearticles.base.view.MainDrawerActivity
import com.wtmcodex.samplearticles.constants.NavigationConstants
import com.wtmcodex.samplearticles.features.articles.detail.ArticlesDetailFragment
import com.wtmcodex.samplearticles.features.articles.master.ArticlesFragment
import com.wtmcodex.samplearticles.features.places.PlacesFragment
import com.wtmcodex.samplearticles.features.pricing.PricingFragment

class NavigationAdapter(var context: Context) : INavigationAdapter {
    override fun getDestination(destination: String): NavDestination? {
        return when (destination) {
            NavigationConstants.ARTICLES -> buildArticlesDestination()
            NavigationConstants.PRICING -> buildPricingDestination()
            NavigationConstants.PLACES -> buildPlacesDestination()
            NavigationConstants.ARTICLES_DETAIL -> buildMarketPlaceDetailDestination()
            else -> null
        }
    }

    private fun buildArticlesDestination(): NavDestination {
        val params = Bundle()
        params.putString(
            NavDestination.KEY_ACTIVITY_PARAMS_FRAGMENT_ORDER,
            NavDestination.VALUE_ACTIVITY_PARAMS_ROOT_FRAGMENT
        )
        return NavDestination.Builder()
            .setNavigationType(NavigationType.NEW_ACTIVITY)
            .setActivityFlags(Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK))
            .setActivityClass(MainDrawerActivity::class.java)
            .setActivityParams(params)
            .setFragmentClass(ArticlesFragment::class.java)
            .setSeamlessAnimation(false)
            .build()
    }

    private fun buildPricingDestination(): NavDestination {
        val params = Bundle()
        params.putString(
            NavDestination.KEY_ACTIVITY_PARAMS_FRAGMENT_ORDER,
            NavDestination.VALUE_ACTIVITY_PARAMS_ADD_FRAGMENT
        )
        return NavDestination.Builder()
            .setNavigationType(NavigationType.CURRENT_ACTIVITY)
            .setActivityClass(MainDrawerActivity::class.java)
            .setActivityParams(params)
            .setFragmentClass(PricingFragment::class.java)
            .build()
    }

    private fun buildPlacesDestination(): NavDestination {
        val params = Bundle()
        params.putString(
            NavDestination.KEY_ACTIVITY_PARAMS_FRAGMENT_ORDER,
            NavDestination.VALUE_ACTIVITY_PARAMS_ADD_FRAGMENT
        )
        return NavDestination.Builder()
            .setNavigationType(NavigationType.CURRENT_ACTIVITY)
            .setActivityClass(MainDrawerActivity::class.java)
            .setActivityParams(params)
            .setFragmentClass(PlacesFragment::class.java)
            .build()
    }

    private fun buildMarketPlaceDetailDestination(): NavDestination {
        val params = Bundle()
        params.putString(
            NavDestination.KEY_ACTIVITY_PARAMS_FRAGMENT_ORDER,
            NavDestination.VALUE_ACTIVITY_PARAMS_ADD_FRAGMENT
        )
        return NavDestination.Builder()
            .setNavigationType(NavigationType.NEW_ACTIVITY)
            .setActivityClass(MainActivity::class.java)
            .setActivityParams(params)
            .setFragmentClass(ArticlesDetailFragment::class.java)
            .build()
    }
}