package  com.wtmcodex.samplearticles.features.splash

import androidx.lifecycle.ViewModelProvider
import com.wtmcodex.samplearticles.R
import com.wtmcodex.samplearticles.base.view.BaseFragment

class SplashFragment : BaseFragment<SplashViewModel>() {

    override fun getViewLayout(): Int {
        return R.layout.fragment_splash
    }

    override fun getOrCreateViewModel(): SplashViewModel {
        return ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadData(null)
    }
}