package com.ssac.ah_jeom.src.profile.settings.manual.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.databinding.ActivityManualViewpagerItemBinding
import com.ssac.ah_jeom.src.profile.settings.manual.models.ManualViewpagerData

class ManualViewpagerAdapter : RecyclerView.Adapter<ManualViewpagerAdapter.PagerViewHolder>() {

    var listData = mutableListOf<ManualViewpagerData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = ActivityManualViewpagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val title = listData[position].title
        val image = listData[position].image
        val explanation = listData[position].explanation

        with(holder) {
            setTitle(title)
            setImage(image)
            setExplanation(explanation)
        }
    }

    inner class PagerViewHolder(val binding: ActivityManualViewpagerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setTitle(title: String) {
            binding.activityManualViewPagerTitle.text = title
        }

        fun setImage(image: Int) {
            Glide.with(itemView).load(image).into(binding.activityManualViewPagerImage)
        }

        fun setExplanation(explanation: String) {
            binding.activityManualViewPagerExplanation.text = explanation
        }
    }
}