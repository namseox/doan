package com.kma.myapplication.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.kma.myapplication.MainActivity
import com.kma.myapplication.R
import com.kma.myapplication.databinding.FragmentSplashBinding
import com.kma.myapplication.ui.base.AbsBaseFragment
import com.kma.myapplication.utils.Utils.setFullScreen
import com.kma.myapplication.utils.setDefaultScreen

class SplashFragment : AbsBaseFragment<FragmentSplashBinding>() {
    override fun getLayout(): Int = R.layout.fragment_splash
    private val handler = Handler(Looper.myLooper()!!)
    private val runnable = Runnable {
        requireActivity().startActivity(Intent(requireContext(), MainActivity::class.java))
        requireActivity().finish()
    }

    override fun initView() {


        //binding.imgCircleSplash.startAnimation(anim)
    }

    override fun onStart() {

        binding.imgIconSplash.animate().apply {
            duration = 1000
            scaleX(0.5F)
            scaleY(0.5F)
            scaleXBy(1F)
            scaleYBy(1F)


        }

        binding.imgCircleSplash.animate().apply {
            duration = 1000
            scaleX(0F)
            scaleY(0F)
            scaleXBy(2F)
            scaleYBy(2F)

        }

        super.onStart()


        handler.postDelayed(runnable, 1500)
    }
    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFullScreen()
    }

    override fun onDestroy() {
        super.onDestroy()
        setDefaultScreen()
    }



}