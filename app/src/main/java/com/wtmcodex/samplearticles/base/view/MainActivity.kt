package com.wtmcodex.samplearticles.base.view

import com.wtmcodex.samplearticles.R

class MainActivity : BaseActivity() {

    override fun getContentLayout(): Int {
        return R.layout.activity_main
    }

    override fun getContainerId(): Int {
        return R.id.content_container;
    }
}