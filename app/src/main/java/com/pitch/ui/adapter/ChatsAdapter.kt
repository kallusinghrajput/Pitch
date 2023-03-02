package com.pitch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pitch.data.model.ChatModelModel
import com.pitch.databinding.ChatRowLayoutBinding
import com.pitch.utils.extention.gone
import com.pitch.utils.extention.inVisible
import com.pitch.utils.extention.show


class ChatsAdapter(private val click: (model: ChatModelModel, position: Int) -> Unit) :
    ListAdapter<ChatModelModel, ChatsAdapter.MyViewHolder>(DiffUtills()) {

    class MyViewHolder(val binding: ChatRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)


    class DiffUtills : DiffUtil.ItemCallback<ChatModelModel>() {
        override fun areItemsTheSame(oldItem: ChatModelModel, newItem: ChatModelModel) =
            oldItem.image == newItem.image

        override fun areContentsTheSame(
            oldItem: ChatModelModel,
            newItem: ChatModelModel
        ) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ChatRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            val model = getItem(position)
            chatDevider.chatModel = model
            chatLeft.chatModel = model
            chatRight.chatModel = model
            chatLeft.root.gone()
            chatRight.root.gone()
            if (model.isMe) {
                chatRight.root.show()
                if (model.isFirst) {
                    chatRight.ivImage.show()
                } else {
                    chatRight.ivImage.inVisible()

                }
            } else {
                chatLeft.root.show()
                if (model.isFirst) {
                    chatLeft.ivImage.show()
                } else {
                    chatLeft.ivImage.inVisible()
                }
            }
            executePendingBindings()
        }
    }
}