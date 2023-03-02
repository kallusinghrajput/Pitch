package com.pitch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pitch.data.model.HomeModel
import com.pitch.databinding.HomeRowLayoutBinding
import com.pitch.databinding.PeopleRowLayoutBinding


class PeopleAdapter(private val click: (model: HomeModel, position: Int) -> Unit) :
    ListAdapter<HomeModel, PeopleAdapter.MyViewHolder>(DiffUtills()) {

    class MyViewHolder(val binding: PeopleRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)


    class DiffUtills : DiffUtil.ItemCallback<HomeModel>() {
        override fun areItemsTheSame(oldItem: HomeModel, newItem: HomeModel) =
            oldItem.image == newItem.image

        override fun areContentsTheSame(
            oldItem: HomeModel,
            newItem: HomeModel
        ) = oldItem == newItem
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            PeopleRowLayoutBinding.inflate(
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
                click.invoke(model, position)
            }
            executePendingBindings()
        }
    }
}