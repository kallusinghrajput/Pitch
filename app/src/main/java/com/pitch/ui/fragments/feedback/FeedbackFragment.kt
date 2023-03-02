package com.pitch.ui.fragments.feedback

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pitch.R
import com.pitch.databinding.FragmentChatBinding
import com.pitch.databinding.FragmentFeedbackBinding
import com.pitch.ui.activities.home.HomeViewModel
import com.pitch.ui.adapter.PeopleAdapter
import com.pitch.ui.base.BaseFragment
import com.pitch.ui.fragments.chats.PeopleViewModel
import com.pitch.utils.DummyData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedbackFragment : BaseFragment<FragmentFeedbackBinding, FeedbackViewModel>() {

    override val viewModel: FeedbackViewModel by viewModels()
    val homeViewModel: HomeViewModel by activityViewModels()

    override fun getLayoutRes() = R.layout.fragment_feedback
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
            toolbar.apply {
                ivBack.setOnClickListener {
                    findNavController().navigateUp()
                }
                btnForgot.setOnClickListener {
                    findNavController().navigate(R.id.toScussedFeedback)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        homeViewModel.bottomProgressBar(false)
    }
}