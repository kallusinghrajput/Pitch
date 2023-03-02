package com.pitch.data.model

import com.pitch.R

data class ChatModelModel(
    val image: Int = R.drawable.dummy_image_1,
    val isMe: Boolean = true,
    val isChats: Boolean = false,
    val isFirst: Boolean = false,
    val message: String = "Gravida posuere tempor quam tristique auctor arcu suspendisse odio sagittis. Mi sit vulputate eget massa neque non ut nec.",
)
