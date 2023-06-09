package com.kma.myapplication


import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.kma.myapplication.databinding.ActivityMainBinding
import com.kma.myapplication.ui.base.AbsBaseActivity
import com.kma.myapplication.utils.SharedViewModel
import androidx.fragment.app.activityViewModels

class MainActivity : AbsBaseActivity<ActivityMainBinding>() {

    override fun getFragmentID(): Int {
        return R.id.navContainerViewMain
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init() {


    }


}