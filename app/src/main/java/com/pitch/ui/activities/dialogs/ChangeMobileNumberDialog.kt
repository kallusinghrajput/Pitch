package com.pitch.ui.activities.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.pitch.databinding.EditDialogLayoutBinding
import com.pitch.utils.DialogBuilders

class ChangeMobileNumberDialog(
    private var deleteDialogInterface: MobileNumberDialogInterface
) : DialogFragment() {
    private lateinit var binding: EditDialogLayoutBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = EditDialogLayoutBinding.inflate(layoutInflater)
        binding.yes.setOnClickListener {
            binding.ltMobileNumber.apply {
                val reason = etPhoneNumber.text.toString().trim()
                if (reason.isEmpty()) {
                    binding.tvError.text = "Please enter phone number"
                    return@setOnClickListener
                }
                deleteDialogInterface.dialogCallback(reason)
                dismiss()
            }
        }
        binding.close.setOnClickListener {
            dismiss()
        }
        return DialogBuilders.getDialogBuilder(requireActivity(), binding.root)
    }

    interface MobileNumberDialogInterface {
        fun dialogCallback(reason: String)
    }
}