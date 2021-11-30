package com.ssac.ah_jeom.src.main.subscribe.bestArtist.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.ActivityBestArtistRecyclerItemBinding
import com.ssac.ah_jeom.src.detail.artistDetail.ArtistDetailActivity
import com.ssac.ah_jeom.src.main.subscribe.bestArtist.BestArtistActivity
import com.ssac.ah_jeom.src.main.subscribe.bestArtist.models.BestArtistRecyclerData
import com.ssac.ah_jeom.src.main.subscribe.bestArtist.models.GetBestArtistResponse

class BestArtistRecyclerAdapter(private val context: Context, response: GetBestArtistResponse) :
    RecyclerView.Adapter<BestArtistRecyclerAdapter.PagerViewHolder>() {

    var listData = mutableListOf<BestArtistRecyclerData>()

    val response = response

    interface OnItemClickListener {
        fun onItemClick(v: View, data: BestArtistRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BestArtistRecyclerAdapter.PagerViewHolder {
        val binding = ActivityBestArtistRecyclerItemBinding.inflate(
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

    inner class PagerViewHolder(val binding: ActivityBestArtistRecyclerItemBinding) :
        RecyclerView.ViewHolder
            (binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }

        fun setData(data: BestArtistRecyclerData) {

            Glide.with(itemView.context).load(data.image).circleCrop().into(binding.activityBestArtistRecyclerProfileImage)
            binding.activityBestArtistRecyclerRateImage.setImageResource(data.rateImage)
            binding.activityBestArtistRecyclerNameText.text = data.name
            binding.activityBestArtistRecyclerSubscribeNumberText.text = data.subscribeNumber
            binding.activityBestArtistRecyclerRateText.text = data.rate

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)

                    for (i in 0 until response.result.best.size) {
                        if (pos == i) {
                            val intent = Intent(itemView.context, ArtistDetailActivity::class.java)
                            intent.putExtra("artistId", response.result.best[pos].userId)
                            itemView.context.startActivity(intent)
                            (itemView.context as BestArtistActivity).overridePendingTransition(
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