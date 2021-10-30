package com.ssac.ah_jeom.src.main.myPage

import android.os.Bundle
import android.view.View
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseFragment
import com.ssac.ah_jeom.databinding.FragmentMyPageBinding

class MyPageFragment :
    BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {
    private var mCount = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}