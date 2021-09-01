package  com.wtmcodex.samplearticles.features.splash

import android.os.Bundle
import com.wtmcodex.samplearticles.base.viewmodel.BaseViewModel
import com.wtmcodex.samplearticles.constants.AppConstants
import com.wtmcodex.samplearticles.constants.NavigationConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel() {

    override fun loadData(params: Bundle?) {
        startDelayForSplash()
    }

    private fun startDelayForSplash() {

        GlobalScope.launch(Dispatchers.Main) {
            delay(AppConstants.SPLASH_DELAY_TIME)
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        navigator.navigate(NavigationConstants.ARTICLES)
    }
}
