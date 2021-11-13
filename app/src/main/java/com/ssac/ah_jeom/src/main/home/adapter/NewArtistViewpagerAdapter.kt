package com.ssac.ah_jeom.src.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.databinding.FragmentHomeNewArtistItemBinding
import com.ssac.ah_jeom.src.main.home.models.NewArtist

class NewArtistViewpagerAdapter : RecyclerView.Adapter<NewArtistViewpagerAdapter.PagerViewHolder>() {

    var listData = mutableListOf<NewArtist>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = FragmentHomeNewArtistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val newArtistName = listData[position].newArtistName
        val newArtistProfileImage = listData[position].newArtistProfileImage

        with(holder) {
//            setName(newArtistName)
            setProfileImage(newArtistProfileImage)
        }
    }

    inner class PagerViewHolder(val binding: FragmentHomeNewArtistItemBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun setName(newArtistName: String) {
//            binding.fragmentHomeNewArtistProfileNameText.text = newArtistName
//        }
        fun setProfileImage(newArtistProfileImage: String) {
            Glide.with(itemView).load(newArtistProfileImage).into(binding.fragmentHomeNewArtistProfileImage)
        }
    }
}