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
import com.ssac.ah_jeom.databinding.FragmentHomeNewArtistItemBinding
import com.ssac.ah_jeom.src.main.home.models.NewArtist

class NewArtistViewpagerAdapter :
    RecyclerView.Adapter<NewArtistViewpagerAdapter.PagerViewHolder>() {

    var listData = mutableListOf<NewArtist>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = FragmentHomeNewArtistItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

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

        binding.fragmentHomeNewArtistProfileButton.setOnClickListener {

            binding.fragmentHomeNewArtistProfileText.visibility = View.VISIBLE
            binding.fragmentHomeNewArtistBlackBackground.visibility = View.VISIBLE
            binding.fragmentHomeNewArtistProfileButton.visibility = View.GONE
            binding.fragmentHomeNewArtistProfileText.startAnimation(blackBackgroundOpen)
            binding.fragmentHomeNewArtistBlackBackground.startAnimation(blackBackgroundOpen)
            binding.fragmentHomeNewArtistProfileButton.startAnimation(profileButtonDisappear)

            Handler(Looper.getMainLooper()).postDelayed({
                binding.fragmentHomeNewArtistProfileText.visibility = View.INVISIBLE
                binding.fragmentHomeNewArtistBlackBackground.visibility = View.INVISIBLE
                binding.fragmentHomeNewArtistProfileButton.visibility = View.VISIBLE
                binding.fragmentHomeNewArtistProfileText.startAnimation(blackBackgroundClose)
                binding.fragmentHomeNewArtistBlackBackground.startAnimation(blackBackgroundClose)
                binding.fragmentHomeNewArtistProfileButton.startAnimation(profileButtonAppear)
            }, 3500)

        }

        return PagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val newArtistName = listData[position].newArtistName
        val newArtistProfileImage = listData[position].newArtistProfileImage

        with(holder) {
            setName(newArtistName)
            setProfileImage(newArtistProfileImage)
        }
    }

    inner class PagerViewHolder(val binding: FragmentHomeNewArtistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setName(newArtistName: String) {
            binding.fragmentHomeNewArtistProfileText.text = newArtistName
        }

        fun setProfileImage(newArtistProfileImage: String) {
            Glide.with(itemView).load(newArtistProfileImage)
                .into(binding.fragmentHomeNewArtistProfileImage)
        }
    }
}