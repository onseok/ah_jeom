package com.ssac.ah_jeom.src.main.peek.bestStorage.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.ActivityBestStorageRecyclerItemBinding
import com.ssac.ah_jeom.src.detail.storageDetail.StorageDetailActivity
import com.ssac.ah_jeom.src.main.peek.bestStorage.BestStorageActivity
import com.ssac.ah_jeom.src.main.peek.bestStorage.models.BestStorageRecyclerData
import com.ssac.ah_jeom.src.main.peek.bestStorage.models.GetBestStorageResponse

class BestStorageRecyclerAdapter(private val context: Context, response: GetBestStorageResponse) :
    RecyclerView.Adapter<BestStorageRecyclerAdapter.PagerViewHolder>() {

    var listData = mutableListOf<BestStorageRecyclerData>()

    val response = response

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
            Glide.with(itemView.context).load(data.profileImage).circleCrop().into(binding.activityBestStorageProfileImage)
            binding.activityBestStorageProfileNameText.text = data.profileName
            binding.activityBestStorageDownloadNumberText.text = data.downloadNumber
            Glide.with(itemView.context).load(data.image).into(binding.activityBestStorageMainImage)
            binding.activityBestStorageArtTitleText.text = data.artTitle
            binding.activityBestStorageLikedNumberText.text = data.artLikeNumber

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)

                    for (i in 0 until response.result.best.size) {
                        if (pos == i) {
                            val intent = Intent(itemView.context, StorageDetailActivity::class.java)
                            intent.putExtra("storageId", response.result.best[pos].storageId)
                            itemView.context.startActivity(intent)
                            (itemView.context as BestStorageActivity).overridePendingTransition(
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