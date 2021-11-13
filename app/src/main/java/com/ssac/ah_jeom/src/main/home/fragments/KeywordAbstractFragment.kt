package com.ssac.ah_jeom.src.main.home.fragments

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseFragment
import com.ssac.ah_jeom.databinding.FragmentKeywordAbstractBinding

class KeywordAbstractFragment(private val imageUrl: String) :
    BaseFragment<FragmentKeywordAbstractBinding>(FragmentKeywordAbstractBinding::bind, R.layout.fragment_keyword_abstract) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(imageUrl).into(binding.fragmentKeywordAbstractImage)

    }
}