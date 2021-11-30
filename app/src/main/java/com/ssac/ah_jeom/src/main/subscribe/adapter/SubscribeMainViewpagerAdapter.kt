package com.ssac.ah_jeom.src.main.subscribe.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.FragmentSubscribeMainViewpagerItemBinding
import com.ssac.ah_jeom.src.detail.artistDetail.ArtistDetailActivity
import com.ssac.ah_jeom.src.main.subscribe.bestArtist.BestArtistActivity
import com.ssac.ah_jeom.src.main.subscribe.bestArtist.adapter.BestArtistRecyclerAdapter
import com.ssac.ah_jeom.src.main.subscribe.models.SubscribeImageData

class SubscribeMainViewpagerAdapter : RecyclerView.Adapter<SubscribeMainViewpagerAdapter.PagerViewHolder>() {

    var listData = mutableListOf<SubscribeImageData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = FragmentSubscribeMainViewpagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return PagerViewHolder(binding)
    }

    private var listener: BestArtistRecyclerAdapter.OnItemClickListener? = null

    fun setOnItemClickListener(listener: BestArtistRecyclerAdapter.OnItemClickListener) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val subscribeImage = listData[position].subscribeImage

        holder.setProfileImage(subscribeImage)

    }

    inner class PagerViewHolder(val binding: FragmentSubscribeMainViewpagerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setProfileImage(subscribeImage: String) {
            Glide.with(itemView).load(subscribeImage).into(binding.fragmentSubscribeMainViewpagerImage)

//            val pos = adapterPosition
//            if (pos != RecyclerView.NO_POSITION) {
//                itemView.setOnClickListener {
//                    listener?.onItemClick(itemView, data, pos)
//
//                    for (i in 0 until response.result.best.size) {
//                        if (pos == i) {
//                            val intent = Intent(itemView.context, ArtistDetailActivity::class.java)
//                            intent.putExtra("artistId", response.result.best[pos].userId)
//                            itemView.context.startActivity(intent)
//                            (itemView.context as BestArtistActivity).overridePendingTransition(
//                                R.anim.activity_fade_in,
//                                R.anim.activity_fade_out
//                            )
//                        }
//                    }
//
//                }
//            }

        }

    }
}