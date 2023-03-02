package com.pitch.ui.fragments.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pitch.R
import com.pitch.databinding.FragmentDashboardBinding
import com.pitch.ui.activities.home.HomeViewModel
import com.pitch.ui.adapter.ProjectsRowAdapter
import com.pitch.ui.base.BaseFragment
import com.pitch.utils.DummyData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

    override val viewModel: DashboardViewModel by viewModels()
    val homeViewModel: HomeViewModel by activityViewModels()

    override fun getLayoutRes() = R.layout.fragment_dashboard
    override fun initViewModel(viewModel: DashboardViewModel) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        onClickListeners()
        adapterInitialization()
    }


    private fun adapterInitialization() {
        binding.apply {
            val adapter = ProjectsRowAdapter { model, position ->
                if (position == 1) {
                    findNavController().navigate(R.id.toPeople)

                } else {
                    val des = DashboardFragmentDirections.toProject(model.title)
                    findNavController().navigate(des)
                }

            }
            rvProjects.adapter = adapter
            adapter.submitList(DummyData.listOfProjects)
        }
    }

    private fun onClickListeners() {
        binding.apply {
            toolbar.ivBack.setOnClickListener {
                findNavController().navigateUp()
            }

        }
    }

}