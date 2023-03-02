package com.pitch.ui.activities.authantication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.pitch.R
import com.pitch.databinding.ActivityAuthenticationBinding
import com.pitch.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity :
    BaseActivity<ActivityAuthenticationBinding, AuthenticationViewModel>() {

    override val viewModel: AuthenticationViewModel by viewModels()

    override fun getLayoutRes() = R.layout.activity_authentication

    override fun initViewModel(viewModel: AuthenticationViewModel) {
        binding.authenticationViewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialization()
        changeStatusBarColor(R.color.start_orange_color)
    }

    private fun initialization() {

    }
}