package com.pitch.ui.fragments.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pitch.R
import com.pitch.data.model.HomeModel
import com.pitch.databinding.FragmentHomeBinding
import com.pitch.databinding.FragmentOtpFragmentBinding
import com.pitch.ui.adapter.HomeAdapter
import com.pitch.ui.base.BaseFragment
import com.pitch.ui.fragments.otp.OtpFragmentFragmentArgs
import com.pitch.ui.fragments.otp.OtpViewMode
import com.pitch.utils.DummyData.list
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()

    override fun getLayoutRes() = R.layout.fragment_home
    override fun initViewModel(viewModel: HomeViewModel) {
        binding.homeViewModel = viewModel
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        onClickListeners()
        homeAdapterInitialization()
    }

    private fun onClickListeners() {
        binding.apply {

        }
    }

    private fun homeAdapterInitialization() {
        binding.apply {
            val adapter = HomeAdapter { model, position ->
                findNavController().navigate(R.id.toAddProjectDetails)
            }
            rvHome.adapter = adapter
            adapter.submitList(list)
        }
    }
}