package com.ssac.ah_jeom.src.main.peek.newStorage.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.ActivityNewStorageRecyclerItemBinding
import com.ssac.ah_jeom.src.detail.storageDetail.StorageDetailActivity
import com.ssac.ah_jeom.src.main.peek.newStorage.NewStorageActivity
import com.ssac.ah_jeom.src.main.peek.newStorage.models.GetNewStorageResponse
import com.ssac.ah_jeom.src.main.peek.newStorage.models.NewStorageRecyclerData

class NewStorageRecyclerAdapter(private val context: Context, response: GetNewStorageResponse) :
    RecyclerView.Adapter<NewStorageRecyclerAdapter.PagerViewHolder>() {

    var listData = mutableListOf<NewStorageRecyclerData>()

    val response = response

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

            Glide.with(itemView.context).load(data.profileImage).circleCrop().into(binding.activityNewStorageProfileImage)
            binding.activityNewStorageProfileNameText.text = data.profileName
            binding.activityNewStorageDownloadNumberText.text = data.downloadNumber
            Glide.with(itemView.context).load(data.image).into(binding.activityNewStorageMainImage)
            binding.activityNewStorageArtTitleText.text = data.artTitle
            binding.activityNewStorageLikedNumberText.text = data.artLikeNumber

            if(data.isLiked) {
                binding.activityNewStorageLikeButtonTrue.visibility = View.VISIBLE
                binding.activityNewStorageLikeButton.visibility = View.GONE
            }
            else if (!data.isLiked) {
                binding.activityNewStorageLikeButtonTrue.visibility = View.GONE
                binding.activityNewStorageLikeButton.visibility = View.VISIBLE
            }

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)

                    for (i in 0 until response.result.new.size) {
                        if (pos == i) {
                            val intent = Intent(itemView.context, StorageDetailActivity::class.java)
                            intent.putExtra("storageId", response.result.new[pos].storageId)
                            itemView.context.startActivity(intent)
                            (itemView.context as NewStorageActivity).overridePendingTransition(
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