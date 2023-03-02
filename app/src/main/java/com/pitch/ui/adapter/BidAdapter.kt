package com.pitch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pitch.data.model.HomeModel
import com.pitch.data.model.RatingModel
import com.pitch.databinding.BidRowLayoutBinding
import com.pitch.databinding.HomeRowLayoutBinding


class BidAdapter(private val click: (model: RatingModel, position: Int, click: Int) -> Unit) :
    ListAdapter<RatingModel, BidAdapter.MyViewHolder>(DiffUtills()) {

    class MyViewHolder(val binding: BidRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)


    class DiffUtills : DiffUtil.ItemCallback<RatingModel>() {
        override fun areItemsTheSame(oldItem: RatingModel, newItem: RatingModel) =
            oldItem.image == newItem.image

        override fun areContentsTheSame(
            oldItem: RatingModel,
            newItem: RatingModel
        ) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            BidRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            val model = getItem(position)
            dataModel = model
            root.setOnClickListener {
                click.invoke(model, position, 1)
            }
            btnChat.setOnClickListener {
                click.invoke(model, position, 2)
            }

            executePendingBindings()
        }
    }
}