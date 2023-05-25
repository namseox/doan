package com.hola360.m3uplayer.ui.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.kma.myapplication.ui.base.AbsBaseFragment

abstract class BaseWithVMFragment<V : ViewDataBinding, T: ViewModel> : AbsBaseFragment<V>() {
    protected lateinit var mViewModel: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    abstract fun initViewModel()
}