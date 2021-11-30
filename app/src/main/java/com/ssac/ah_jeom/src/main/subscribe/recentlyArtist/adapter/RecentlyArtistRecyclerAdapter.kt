package com.ssac.ah_jeom.src.main.subscribe.recentlyArtist.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.ActivityRecentlyArtistRecyclerItemBinding
import com.ssac.ah_jeom.src.detail.artistDetail.ArtistDetailActivity
import com.ssac.ah_jeom.src.main.subscribe.recentlyArtist.RecentlyArtistActivity
import com.ssac.ah_jeom.src.main.subscribe.recentlyArtist.models.GetRecentlyArtistResponse
import com.ssac.ah_jeom.src.main.subscribe.recentlyArtist.models.RecentlyArtistRecyclerData

class RecentlyArtistRecyclerAdapter(private val context: Context, response: GetRecentlyArtistResponse) :
    RecyclerView.Adapter<RecentlyArtistRecyclerAdapter.PagerViewHolder>() {

    var listData = mutableListOf<RecentlyArtistRecyclerData>()

    val response = response

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

                    for (i in 0 until response.result.sub.size) {
                        if (pos == i) {
                            val intent = Intent(itemView.context, ArtistDetailActivity::class.java)
                            intent.putExtra("artistId", response.result.sub[pos].userId)
                            itemView.context.startActivity(intent)
                            (itemView.context as RecentlyArtistActivity).overridePendingTransition(
                                R.anim.activity_fade_in,
                                R.anim.activity_fade_out
                            )
                        }
                    }
                }
            }
        }

    }
}