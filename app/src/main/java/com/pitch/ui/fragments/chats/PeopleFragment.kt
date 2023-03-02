package com.pitch.ui.fragments.chats

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
import com.pitch.databinding.FragmentBlogBinding
import com.pitch.databinding.FragmentPeopleBinding
import com.pitch.ui.activities.home.HomeViewModel
import com.pitch.ui.adapter.BlogAdapter
import com.pitch.ui.adapter.PeopleAdapter
import com.pitch.ui.base.BaseFragment
import com.pitch.ui.fragments.blog.BlogViewModel
import com.pitch.utils.DummyData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : BaseFragment<FragmentPeopleBinding, PeopleViewModel>() {

    override val viewModel: PeopleViewModel by viewModels()
    val homeViewModel: HomeViewModel by activityViewModels()

    override fun getLayoutRes() = R.layout.fragment_people
    override fun initViewModel(viewModel: PeopleViewModel) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        onClickListeners()
        homeViewModel.bottomProgressBar(true)
        blogAdapterInitialization()
    }

    private fun blogAdapterInitialization() {
        binding.apply {
            val adapter = PeopleAdapter { model, position ->
                findNavController().navigate(R.id.toChat)
            }
            rvPeople.adapter = adapter
            adapter.submitList(DummyData.list)
        }
    }

    private fun onClickListeners() {
        binding.apply {
            toolbar.apply {
                tvTitle.text = getString(R.string.chats)
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