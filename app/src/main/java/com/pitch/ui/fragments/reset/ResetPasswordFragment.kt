package com.pitch.ui.fragments.reset

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pitch.R
import com.pitch.databinding.FragmentResetPassswordBinding
import com.pitch.ui.base.BaseFragment
import com.pitch.utils.extention.hideAndShowPassword
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordFragment : BaseFragment<FragmentResetPassswordBinding, ResetPasswordViewMode>() {
    private var isShow = false
    private var isCnShow = false
    override val viewModel: ResetPasswordViewMode by viewModels()
    override fun getLayoutRes() = R.layout.fragment_reset_passsword
    override fun initViewModel(viewModel: ResetPasswordViewMode) {
        binding.resetPassword = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, onBackPress)
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

            btnReset.setOnClickListener {
                findNavController().popBackStack(R.id.loginFragment, false)
            }
        }
    }

    /**
     * On back pressed callback to handle
     */
    private val onBackPress = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            findNavController().popBackStack(R.id.loginFragment, false)
        }

    }
}