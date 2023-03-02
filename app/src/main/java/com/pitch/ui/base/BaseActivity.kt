package com.pitch.ui.base

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<VB : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {

    abstract val viewModel: VM

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as VB
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel(viewModel)
        setupContentWindow()
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun initViewModel(viewModel: VM)

    /**
     *  Status Bar Color
     * */
    private fun setupContentWindow() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    fun changeStatusBarColor(@ColorRes barColor: Int) {
        window.apply {
            statusBarColor = getColorFromRes(barColor)
        }
    }

    private fun getColorFromRes(barColor: Int): Int {
        return ContextCompat.getColor(this, barColor)
    }


    private val permissionCallbacks = HashMap<Int, PermissionRequest>()
    fun onPermission(
        permission: String,
        onDeniedAction: (() -> Unit)? = null,
        onGrantedAction: () -> Unit
    ) {
        val requestCode = View.generateViewId()
        permissionCallbacks[requestCode] =
            PermissionRequest(permission, onDeniedAction, onGrantedAction)
        ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionCallbacks[requestCode]?.let {
            if (checkSelfPermission(it.permission) == PackageManager.PERMISSION_GRANTED) {
                it.onGrantedAction()
            } else {
                it.onDeniedAction?.invoke()
            }
            permissionCallbacks.remove(requestCode)
        }
    }

    private data class PermissionRequest(
        val permission: String,
        val onDeniedAction: (() -> Unit)?,
        val onGrantedAction: () -> Unit
    )

}