package com.pitch.utils.customviews

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import com.pitch.utils.extention.gone
import com.pitch.utils.extention.show


@BindingAdapter("isFirstTime")
fun View.visibility(isFirstTime: Boolean) {
    if (isFirstTime)
        show()
    else
        gone()


    Log.e("TAG", "visibility: ", )
}




