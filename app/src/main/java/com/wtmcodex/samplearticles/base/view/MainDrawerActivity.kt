package com.wtmcodex.samplearticles.base.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import butterknife.BindView
import com.google.android.material.navigation.NavigationView
import com.wtmcodex.samplearticles.BuildConfig
import com.wtmcodex.samplearticles.R
import com.wtmcodex.samplearticles.constants.NavigationConstants
import com.wtmcodex.samplearticles.core.navigation.INavigationFragmentListener
import com.wtmcodex.samplearticles.core.navigation.Navigator
import kotlin.system.exitProcess


class MainDrawerActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.drawer_layout)
    lateinit var drawerLayout: DrawerLayout

    @BindView(R.id.nav_view)
    lateinit var navigationView: NavigationView

    var tvUserName: TextView? = null

    @BindView(R.id.tv_app_version)
    lateinit var tvAppVersion: TextView

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            if (getCurrentFragment() == null || supportFragmentManager.backStackEntryCount == 0) {
                Handler(Looper.getMainLooper()).postDelayed({ exitProcess(0) }, 500)
                super.onBackPressed()
                return
            }
            super.onBackPressed()
            updateDrawerSelectedItem()
        }
    }

    private fun updateDrawerSelectedItem() {
        val fragment = getCurrentFragment()
        if (fragment is INavigationFragmentListener) {
            navigationView.setCheckedItem(fragment.getFragmentNavigationId())
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.setCheckedItem(R.id.nav_articles)
        tvAppVersion.text = "App Version: ${BuildConfig.VERSION_NAME}(${BuildConfig.VERSION_CODE})"

        val hView: View = navigationView.getHeaderView(0)
        tvUserName = hView.findViewById(R.id.tv_user_name)

        val navigator = Navigator()
        if (navigator.hasNext()) {
            navigator.next()
        }
    }

    override fun getContentLayout(): Int {
        return R.layout.activity_main_drawer
    }

    override fun getContainerId(): Int {
        return R.id.fragmentContainer
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_articles -> {
                showArticles()
            }
            R.id.nav_pricing -> {
                showPricing()
            }
            R.id.nav_places -> {
                showPlaces()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showArticles() {
        Navigator().navigateToSameActivity(NavigationConstants.ARTICLES)
    }

    private fun showPricing() {
        Navigator().navigateToSameActivity(NavigationConstants.PRICING)
    }

    private fun showPlaces() {
        Navigator().navigateToSameActivity(NavigationConstants.PLACES)
    }
}
