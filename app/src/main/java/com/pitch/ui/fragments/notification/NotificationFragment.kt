package com.pitch.ui.fragments.notification

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pitch.R
import com.pitch.databinding.FragmentNotificationBinding
import com.pitch.databinding.FragmentProjectsBinding
import com.pitch.ui.activities.home.HomeViewModel
import com.pitch.ui.adapter.HomeAdapter
import com.pitch.ui.base.BaseFragment
import com.pitch.ui.fragments.addproject.AddProjectViewModel
import com.pitch.ui.fragments.addproject.ProjectsFragmentArgs
import com.pitch.utils.DummyData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : BaseFragment<FragmentNotificationBinding, NotificationViewModel>() {

    override val viewModel: NotificationViewModel by viewModels()
    val homeViewModel: HomeViewModel by activityViewModels()
    override fun getLayoutRes() = R.layout.fragment_notification
    override fun initViewModel(viewModel: NotificationViewModel) {
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
            toolbar.apply {
                tvTitle.text = getString(R.string.notifications)
                ivBack.setOnClickListener {
                    requireActivity().onBackPressed()
                }
            }
        }

    }

    private fun homeAdapterInitialization() {
        binding.apply {
            val adapter = HomeAdapter { model, position ->
                findNavController().navigate(R.id.toAddProjectDetails)
            }
            rvHome.adapter = adapter
            adapter.submitList(DummyData.list)
        }
    }
}