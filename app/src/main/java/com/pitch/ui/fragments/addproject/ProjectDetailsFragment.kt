package com.pitch.ui.fragments.addproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.pitch.R
import com.pitch.databinding.FragmentAddProjectBinding
import com.pitch.databinding.FragmentProjectDetailsBinding
import com.pitch.ui.activities.home.HomeActivity
import com.pitch.ui.activities.home.HomeViewModel
import com.pitch.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectDetailsFragment : BaseFragment<FragmentProjectDetailsBinding, AddProjectViewModel>() {

    override val viewModel: AddProjectViewModel by viewModels()
    val homeViewModel: HomeViewModel by activityViewModels()

    override fun getLayoutRes() = R.layout.fragment_project_details
    override fun initViewModel(viewModel: AddProjectViewModel) {
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
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        homeViewModel.bottomProgressBar(false)
    }
}