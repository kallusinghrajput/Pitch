package com.pitch.ui.fragments.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.pitch.R
import com.pitch.databinding.FragmentAddBinding
import com.pitch.databinding.FragmentProfileBinding
import com.pitch.ui.activities.home.HomeActivity
import com.pitch.ui.base.BaseFragment
import com.pitch.ui.fragments.add.AddViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    override val viewModel: ProfileViewModel by viewModels()

    override fun getLayoutRes() = R.layout.fragment_profile
    override fun initViewModel(viewModel: ProfileViewModel) {
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
            ivBack.setOnClickListener {
                requireActivity().onBackPressed()

            }

            btnSave.setOnClickListener {
                requireActivity().onBackPressed()

            }


            icCamera.setOnClickListener {
                findNavController().navigate(R.id.toEdit)
                /*     (activity as HomeActivity).openCameraAndGallery { data, path ->
                         icProfile.load(data)
                     }*/
            }
        }
    }
}