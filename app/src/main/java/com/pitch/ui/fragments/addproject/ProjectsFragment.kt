package com.pitch.ui.fragments.addproject

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
import com.pitch.databinding.FragmentProjectDetailsBinding
import com.pitch.databinding.FragmentProjectsBinding
import com.pitch.ui.activities.home.HomeViewModel
import com.pitch.ui.adapter.HomeAdapter
import com.pitch.ui.base.BaseFragment
import com.pitch.utils.DummyData


class ProjectsFragment : BaseFragment<FragmentProjectsBinding, AddProjectViewModel>() {

    override val viewModel: AddProjectViewModel by viewModels()
    val homeViewModel: HomeViewModel by activityViewModels()
    val args by navArgs<ProjectsFragmentArgs>()
    override fun getLayoutRes() = R.layout.fragment_projects
    override fun initViewModel(viewModel: AddProjectViewModel) {
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
                tvTitle.text = args.title
                ivBack.setOnClickListener {
                    findNavController().navigateUp()
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