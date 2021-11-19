package com.ssac.ah_jeom.src.main.subscribe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.databinding.FragmentSubscribeMainViewpagerItemBinding
import com.ssac.ah_jeom.src.main.subscribe.models.SubscribeImageData

class SubscribeMainViewpagerAdapter : RecyclerView.Adapter<SubscribeMainViewpagerAdapter.PagerViewHolder>() {

    var listData = mutableListOf<SubscribeImageData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = FragmentSubscribeMainViewpagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return PagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val subscribeImage = listData[position].subscribeImage


        holder.setProfileImage(subscribeImage)

    }

    inner class PagerViewHolder(val binding: FragmentSubscribeMainViewpagerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        // TODO 추후에 int가 아닌 string으로 변경해야함 (서버에서 받은 이미지 url)
        fun setProfileImage(subscribeImage: Int) {
            Glide.with(itemView).load(subscribeImage).into(binding.fragmentSubscribeMainViewpagerImage)
        }

    }
}