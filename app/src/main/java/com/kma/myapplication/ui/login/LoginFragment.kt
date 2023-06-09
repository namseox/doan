package com.kma.myapplication.ui.login

import android.app.Application
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kma.myapplication.R
import com.kma.myapplication.databinding.FragmentLoginBinding
import com.kma.myapplication.ui.base.AbsBaseFragment
import com.kma.myapplication.ui.main.MainFragmentDirections
import com.kma.myapplication.ui.splash.ConfirmFragment
import com.kma.myapplication.ui.splash.onClick
import com.kma.myapplication.utils.SharedPreferenceUtils
import com.kma.myapplication.utils.SharedViewModel

class LoginFragment : AbsBaseFragment<FragmentLoginBinding>(),onClick {
    lateinit var mViewModel: LoginViewModel
    override fun getLayout(): Int {
        return R.layout.fragment_login
    }

    override fun initView() {
        showButtonSheet()
        mViewModel = LoginViewModel(Application())

        binding.btnLogin.setOnClickListener {
            if (binding.etUsername.text.isNullOrBlank()) {
                Toast.makeText(requireContext(), "Chưa điền tài khoản", Toast.LENGTH_SHORT).show()
            } else {


                if (binding.etPassword.text.isNullOrBlank()) {
                    Toast.makeText(requireContext(), "Chưa điền mật khẩu", Toast.LENGTH_SHORT).show()
                } else {
                    mViewModel.getUser(
                        binding.etUsername.text.toString(),
                        binding.etPassword.text.toString()
                    )
                }
            }
        }
        mViewModel.user.observe(this) {
            it?.let {
                if (it.success) {
                    SharedViewModel.getInstance(requireContext()).user = it
                    SharedPreferenceUtils.getInstance(requireContext()).setObjModel(it)
                    SharedPreferenceUtils.getInstance(requireContext()).putBooleanValue("login_success",true)
                    val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Điền sai thông tin tài khoản hoặc mật khẩu",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    private fun showButtonSheet() {
        if (!SharedPreferenceUtils.getInstance(requireContext()).getAcceptPolicy()) {
            val modalBottomSheet = ConfirmFragment(this)
            modalBottomSheet.show(requireActivity().supportFragmentManager, ConfirmFragment.TAG)
        }
    }
    override fun onClickText(text: String) {
        when (text) {
            "PrivacyPolicy" -> {
                val action =
                    MainFragmentDirections.actionMainFragmentToPolicyAndTermFragment().setParam(1)
                findNavController().navigate(action)
            }
            "TermsOfService" -> {
                val action =
                    MainFragmentDirections.actionMainFragmentToPolicyAndTermFragment().setParam(2)
                findNavController().navigate(action)
            }
            else -> {
                requireActivity().finish()
            }
        }
    }
}