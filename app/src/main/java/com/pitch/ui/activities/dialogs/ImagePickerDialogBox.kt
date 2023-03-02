package com.pitch.ui.activities.dialogs

import android.Manifest
import android.content.ContentValues
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.pitch.R
import com.pitch.databinding.DialogAttachmentBinding
import com.pitch.utils.ImageUtils
import com.pitch.utils.ImageUtils.getRealPathFromURI
import com.pitch.utils.extention.hasCameraPermission
import com.pitch.utils.extention.hasGalleryPermission
import com.pitch.utils.extention.toast


class ImagePickerDialogBox(private val uriInterface: UriInterface) :
    DialogFragment() {
    lateinit var binding: DialogAttachmentBinding
    var fileUri: Uri? = null
    var galleryUri: Uri? = null
    var camera: Boolean = false
    var read: Boolean = false
    var write: Boolean = false
    var imageName: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogAttachmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.close.setOnClickListener {
            dismiss()
        }
        binding.btnGalley.setOnClickListener {
            if (requireContext().hasGalleryPermission()) {
                openGallery()
            } else {
                requestSinglePermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }

        binding.btnCamera.setOnClickListener {
            if (requireContext().hasCameraPermission()) {
                myClickHandler()
            } else {
                requestMultiplePermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                    )
                )
            }
        }
    }

    private val pickImages =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { it ->
                galleryUri = it
                //  ivBackProfile.load(it)
                val path = requireActivity().getRealPathFromURI(it)
                /*    val temp = path!!.substring(path!!.toString().lastIndexOf(".") + 1)
                  if (temp == "png" || temp == "jpg" || temp == "jpeg") {
                      //   getCropUri("rectangle", it.toString())*/
                uriInterface.getGalleryUri(it, path!!)
                // } else requireContext().toast("Please select image in jpg, jpeg and png format.")
                dismiss()
            }
        }

    private fun openGallery() {
        pickImages.launch("image/*")
    }


    /**
     *
     * this is for gallery permission
     *
     */
    private val requestSinglePermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                openGallery()
            } else {
                requireActivity().toast("Permission Denied.")
            }
        }

    /**
     *
     * this is for camera permission
     *
     */
    private val requestMultiplePermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { resultsMap ->
            resultsMap.forEach {
                when (it.key) {
                    Manifest.permission.CAMERA -> {
                        camera = it.value
                    }
                    Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                        write = it.value
                    }
                    Manifest.permission.READ_EXTERNAL_STORAGE -> {
                        read = it.value
                    }
                }
            }

            if (camera && write && read) {
                myClickHandler()
            } else {
                dismiss()
                requireContext().toast("Permission Denied")
            }
        }

    private val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { imageCaptured ->
            if (imageCaptured) {
                //toast("Image Successfully captured")
                // ivBackProfile.load(fileUri)
                uriInterface.getCameraUri(fileUri, imageName)
                dismiss()
            }

        }


    private fun myClickHandler() {
        ImageUtils.createImageFile(requireContext())
        val imageUri =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Images.Media.getContentUri(
                    MediaStore.VOLUME_EXTERNAL_PRIMARY
                )
            } else {
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            }
        val imageDetails = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, ImageUtils.imageName)
        }
        requireActivity().contentResolver.insert(imageUri, imageDetails).let {
            fileUri = it
            takePicture.launch(fileUri)
            imageName = ImageUtils.imageName

        } ?: run {
        }

    }

    override fun onStart() {
        super.onStart()

        val width = (resources.displayMetrics.widthPixels * 0.95).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.60).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun getTheme() = R.style.RoundedCornersDialog

    interface UriInterface {
        fun getCameraUri(fileUri: Uri?, imageName: String?)
        fun getGalleryUri(uri: Uri, path: String)
    }


}