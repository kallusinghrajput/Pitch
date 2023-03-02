package com.pitch.ui.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.pitch.R
import com.pitch.databinding.FragmentEditProfileBinding
import com.pitch.databinding.FragmentProfileBinding
import com.pitch.ui.activities.home.HomeActivity
import com.pitch.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : BaseFragment<FragmentEditProfileBinding, ProfileViewModel>() {

    override val viewModel: ProfileViewModel by viewModels()

    override fun getLayoutRes() = R.layout.fragment_edit_profile
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
                findNavController().navigateUp()

            }

            btnSave.setOnClickListener {
                findNavController().navigateUp()

            }


            icCamera.setOnClickListener {
                (activity as HomeActivity).openCameraAndGallery { data, path ->
                    icProfile.load(data)
                }
            }
        }
    }
}