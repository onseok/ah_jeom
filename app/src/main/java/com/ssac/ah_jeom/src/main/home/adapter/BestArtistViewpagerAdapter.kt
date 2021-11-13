package com.ssac.ah_jeom.src.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.databinding.FragmentHomeBestArtistItemBinding
import com.ssac.ah_jeom.src.main.home.models.BestArtist

class BestArtistViewpagerAdapter : RecyclerView.Adapter<BestArtistViewpagerAdapter.PagerViewHolder>() {

    var listData = mutableListOf<BestArtist>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = FragmentHomeBestArtistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        binding.fragmentHomeBestArtistProfileButton.setOnClickListener {
            binding.fragmentHomeBestArtistProfileText.visibility = View.VISIBLE
        }

        return PagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val bestArtistName = listData[position].name
        val bestArtistProfileImage = listData[position].profileImage

        with(holder) {
            setName(bestArtistName)
            setProfileImage(bestArtistProfileImage)
        }
    }

    inner class PagerViewHolder(val binding: FragmentHomeBestArtistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setName(newArtistName: String) {
            binding.fragmentHomeBestArtistProfileText.text = newArtistName
        }

        fun setProfileImage(bestArtistProfileImage: String) {
            Glide.with(itemView).load(bestArtistProfileImage).into(binding.fragmentHomeBestArtistProfileImage)
        }
    }
}