package com.pitch.ui.fragments.bid

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
import com.pitch.databinding.FragmentBidBinding
import com.pitch.databinding.FragmentBlogBinding
import com.pitch.ui.activities.home.HomeViewModel
import com.pitch.ui.adapter.BidAdapter
import com.pitch.ui.adapter.BlogAdapter
import com.pitch.ui.base.BaseFragment
import com.pitch.ui.fragments.blog.BlogViewModel
import com.pitch.utils.DummyData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BidFragment : BaseFragment<FragmentBidBinding, BidViewModel>() {

    override val viewModel: BidViewModel by viewModels()
    val homeViewModel: HomeViewModel by activityViewModels()

    override fun getLayoutRes() = R.layout.fragment_bid
    override fun initViewModel(viewModel: BidViewModel) {
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
            val adapter = BidAdapter { model, position, click ->
                if (click == 2)
                    findNavController().navigate(R.id.toChat)
                else {
                    // findNavController().navigate(R.id.toFeedback)
                    val desc = BidFragmentDirections.toBidDetail(model)
                    findNavController().navigate(desc)
                }
            }
            rvBid.adapter = adapter
            adapter.submitList(DummyData.bidList)
        }
    }

    private fun onClickListeners() {
        binding.apply {
            toolbar.apply {
                //  tvTitle.text = getString(R.string.blog)
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