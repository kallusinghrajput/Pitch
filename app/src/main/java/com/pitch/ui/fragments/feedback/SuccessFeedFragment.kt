package com.pitch.ui.fragments.feedback

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pitch.R
import com.pitch.databinding.FragmentFeedbackBinding
import com.pitch.databinding.FragmentSuccessFeedBinding
import com.pitch.ui.activities.home.HomeActivity
import com.pitch.ui.activities.home.HomeViewModel
import com.pitch.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuccessFeedFragment : BaseFragment<FragmentSuccessFeedBinding, FeedbackViewModel>() {

    override val viewModel: FeedbackViewModel by viewModels()
    val homeViewModel: HomeViewModel by activityViewModels()

    override fun getLayoutRes() = R.layout.fragment_success_feed
    override fun initViewModel(viewModel: FeedbackViewModel) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        onClickListeners()
        homeViewModel.bottomProgressBar(true)
    }

    private fun onClickListeners() {
        binding.apply {
            btnForgot.setOnClickListener {
             findNavController().navigateUp()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        homeViewModel.bottomProgressBar(false)
    }
}