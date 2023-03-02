package com.pitch.ui.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pitch.R
import com.pitch.databinding.FragmentLoginBinding
import com.pitch.ui.activities.home.HomeActivity
import com.pitch.ui.base.BaseFragment
import com.pitch.utils.extention.hideAndShowPassword


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewMode>() {
    var isShow = false
    override val viewModel: LoginViewMode by viewModels()
    override fun getLayoutRes() = R.layout.fragment_login
    override fun initViewModel(viewModel: LoginViewMode) {
        binding.viewModel = viewModel
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
            btnRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
            }
            tbForgot.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_forgotFragment)
            }
            btnLogin.setOnClickListener {
                startActivity(Intent(requireActivity(), HomeActivity::class.java))
                requireActivity().finish()
            }
        }
    }


}