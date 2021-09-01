package  com.wtmcodex.samplearticles.core.navigation

interface INavigationAdapter {
    fun getDestination(destination: String): NavDestination?
}