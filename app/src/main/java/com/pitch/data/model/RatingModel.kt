package com.pitch.data.model

import android.os.Parcelable
import com.pitch.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class RatingModel(
    val image: Int = R.drawable.dummy_image_1,
    val title: String = "In curabitur",
    val rating: Float = 4.5F,
    val price: String = "40.5 $",
    val subTitle: String = "Gravida posuere tempor quam tristique auctor arcu suspendisse odio sagittis. Mi sit vulputate eget massa neque non ut nec.",
) : Parcelable
