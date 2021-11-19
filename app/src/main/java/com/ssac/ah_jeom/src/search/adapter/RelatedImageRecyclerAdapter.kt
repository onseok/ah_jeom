package com.ssac.ah_jeom.src.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssac.ah_jeom.databinding.ActivitySearchGridRecyclerItemBinding
import com.ssac.ah_jeom.src.search.models.RelatedImageRecyclerData

class RelatedImageRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<RelatedImageRecyclerAdapter.ProductHolder>() {

    var listData = mutableListOf<RelatedImageRecyclerData>()


    interface OnItemClickListener {
        fun onItemClick(v: View, data: RelatedImageRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ActivitySearchGridRecyclerItemBinding.inflate(
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


    inner class ProductHolder(val binding: ActivitySearchGridRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }


        fun setData(data: RelatedImageRecyclerData) {
            binding.activitySearchGridRecyclerItemImage.setImageResource(data.relatedImageRecyclerData)
        }

    }
}