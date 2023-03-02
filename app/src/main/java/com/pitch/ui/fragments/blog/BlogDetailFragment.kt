package com.pitch.ui.fragments.blog

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
import com.pitch.databinding.FragmentBlogDetailBinding
import com.pitch.ui.activities.home.HomeViewModel
import com.pitch.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlogDetailFragment : BaseFragment<FragmentBlogDetailBinding, BlogViewModel>() {

    override val viewModel: BlogViewModel by viewModels()
    val homeViewModel: HomeViewModel by activityViewModels()

    override fun getLayoutRes() = R.layout.fragment_blog_detail
    override fun initViewModel(viewModel: BlogViewModel) {
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
            toolbar.apply {
                tvTitle.text = getString(R.string.blog1)
                ivBack.setOnClickListener {
                    findNavController().navigateUp()
                }
            }
        }
    }

}