package com.ssac.ah_jeom.src.main.peek.bestStorage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssac.ah_jeom.databinding.ActivityBestStorageRecyclerItemBinding
import com.ssac.ah_jeom.src.main.peek.bestStorage.models.BestStorageRecyclerData

class BestStorageRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<BestStorageRecyclerAdapter.PagerViewHolder>() {

    var listData = mutableListOf<BestStorageRecyclerData>()

    interface OnItemClickListener {
        fun onItemClick(v: View, data: BestStorageRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BestStorageRecyclerAdapter.PagerViewHolder {
        val binding = ActivityBestStorageRecyclerItemBinding.inflate(
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

    inner class PagerViewHolder(val binding: ActivityBestStorageRecyclerItemBinding) :
        RecyclerView.ViewHolder
            (binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }

        fun setData(data: BestStorageRecyclerData) {

            binding.activityBestStorageProfileImage.setImageResource(data.profileImage)
            binding.activityBestStorageProfileNameText.text = data.profileName
            binding.activityBestStorageDownloadNumberText.text = data.downloadNumber
            binding.activityBestStorageMainImage.setImageResource(data.image)
            binding.activityBestStorageArtTitleText.text = data.artTitle
            binding.activityBestStorageLikedNumberText.text = data.artLikeNumber

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)

                }
            }
        }

    }
}