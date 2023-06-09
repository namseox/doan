package com.kma.myapplication

import com.kma.myapplication.databinding.ActivitySplashBinding
import com.kma.myapplication.ui.base.AbsBaseActivity


class SplashActivity: AbsBaseActivity<ActivitySplashBinding>() {
    override fun getFragmentID(): Int = R.id.navContainerViewSplash

    override fun getLayoutId(): Int = R.layout.activity_splash
    override fun init() {

    }
}