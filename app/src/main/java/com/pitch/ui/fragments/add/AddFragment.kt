package com.pitch.ui.fragments.add

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.pitch.R
import com.pitch.databinding.FragmentAddBinding
import com.pitch.ui.base.BaseFragment
import com.pitch.utils.*
import com.pitch.utils.CustomIntents.cameraIntent
import com.pitch.utils.extention.*
import com.pitch.utils.permissions.Permissions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : BaseFragment<FragmentAddBinding, AddViewModel>() {

    override val viewModel: AddViewModel by viewModels()
    var isCamera = false
    override fun getLayoutRes() = R.layout.fragment_add
    override fun initViewModel(viewModel: AddViewModel) {
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

            if (viewModel.videoThumbnail.isNotEmpty())
                ivImage.load(viewModel.videoThumbnail)

            ivBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
            //this is for gallery
            gallery.setOnClickListener {
                if (requireActivity().hasCameraPermission()) {
                    isCamera = true
                    requireActivity().cameraIntent {
                        pickVideoLauncher.launch(it)
                    }
                } else {
                    requestMultiplePermissionLauncher.launch(Permissions.UPLOAD_FILE_PERMISSIONS)
                }
            }
            //this is for camera
            btnCamera.setOnClickListener {
                if (requireActivity().hasGalleryPermission()) {
                    isCamera = false
                    pickVideoLauncher.launch(CustomIntents.galleryIntent)
                } else {
                    requestMultiplePermissionLauncher.launch(Permissions.UPLOAD_FILE_PERMISSIONS)
                }
            }
            btnUpload.setOnClickListener {
                val des =
                    AddFragmentDirections.toUploadVideo(
                        viewModel.uri,
                        isThumbnail = viewModel.videoThumbnail
                    )
                findNavController().navigate(des)
            }
        }
    }


    private val pickVideoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                result.data?.let {
                    loadVideos(it.data)
                } ?: requireActivity().toast("Something went going wrong")
            }
        }


    private val requestMultiplePermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { resultsMap ->
            resultsMap.forEach {
                if (it.key == Manifest.permission.CAMERA &&
                    it.key == Manifest.permission.WRITE_EXTERNAL_STORAGE &&
                    it.key == Manifest.permission.READ_EXTERNAL_STORAGE
                ) {
                    // requireActivity().toast("Permissions Granted you can take a  action.")

                } else {
                    // requireActivity().toast("Permissions Denied")
                }

            }
        }

    /**
     * load videos from galley and camera
     */
    private fun loadVideos(data: Uri?) {
        binding.apply {
            viewModel.uri = data.toString()
            val thumbnail = requireActivity().createThumbnail(data.toString())
            thumbnail?.let {
                viewModel.videoThumbnail = requireActivity().getImageUriFromBitmap(it).toString()
                ivImage.setImageURI(viewModel.videoThumbnail.toUri())
            } ?: requireActivity().toast("Video is not present in well format")
        }
    }
}