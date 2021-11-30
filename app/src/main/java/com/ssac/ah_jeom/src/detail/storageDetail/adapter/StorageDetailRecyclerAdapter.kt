package com.ssac.ah_jeom.src.detail.storageDetail.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.ActivityStorageDetailGridRecyclerItemBinding
import com.ssac.ah_jeom.src.detail.artistDetail.ArtistDetailActivity
import com.ssac.ah_jeom.src.detail.storageDetail.models.GetStorageDetailResponse
import com.ssac.ah_jeom.src.detail.storageDetail.models.StorageDetailRecyclerData
import com.ssac.ah_jeom.src.main.subscribe.bestArtist.BestArtistActivity

class StorageDetailRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<StorageDetailRecyclerAdapter.ProductHolder>() {

    var listData = mutableListOf<StorageDetailRecyclerData>()

    interface OnItemClickListener {
        fun onItemClick(v: View, data: StorageDetailRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ActivityStorageDetailGridRecyclerItemBinding.inflate(
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


    inner class ProductHolder(val binding: ActivityStorageDetailGridRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }


        fun setData(data: StorageDetailRecyclerData) {
            Glide.with(itemView.context).load(data.image).into(binding.activityStorageDetailGridRecyclerItem)
        }

    }

}