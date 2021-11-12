package com.ssac.ah_jeom.src.main.home.fragments

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseFragment
import com.ssac.ah_jeom.databinding.FragmentArchitectureBinding

class ArchitectureFragment(private val imageUrl: String) :
    BaseFragment<FragmentArchitectureBinding>(FragmentArchitectureBinding::bind, R.layout.fragment_architecture) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(imageUrl).into(binding.fragmentArchitectureImage)

    }
}