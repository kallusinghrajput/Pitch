package com.pitch.ui.fragments.verified

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pitch.R
import com.pitch.databinding.FragmentOtpFragmentBinding
import com.pitch.databinding.FragmentVerifiedBinding
import com.pitch.ui.activities.home.HomeActivity
import com.pitch.ui.base.BaseFragment
import com.pitch.ui.fragments.otp.OtpViewMode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifiedFragment : BaseFragment<FragmentVerifiedBinding, OtpViewMode>() {

    override val viewModel: OtpViewMode by viewModels()
    override fun getLayoutRes() = R.layout.fragment_verified
    override fun initViewModel(viewModel: OtpViewMode) {
        ///  binding.otpViewModel = viewModel
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
            btnVerified.setOnClickListener {
                startActivity(Intent(requireActivity(), HomeActivity::class.java))
                requireActivity().finish()
            }
        }
    }
}