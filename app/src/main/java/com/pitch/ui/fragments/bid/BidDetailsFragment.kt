package com.pitch.ui.fragments.bid

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
import com.pitch.databinding.FragmentBidBinding
import com.pitch.databinding.FragmentBidDetailsBinding
import com.pitch.ui.activities.home.HomeViewModel
import com.pitch.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BidDetailsFragment : BaseFragment<FragmentBidDetailsBinding, BidViewModel>() {

    override val viewModel: BidViewModel by viewModels()
    val homeViewModel: HomeViewModel by activityViewModels()
    val args by navArgs<BidDetailsFragmentArgs>()
    override fun getLayoutRes() = R.layout.fragment_bid_details
    override fun initViewModel(viewModel: BidViewModel) {
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
            dataModel = args.model
            toolbar.apply {
                //  tvTitle.text = getString(R.string.blog)
                ivBack.setOnClickListener {
                    findNavController().navigateUp()
                }
                btnChat.setOnClickListener {
                    findNavController().navigate(R.id.toChat)

                }
                tvRatings.setOnClickListener {
                  findNavController().navigate(R.id.toFeedback)
                }
            }
        }
    }
}