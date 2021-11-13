package com.ssac.ah_jeom.src.main.home.fragments

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseFragment
import com.ssac.ah_jeom.databinding.FragmentKeywordVividBinding

class KeywordVividFragment(private val imageUrl: String) :
    BaseFragment<FragmentKeywordVividBinding>(FragmentKeywordVividBinding::bind, R.layout.fragment_keyword_vivid) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(imageUrl).into(binding.fragmentKeywordVividImage)

    }
}