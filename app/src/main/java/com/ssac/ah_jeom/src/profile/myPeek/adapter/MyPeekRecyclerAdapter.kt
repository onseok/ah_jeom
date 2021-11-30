package com.ssac.ah_jeom.src.profile.myPeek.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.ActivityMyPeekRecyclerItemBinding
import com.ssac.ah_jeom.databinding.ActivityMyStorageRecyclerItemBinding
import com.ssac.ah_jeom.src.detail.storageDetail.StorageDetailActivity
import com.ssac.ah_jeom.src.profile.myPeek.MyPeekActivity
import com.ssac.ah_jeom.src.profile.myPeek.models.GetMyPeekResponse
import com.ssac.ah_jeom.src.profile.myPeek.models.MyPeekRecyclerData
import com.ssac.ah_jeom.src.profile.myStorage.MyStorageActivity
import com.ssac.ah_jeom.src.profile.myStorage.models.MyStorageRecyclerData

class MyPeekRecyclerAdapter(private val context: Context, response: GetMyPeekResponse) :
    RecyclerView.Adapter<MyPeekRecyclerAdapter.PagerViewHolder>() {

    var listData = mutableListOf<MyPeekRecyclerData>()

    val response = response

    interface OnItemClickListener {
        fun onItemClick(v: View, data: MyPeekRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyPeekRecyclerAdapter.PagerViewHolder {
        val binding = ActivityMyPeekRecyclerItemBinding.inflate(
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

    inner class PagerViewHolder(val binding: ActivityMyPeekRecyclerItemBinding) :
        RecyclerView.ViewHolder
            (binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }

        fun setData(data: MyPeekRecyclerData) {

            Glide.with(itemView.context).load(data.image).into(binding.activityMyPeekRecyclerImage)
            binding.activityMyPeekRecyclerTitle.text = data.title
            binding.activityMyPeekRecyclerName.text = data.nickname
            binding.activityMyPeekRecyclerDownload.text = data.download

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)

                    for (i in 0 until response.result.save.size) {
                        if (pos == i) {
                            val intent = Intent(itemView.context, StorageDetailActivity::class.java)
                            intent.putExtra("storageId", response.result.save[pos].storageId)
                            itemView.context.startActivity(intent)
                            (itemView.context as MyPeekActivity).overridePendingTransition(
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