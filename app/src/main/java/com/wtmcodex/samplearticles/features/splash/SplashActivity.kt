package  com.wtmcodex.samplearticles.features.splash

import android.os.Bundle
import com.wtmcodex.samplearticles.R
import com.wtmcodex.samplearticles.base.view.BaseActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(getContainerId(), SplashFragment()).commit()
    }

    override fun getContentLayout(): Int {
        return R.layout.activity_splash
    }

    override fun getContainerId(): Int {
        return R.id.content_container;
    }
}
