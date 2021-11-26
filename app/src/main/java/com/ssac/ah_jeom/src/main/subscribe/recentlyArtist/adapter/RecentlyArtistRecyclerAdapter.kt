package com.ssac.ah_jeom.src.main.subscribe.recentlyArtist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.databinding.ActivityRecentlyArtistRecyclerItemBinding
import com.ssac.ah_jeom.src.main.subscribe.recentlyArtist.models.RecentlyArtistRecyclerData

class RecentlyArtistRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<RecentlyArtistRecyclerAdapter.PagerViewHolder>() {

    var listData = mutableListOf<RecentlyArtistRecyclerData>()

    interface OnItemClickListener {
        fun onItemClick(v: View, data: RecentlyArtistRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentlyArtistRecyclerAdapter.PagerViewHolder {
        val binding = ActivityRecentlyArtistRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val data = listData[position]
        holder.setData(data)
    }

    inner class PagerViewHolder(val binding: ActivityRecentlyArtistRecyclerItemBinding) :
        RecyclerView.ViewHolder
            (binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }

        fun setData(data: RecentlyArtistRecyclerData) {
            Glide.with(itemView.context).load(data.image).circleCrop().into(binding.activityRecentlyArtistRecyclerProfileImage)
            binding.activityRecentlyArtistRecyclerRateImage.setImageResource(data.rateImage)
            binding.activityRecentlyArtistRecyclerNameText.text = data.name
            binding.activityRecentlyArtistRecyclerSubscribeNumberText.text = data.subscribeNumber
            binding.activityRecentlyArtistRecyclerRateText.text = data.rate

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)

                }
            }
        }

    }
}