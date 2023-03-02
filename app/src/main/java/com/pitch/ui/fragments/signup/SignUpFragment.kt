package com.pitch.ui.fragments.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pitch.R
import com.pitch.databinding.FragmentSignUpBinding
import com.pitch.ui.base.BaseFragment
import com.pitch.ui.fragments.forgot.ForgotFragmentDirections
import com.pitch.utils.extention.hideAndShowPassword
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignupViewMode>() {
    private var isShow = false
    private var isCnShow = false
    override val viewModel: SignupViewMode by viewModels()
    override fun getLayoutRes() = R.layout.fragment_sign_up
    override fun initViewModel(viewModel: SignupViewMode) {
        binding.signUpViewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        onClickListeners()
    }

    private fun onClickListeners() {
        binding.apply {
            ltPassword.apply {
                ivClose.setOnClickListener {
                    isShow = !isShow
                    etPassword.hideAndShowPassword(ivClose, isShow)
                }
            }

            ltCnPassword.apply {
                tvPasswordTitle.text = getString(R.string.confirm_password)
                ivClose.setOnClickListener {
                    isCnShow = !isCnShow
                    etPassword.hideAndShowPassword(ivClose, isCnShow)
                }
            }

            btnSignup.setOnClickListener {
                val des = ForgotFragmentDirections.toOtp(false)
                findNavController().navigate(des)
            }
        }
    }

}