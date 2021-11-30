package com.ssac.ah_jeom.src.profile.myArt.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.ActivityMyArtRecyclerItemBinding
import com.ssac.ah_jeom.src.detail.artDetail.ArtDetailActivity
import com.ssac.ah_jeom.src.profile.myArt.MyArtActivity
import com.ssac.ah_jeom.src.profile.myArt.models.GetMyArtResponse
import com.ssac.ah_jeom.src.profile.myArt.models.MyArtRecyclerData

class MyArtRecyclerAdapter(private val context: Context, response: GetMyArtResponse) :
    RecyclerView.Adapter<MyArtRecyclerAdapter.PagerViewHolder>() {

    var listData = mutableListOf<MyArtRecyclerData>()

    val response = response

    interface OnItemClickListener {
        fun onItemClick(v: View, data: MyArtRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyArtRecyclerAdapter.PagerViewHolder {
        val binding = ActivityMyArtRecyclerItemBinding.inflate(
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

    inner class PagerViewHolder(val binding: ActivityMyArtRecyclerItemBinding) :
        RecyclerView.ViewHolder
            (binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }

        fun setData(data: MyArtRecyclerData) {

            Glide.with(itemView.context).load(data.image).into(binding.activityMyArtRecyclerImage)

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)

                    for (i in 0 until response.result.artwork.size) {
                        if (pos == i) {
                            val intent = Intent(itemView.context, ArtDetailActivity::class.java)
                            intent.putExtra("artId", response.result.artwork[pos].artId)
                            itemView.context.startActivity(intent)
                            (itemView.context as MyArtActivity).overridePendingTransition(
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