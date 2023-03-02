package com.pitch.ui.fragments.upload

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.pitch.R
import com.pitch.databinding.FragmentUploadVideoBinding
import com.pitch.ui.activities.home.HomeViewModel
import com.pitch.ui.base.BaseFragment
import com.pitch.utils.extention.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
@AndroidEntryPoint
class UploadVideoFragment : BaseFragment<FragmentUploadVideoBinding, UploadVideoViewModel>() {

    override val viewModel: UploadVideoViewModel by viewModels()
    val homeViewModel: HomeViewModel by activityViewModels()
    val args by navArgs<UploadVideoFragmentArgs>()
    override fun getLayoutRes() = R.layout.fragment_upload_video
    override fun initViewModel(viewModel: UploadVideoViewModel) {
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
            ivBack.setOnClickListener {
                findNavController().navigateUp()
            }
            if (args.isThumbnail.isNotEmpty())
                ivImage.load(args.isThumbnail)
            lifecycleScope.launch {
                var i = 20
                while (i < 101) {
                    progressBar.progress = i
                    tvProgrss.text = "$i%"
                    delay(1000)
                    i += 20
                }
                findNavController().navigate(R.id.toAddProject)
                requireActivity().toast("video uploaded successfully")

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        homeViewModel.bottomProgressBar(false)
    }


}