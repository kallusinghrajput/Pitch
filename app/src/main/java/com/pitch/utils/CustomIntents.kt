package com.pitch.utils

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore

object CustomIntents {

    val galleryIntent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
        type = "video/*"
    }

    fun Activity.cameraIntent(click: (intent: Intent) -> Unit) {
        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
            takeVideoIntent.resolveActivity(packageManager)?.also {
                click(takeVideoIntent)
            }
        }
    }
}