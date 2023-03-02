package com.pitch.ui.fragments.forgot

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pitch.R
import com.pitch.databinding.FragmentForgotBinding
import com.pitch.ui.base.BaseFragment
import com.pitch.utils.extention.gone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotFragment : BaseFragment<FragmentForgotBinding, ForgotViewMode>() {
    var isShow = true
    override val viewModel: ForgotViewMode by viewModels()
    override fun getLayoutRes() = R.layout.fragment_forgot
    override fun initViewModel(viewModel: ForgotViewMode) {
        binding.forgotPasswordViewModel = viewModel
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
            /*  ivClose.setOnClickListener {
                  isShow = !isShow
                  etPassword.hideAndShowPassword(ivClose, isShow)
              }*/
            ltMobileNumber.apply {
                tvUsernameTitle.gone()
            }
            btnForgot.setOnClickListener {
                val des = ForgotFragmentDirections.toOtp(true)
                findNavController().navigate(des)
            }
        }
    }


}