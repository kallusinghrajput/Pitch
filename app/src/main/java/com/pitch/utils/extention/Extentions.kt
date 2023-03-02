package com.pitch.utils.extention

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.pitch.R
import java.io.ByteArrayOutputStream
import java.util.*


fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}

fun Context.hasCameraPermission() = ContextCompat.checkSelfPermission(
    this,
    Manifest.permission.CAMERA
) == PackageManager.PERMISSION_GRANTED

fun Context.hasGalleryPermission() = ContextCompat.checkSelfPermission(
    this,
    Manifest.permission.READ_EXTERNAL_STORAGE
) == PackageManager.PERMISSION_GRANTED

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/**
 * this is for create thumbnail from video
 */
fun Activity.createThumbnail(path: String?): Bitmap? {
    var mediaMetadataRetriever: MediaMetadataRetriever? = null
    var bitmap: Bitmap? = null
    try {
        mediaMetadataRetriever = MediaMetadataRetriever()
        mediaMetadataRetriever.setDataSource(this, Uri.parse(path))
        bitmap = mediaMetadataRetriever.getFrameAtTime(
            1000,
            MediaMetadataRetriever.OPTION_CLOSEST_SYNC
        )
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        mediaMetadataRetriever?.release()
    }
    return bitmap
}


fun Context.getImageUriFromBitmap(bitmap: Bitmap): Uri {
    val bytes = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path =
        MediaStore.Images.Media.insertImage(
            this.contentResolver,
            bitmap,
            Date().toString(),
            null
        )
    return Uri.parse(path.toString())
}

fun Activity.changeStatusBarColor(colorResId: Int, darkStatusBarIcons: Boolean) {
    this.window?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val wic = WindowInsetsControllerCompat(it, it.decorView)
            wic.isAppearanceLightStatusBars = darkStatusBarIcons
            it.statusBarColor = ContextCompat.getColor(this, colorResId)
        }

    }
}




/**
 * this is for message dialog box
 */
fun Context.showDialogs(message: String, onClick: () -> Unit) {
    val builder = AlertDialog.Builder(this, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert)
    builder.setMessage(message)

    builder.setPositiveButton(resources.getString(R.string.yes)) { dialog, _ ->
        onClick()
        dialog.cancel()
    }
    builder.setNegativeButton(resources.getString(R.string.no)) { dialog, _ ->
        dialog.cancel()
    }
    val alert = builder.create()
    alert.show()
}


/**
 * this is for get text color
 */
fun Context.textColor(@ColorRes color: Int): Int {
    return ContextCompat.getColor(this, color)
}