package com.wtmcodex.samplearticles.features.pricing

import androidx.lifecycle.ViewModelProvider
import com.wtmcodex.samplearticles.R
import com.wtmcodex.samplearticles.base.view.BaseFragment
import com.wtmcodex.samplearticles.core.navigation.INavigationFragmentListener

class PricingFragment : BaseFragment<PricingViewModel>(), INavigationFragmentListener {

    override fun getTitle(): String {
        return getString(R.string.pricing_label)
    }

    override fun getViewLayout(): Int {
        return R.layout.fragment_pricing
    }

    override fun loadData() {
        super.loadData()
        viewModel.loadData(arguments)
    }

    override fun getOrCreateViewModel(): PricingViewModel {
        return ViewModelProvider(this).get(PricingViewModel::class.java)
    }

    override fun setupBindings() {
        super.setupBindings()
    }

    override fun getFragmentNavigationId(): Int {
        return R.id.nav_pricing
    }
}