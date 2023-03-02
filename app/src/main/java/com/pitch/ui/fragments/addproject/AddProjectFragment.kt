package com.pitch.ui.fragments.addproject

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.pitch.R
import com.pitch.databinding.FragmentAddBinding
import com.pitch.databinding.FragmentAddProjectBinding
import com.pitch.ui.activities.home.HomeActivity
import com.pitch.ui.activities.home.HomeViewModel
import com.pitch.ui.base.BaseFragment
import com.pitch.ui.fragments.add.AddViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProjectFragment : BaseFragment<FragmentAddProjectBinding, AddProjectViewModel>() {

    override val viewModel: AddProjectViewModel by viewModels()
    val homeViewModel: HomeViewModel by activityViewModels()

    override fun getLayoutRes() = R.layout.fragment_add_project
    override fun initViewModel(viewModel: AddProjectViewModel) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, onBackPress)

    }

    /**
     * On back pressed callback to handle
     */
    private val onBackPress = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            startActivity(Intent(requireActivity(), HomeActivity::class.java))
            requireActivity().finish()
        }

    }

    private fun initialize() {
        onClickListeners()
        homeViewModel.bottomProgressBar(true)
    }

    private fun onClickListeners() {
        binding.apply {
            toolbar.apply {
                tvTitle.text = getString(R.string.add_project)
                ivBack.setOnClickListener {
                    startActivity(Intent(requireActivity(), HomeActivity::class.java))
                    requireActivity().finish()
                }
            }

            ivImage.setOnClickListener {
                (activity as HomeActivity).openCameraAndGallery { data, _ ->
                    ivImage.load(data)
                }
            }

            btnDone.setOnClickListener {
                startActivity(Intent(requireActivity(), HomeActivity::class.java))
                requireActivity().finish()
            }
            btnBanner.setOnClickListener {
                (activity as HomeActivity).openCameraAndGallery { _, path ->
                    etBanner.setText(path)
                }
            }
            btnPhoto.setOnClickListener {
                (activity as HomeActivity).openCameraAndGallery { _, path ->
                    etUpload.setText(path)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        homeViewModel.bottomProgressBar(false)
    }
}