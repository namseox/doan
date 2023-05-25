package com.kma.myapplication.ui.splash

import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kma.myapplication.databinding.FragmentConfirmBinding
import com.kma.myapplication.utils.SharedPreferenceUtils

class ConfirmFragment(var onClick: onClick) : BottomSheetDialogFragment() {
    lateinit var binding: FragmentConfirmBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (dialog as BottomSheetDialog).behavior.state =
            BottomSheetBehavior.STATE_EXPANDED
        binding = FragmentConfirmBinding.inflate(layoutInflater, container, false)
        binding.tvPrivacyPolicy.setOnClickListener {
            onClick.onClickText("PrivacyPolicy")
            destroy()
        }
        binding.tvTermsOfService.setOnClickListener {
            onClick.onClickText("TermsOfService")
            destroy()
        }
        binding.btnStart.setOnClickListener {
            SharedPreferenceUtils.getInstance(requireActivity()).putAcceptPolicy(true)
            destroy()
        }

        return binding.root
    }

    private fun destroy() {
        Handler(Looper.getMainLooper()).postDelayed({ dismiss() }, 200)

    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        if (!SharedPreferenceUtils.getInstance(requireContext()).getAcceptPolicy()) {
            onClick.onClickText("Cancal")
        }
    }

}

interface onClick {
    fun onClickText(text: String)
}