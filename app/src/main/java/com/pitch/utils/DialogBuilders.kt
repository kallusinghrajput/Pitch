package com.pitch.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.pitch.R

object DialogBuilders {
    fun getDialogBuilder(activity: Activity, view: View): AlertDialog {
        val dialogBuilder = AlertDialog.Builder(activity)
        dialogBuilder.setView(view)
        val dialog = dialogBuilder.create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setGravity(Gravity.CENTER)
        return dialog
    }

    fun Context.selectImage(click: (position: Int) -> Unit) {
        val options =
            arrayOf<CharSequence>(getString(R.string.take_a_photo), getString(R.string.gallery))
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.choose_media))
        builder.setItems(options) { dialog: DialogInterface?, item: Int ->
            if (options[item] == getString(R.string.take_a_photo)) {
                click(1)
            } else if (options[item] == getString(R.string.gallery)) {
                click(2)
            }
        }
        builder.show()
    }
}