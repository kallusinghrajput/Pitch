package com.pitch.ui.fragments.blog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pitch.R
import com.pitch.databinding.FragmentBlogBinding
import com.pitch.ui.activities.home.HomeActivity
import com.pitch.ui.activities.home.HomeViewModel
import com.pitch.ui.adapter.BlogAdapter
import com.pitch.ui.base.BaseFragment
import com.pitch.utils.DummyData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlogFragment : BaseFragment<FragmentBlogBinding, BlogViewModel>() {

    override val viewModel: BlogViewModel by viewModels()
    val homeViewModel: HomeViewModel by activityViewModels()

    override fun getLayoutRes() = R.layout.fragment_blog
    override fun initViewModel(viewModel: BlogViewModel) {
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
            val adapter = BlogAdapter { model, position ->
                findNavController().navigate(R.id.toBlogDetails)
            }
            rvBlog.adapter = adapter
            adapter.submitList(DummyData.list)
        }
    }

    private fun onClickListeners() {
        binding.apply {
            toolbar.apply {
                tvTitle.text = getString(R.string.blog)
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