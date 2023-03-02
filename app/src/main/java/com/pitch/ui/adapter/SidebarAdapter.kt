package com.pitch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pitch.data.model.HomeModel
import com.pitch.data.model.SideBarModel
import com.pitch.databinding.BlogRowLayoutBinding
import com.pitch.databinding.HomeRowLayoutBinding
import com.pitch.databinding.SideRowLayoutBinding


class SidebarAdapter(private val click: (model: SideBarModel, position: Int) -> Unit) :
    ListAdapter<SideBarModel, SidebarAdapter.MyViewHolder>(DiffUtills()) {

    class MyViewHolder(val binding: SideRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)


    class DiffUtills : DiffUtil.ItemCallback<SideBarModel>() {
        override fun areItemsTheSame(oldItem: SideBarModel, newItem: SideBarModel) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(
            oldItem: SideBarModel,
            newItem: SideBarModel
        ) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SideRowLayoutBinding.inflate(
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