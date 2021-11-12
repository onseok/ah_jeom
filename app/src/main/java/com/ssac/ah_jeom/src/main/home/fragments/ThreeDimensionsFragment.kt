package com.ssac.ah_jeom.src.main.home.fragments

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseFragment
import com.ssac.ah_jeom.databinding.FragmentThreeDimensionsBinding

class ThreeDimensionsFragment(private val imageUrl: String) :
    BaseFragment<FragmentThreeDimensionsBinding>(FragmentThreeDimensionsBinding::bind, R.layout.fragment_three_dimensions) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(imageUrl).into(binding.fragmentThreeDimensionImage)

    }
}