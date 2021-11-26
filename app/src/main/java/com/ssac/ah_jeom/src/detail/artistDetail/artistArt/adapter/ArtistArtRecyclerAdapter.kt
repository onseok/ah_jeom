package com.ssac.ah_jeom.src.detail.artistDetail.artistArt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssac.ah_jeom.databinding.ActivityArtistArtGridRecyclerItemBinding
import com.ssac.ah_jeom.src.detail.artistDetail.artistArt.models.ArtistArtRecyclerData

class ArtistArtRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<ArtistArtRecyclerAdapter.ProductHolder>() {

    var listData = mutableListOf<ArtistArtRecyclerData>()


    interface OnItemClickListener {
        fun onItemClick(v: View, data: ArtistArtRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ActivityArtistArtGridRecyclerItemBinding.inflate(
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


    inner class ProductHolder(val binding: ActivityArtistArtGridRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }

        fun setData(data: ArtistArtRecyclerData) {
            binding.activityArtistArtGridRecyclerImage.setImageResource(data.artistArtImage)
        }

    }
}