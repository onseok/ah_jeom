package com.ssac.ah_jeom.src.main.subscribe.subscribeArtist.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.ActivityRecentlyArtistRecyclerItemBinding
import com.ssac.ah_jeom.databinding.ActivitySubscribeArtistRecyclerItemBinding
import com.ssac.ah_jeom.src.detail.artistDetail.ArtistDetailActivity
import com.ssac.ah_jeom.src.main.MainActivity
import com.ssac.ah_jeom.src.main.subscribe.bestArtist.BestArtistActivity
import com.ssac.ah_jeom.src.main.subscribe.subscribeArtist.SubscribeArtistActivity
import com.ssac.ah_jeom.src.main.subscribe.subscribeArtist.models.SubscribeArtistRecyclerData

class SubscribeArtistRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<SubscribeArtistRecyclerAdapter.PagerViewHolder>() {

    var listData = mutableListOf<SubscribeArtistRecyclerData>()

    interface OnItemClickListener {
        fun onItemClick(v: View, data: SubscribeArtistRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubscribeArtistRecyclerAdapter.PagerViewHolder {
        val binding = ActivitySubscribeArtistRecyclerItemBinding.inflate(
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

    inner class PagerViewHolder(val binding: ActivitySubscribeArtistRecyclerItemBinding) :
        RecyclerView.ViewHolder
            (binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }

        fun setData(data: SubscribeArtistRecyclerData) {

            binding.activitySubscribeArtistRecyclerProfileImage.setImageResource(data.image)
            binding.activitySubscribeArtistRecyclerRateImage.setImageResource(data.rateImage)
            binding.activitySubscribeArtistRecyclerNameText.text = data.name
            binding.activitySubscribeArtistRecyclerSubscribeNumberText.text = data.subscribeNumber
            binding.activitySubscribeArtistRecyclerRateText.text = data.rate

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)

                    if (pos == 0) {
                        val intent = Intent(itemView.context, ArtistDetailActivity::class.java)
                        itemView.context.startActivity(intent)
                        (itemView.context as SubscribeArtistActivity).overridePendingTransition(
                            R.anim.activity_fade_in,
                            R.anim.activity_fade_out
                        )
                    }
                    
                }
            }
        }

    }
}