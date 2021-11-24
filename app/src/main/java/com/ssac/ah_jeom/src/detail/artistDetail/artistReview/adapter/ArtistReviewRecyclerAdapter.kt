package com.ssac.ah_jeom.src.detail.artistDetail.artistReview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssac.ah_jeom.databinding.ActivityArtistReviewRecyclerItemBinding
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.ArtistReviewRecyclerData

class ArtistReviewRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<ArtistReviewRecyclerAdapter.PagerViewHolder>() {

    var listData = mutableListOf<ArtistReviewRecyclerData>()

    interface OnItemClickListener {
        fun onItemClick(v: View, data: ArtistReviewRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArtistReviewRecyclerAdapter.PagerViewHolder {
        val binding = ActivityArtistReviewRecyclerItemBinding.inflate(
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

    inner class PagerViewHolder(val binding: ActivityArtistReviewRecyclerItemBinding) :
        RecyclerView.ViewHolder
            (binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }

        fun setData(data: ArtistReviewRecyclerData) {

            binding.activityArtistReviewRecyclerProfileImage.setImageResource(data.image)
            binding.activityArtistReviewRecyclerNameText.text = data.name
            binding.activityArtistReviewSampleReviewText.text = data.review

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)

                }
            }
        }

    }
}