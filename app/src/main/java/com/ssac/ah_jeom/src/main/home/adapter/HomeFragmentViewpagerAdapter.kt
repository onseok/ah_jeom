package com.ssac.ah_jeom.src.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ssac.ah_jeom.R

class HomeFragmentViewpagerAdapter(bannerList: ArrayList<Int>) : RecyclerView.Adapter<HomeFragmentViewpagerAdapter.PagerViewHolder>() {

    var item = bannerList

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    } // 아이템의 갯수를 임의로 확 늘린다. 마치 무한으로 스크롤 되는 것처럼

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.banner.setImageResource(item[position%3])
    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_home_banner_item, parent, false)) {
        val banner: ImageView = itemView.findViewById(R.id.fragment_home_banner_item_image)
    }


}