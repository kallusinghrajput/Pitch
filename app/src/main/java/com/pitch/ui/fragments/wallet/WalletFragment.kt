package com.pitch.ui.fragments.wallet

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pitch.R
import com.pitch.databinding.FragmentAddBinding
import com.pitch.databinding.FragmentWalletBinding
import com.pitch.ui.activities.home.HomeActivity
import com.pitch.ui.base.BaseFragment
import com.pitch.ui.fragments.add.AddViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletFragment : BaseFragment<FragmentWalletBinding, WalletViewModel>() {

    override val viewModel: WalletViewModel by viewModels()

    override fun getLayoutRes() = R.layout.fragment_wallet
    override fun initViewModel(viewModel: WalletViewModel) {
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

            ivBack.setOnClickListener {
                requireActivity().onBackPressed()
            }

            btnDepositProceed.setOnClickListener {

            }
            btnWithProceed.setOnClickListener {

            }

            moneyDeposit.setOnClickListener {


            }

            moneyWithdrabel.setOnClickListener {

            }
            transcationHistory.setOnClickListener {

            }
        }
    }
}