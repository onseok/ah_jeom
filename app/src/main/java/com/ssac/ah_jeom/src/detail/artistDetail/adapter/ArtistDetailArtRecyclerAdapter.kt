package com.ssac.ah_jeom.src.detail.artistDetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.databinding.ActivityArtistDetailArtGridRecyclerItemBinding
import com.ssac.ah_jeom.src.detail.artistDetail.models.ArtistDetailArtData

class ArtistDetailArtRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<ArtistDetailArtRecyclerAdapter.ProductHolder>() {

    var listData = mutableListOf<ArtistDetailArtData>()


    interface OnItemClickListener {
        fun onItemClick(v: View, data: ArtistDetailArtData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ActivityArtistDetailArtGridRecyclerItemBinding.inflate(
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


    inner class ProductHolder(val binding: ActivityArtistDetailArtGridRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }

        fun setData(data: ArtistDetailArtData) {
            Glide.with(itemView.context).load(data.image).into(binding.activityArtistDetailArtGridRecyclerImage)
        }

    }
}