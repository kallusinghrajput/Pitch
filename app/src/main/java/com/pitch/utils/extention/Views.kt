package com.pitch.utils.extention

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageView
import com.pitch.R


fun EditText.hideAndShowPassword(ivEye: ImageView, show: Boolean) {
    if (show) {
        ivEye.setImageResource(R.drawable.ic_hide)
        transformationMethod = HideReturnsTransformationMethod.getInstance()
        setSelection(text!!.length)
    } else {
        ivEye.setImageResource(R.drawable.ic_hide)
        transformationMethod = PasswordTransformationMethod.getInstance()
        setSelection(text!!.length)
    }
}