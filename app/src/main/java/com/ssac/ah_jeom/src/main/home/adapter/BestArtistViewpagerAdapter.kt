package com.ssac.ah_jeom.src.main.home.adapter

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.FragmentHomeBestArtistItemBinding
import com.ssac.ah_jeom.src.main.home.models.BestArtist

class BestArtistViewpagerAdapter : RecyclerView.Adapter<BestArtistViewpagerAdapter.PagerViewHolder>() {

    var listData = mutableListOf<BestArtist>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = FragmentHomeBestArtistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val blackBackgroundOpen: Animation by lazy {
            AnimationUtils.loadAnimation(
                parent.context,
                R.anim.black_background_open
            )
        }

        val blackBackgroundClose: Animation by lazy {
            AnimationUtils.loadAnimation(
                parent.context,
                R.anim.black_background_close
            )
        }

        val profileButtonAppear: Animation by lazy {
            AnimationUtils.loadAnimation(
                parent.context,
                R.anim.profile_button_appear
            )
        }

        val profileButtonDisappear: Animation by lazy {
            AnimationUtils.loadAnimation(
                parent.context,
                R.anim.profile_button_disappear
            )
        }

        binding.fragmentHomeBestArtistProfileButton.setOnClickListener {

            binding.fragmentHomeBestArtistProfileText.visibility = View.VISIBLE
            binding.fragmentHomeBestArtistBlackBackground.visibility = View.VISIBLE
            binding.fragmentHomeBestArtistProfileButton.visibility = View.GONE
            binding.fragmentHomeBestArtistProfileText.startAnimation(blackBackgroundOpen)
            binding.fragmentHomeBestArtistBlackBackground.startAnimation(blackBackgroundOpen)
            binding.fragmentHomeBestArtistProfileButton.startAnimation(profileButtonDisappear)

            Handler(Looper.getMainLooper()).postDelayed({
                binding.fragmentHomeBestArtistProfileText.visibility = View.INVISIBLE
                binding.fragmentHomeBestArtistBlackBackground.visibility = View.INVISIBLE
                binding.fragmentHomeBestArtistProfileButton.visibility = View.VISIBLE
                binding.fragmentHomeBestArtistProfileText.startAnimation(blackBackgroundClose)
                binding.fragmentHomeBestArtistBlackBackground.startAnimation(blackBackgroundClose)
                binding.fragmentHomeBestArtistProfileButton.startAnimation(profileButtonAppear)
            }, 3500)

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