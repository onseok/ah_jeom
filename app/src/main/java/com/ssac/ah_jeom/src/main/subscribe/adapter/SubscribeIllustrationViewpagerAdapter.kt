package com.ssac.ah_jeom.src.main.subscribe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ssac.ah_jeom.R

class SubscribeIllustrationViewpagerAdapter(imageList: ArrayList<Int>) : RecyclerView.Adapter<SubscribeIllustrationViewpagerAdapter.PagerViewHolder>() {

    var item = imageList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.image.setImageResource(item[position])
    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.subscribe_character_image_list_item, parent, false)){

        val image = itemView.findViewById<ImageView>(R.id.subscribe_character_image_view)!!
    }
}