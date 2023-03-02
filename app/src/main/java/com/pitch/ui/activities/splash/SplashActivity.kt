package com.pitch.ui.activities.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.pitch.R
import com.pitch.databinding.ActivitySplashBinding
import com.pitch.ui.activities.authantication.AuthenticationActivity
import com.pitch.ui.activities.home.HomeViewModel
import com.pitch.ui.base.BaseActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.apply {
            statusBarColor = getColorFromRes(R.color.start_orange_color)
        }
        lifecycleScope.launch {
            delay(3000)
            startActivity(Intent(this@SplashActivity, AuthenticationActivity::class.java))
            finish()
        }
    }
    private fun getColorFromRes(barColor: Int): Int {
        return ContextCompat.getColor(this, barColor)
    }
}