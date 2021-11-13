package com.ssac.ah_jeom.src.main.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseFragment
import com.ssac.ah_jeom.databinding.FragmentKeywordBeautifulBinding
import com.ssac.ah_jeom.databinding.FragmentKeywordHolyBinding

class KeywordHolyFragment(private val imageUrl: String) :
    BaseFragment<FragmentKeywordHolyBinding>(FragmentKeywordHolyBinding::bind, R.layout.fragment_keyword_holy) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(imageUrl).into(binding.fragmentKeywordHolyImage)

    }
}