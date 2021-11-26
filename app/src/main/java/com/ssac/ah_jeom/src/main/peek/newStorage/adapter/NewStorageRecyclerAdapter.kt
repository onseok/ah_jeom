package com.ssac.ah_jeom.src.main.peek.newStorage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssac.ah_jeom.databinding.ActivityNewStorageRecyclerItemBinding
import com.ssac.ah_jeom.src.main.peek.newStorage.models.NewStorageRecyclerData

class NewStorageRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<NewStorageRecyclerAdapter.PagerViewHolder>() {

    var listData = mutableListOf<NewStorageRecyclerData>()

    interface OnItemClickListener {
        fun onItemClick(v: View, data: NewStorageRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewStorageRecyclerAdapter.PagerViewHolder {
        val binding = ActivityNewStorageRecyclerItemBinding.inflate(
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

    inner class PagerViewHolder(val binding: ActivityNewStorageRecyclerItemBinding) :
        RecyclerView.ViewHolder
            (binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }

        fun setData(data: NewStorageRecyclerData) {

            binding.activityNewStorageProfileImage.setImageResource(data.profileImage)
            binding.activityNewStorageProfileNameText.text = data.profileName
            binding.activityNewStorageDownloadNumberText.text = data.downloadNumber
            binding.activityNewStorageMainImage.setImageResource(data.image)
            binding.activityNewStorageArtTitleText.text = data.artTitle
            binding.activityNewStorageLikedNumberText.text = data.artLikeNumber

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)

                }
            }
        }

    }
}