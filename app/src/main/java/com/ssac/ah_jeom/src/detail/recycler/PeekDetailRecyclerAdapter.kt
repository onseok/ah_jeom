package com.ssac.ah_jeom.src.detail.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssac.ah_jeom.databinding.ActivityPeekDetailGridRecyclerItemBinding

class PeekDetailRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<PeekDetailRecyclerAdapter.ProductHolder>() {

    var listData = mutableListOf<PeekDetailRecyclerData>()


    interface OnItemClickListener {
        fun onItemClick(v: View, data: PeekDetailRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ActivityPeekDetailGridRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val data = listData[position]
        holder.setData(data)
    }


    inner class ProductHolder(val binding: ActivityPeekDetailGridRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }


        fun setData(data: PeekDetailRecyclerData) {
            binding.activityPeekDetailGridRecyclerItem.setImageResource(data.peekDetailRecyclerImage)
        }

    }

}