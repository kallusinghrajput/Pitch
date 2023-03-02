package com.pitch.ui.fragments.otp

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pitch.R
import com.pitch.databinding.FragmentOtpFragmentBinding
import com.pitch.ui.activities.dialogs.ChangeMobileNumberDialog
import com.pitch.ui.base.BaseFragment
import com.pitch.utils.extention.inVisible
import com.pitch.utils.extention.show
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class OtpFragmentFragment : BaseFragment<FragmentOtpFragmentBinding, OtpViewMode>() {
    private var isShow = false
    private var isCnShow = false
    override val viewModel: OtpViewMode by viewModels()
    private val args by navArgs<OtpFragmentFragmentArgs>()

    override fun getLayoutRes() = R.layout.fragment_otp_fragment
    override fun initViewModel(viewModel: OtpViewMode) {
        binding.otpViewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, onBackPress)
        initialize()
    }

    private fun initialize() {
        onClickListeners()
        startTimer(15000)
    }

    private fun onClickListeners() {
        binding.apply {
            btnResendOtp.setOnClickListener {
                startTimer(15000)
            }
            btnChangeNumber.setOnClickListener {
                if (args.isTrue) {
                    findNavController().navigateUp()
                } else {
                    ChangeMobileNumberDialog(object :
                        ChangeMobileNumberDialog.MobileNumberDialogInterface {
                        override fun dialogCallback(reason: String) {

                        }

                    }).show(parentFragmentManager, "tag")
                }
            }
            btnVerify.setOnClickListener {
                if (args.isTrue)
                    findNavController().navigate(R.id.toReset)
                else
                    findNavController().navigate(R.id.verifiedFragment)
            }
        }
    }


    /**
     *
     * Timer
     *
     */
    private fun startTimer(onTimerStart: Int) {
        binding.btnResendOtp.inVisible()

        binding.tvTimer.show()
        object : CountDownTimer(onTimerStart.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val hms = String.format(
                    "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                    ),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                    )
                )
                binding.tvTimer.text = hms
            }

            override fun onFinish() {
                binding.btnResendOtp.show()
                binding.tvTimer.inVisible()
            }
        }.start()
    }


    /**
     * On back pressed callback to handle
     */
    private val onBackPress = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (args.isTrue) {
                findNavController().navigateUp()
            } else {
                findNavController().popBackStack(R.id.loginFragment, false)
            }
        }

    }

}