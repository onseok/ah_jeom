package com.ssac.ah_jeom.src.main.subscribe

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.FragmentSubscribeBinding
import com.ssac.ah_jeom.src.main.home.adapter.BestArtistViewpagerAdapter
import com.ssac.ah_jeom.src.main.home.models.BestArtist
import com.ssac.ah_jeom.src.main.home.models.GetHomeResponse
import com.ssac.ah_jeom.src.main.subscribe.adapter.SubscribeIllustrationViewpagerAdapter
import com.ssac.ah_jeom.src.main.subscribe.adapter.SubscribeMainViewpagerAdapter
import com.ssac.ah_jeom.src.main.subscribe.models.SubscribeImageData

class SubscribeFragment : Fragment() {

    private var binding: FragmentSubscribeBinding? = null

    private val imageData: MutableList<SubscribeImageData> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubscribeBinding.inflate(inflater, container, false)

        binding!!.fragmentSubscribeIllustrationViewPager.adapter = SubscribeIllustrationViewpagerAdapter(getImageList()) // 어댑터 생성
        binding!!.fragmentSubscribeIllustrationViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로

        setSubscribeMainViewpager()

        return binding!!.root
    }

    // 뷰 페이저에 들어갈 아이템
    private fun getImageList(): ArrayList<Int> {
        return arrayListOf(R.drawable.best_artist_illustration_image, R.drawable.recently_artist_illustration_image, R.drawable.soaring_artist_illustration_image)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setSubscribeMainViewpager() {
        // 구독 프래그먼트 메인 뷰페이저
        val subscribeImageData: MutableList<SubscribeImageData> = loadData()
        var subscribeImageAdapter = SubscribeMainViewpagerAdapter()
        subscribeImageAdapter.listData = subscribeImageData
        binding?.fragmentSubscribeMainViewpager?.adapter = subscribeImageAdapter

        // 뷰페이저 인디케이터
        val dotsIndicator = binding?.fragmentSubscribeMainIndicator
        val viewPager = binding?.fragmentSubscribeMainViewpager
        viewPager?.adapter = subscribeImageAdapter
        dotsIndicator?.setViewPager2(viewPager!!)
    }

    private fun loadData(): MutableList<SubscribeImageData> {

        imageData.add(SubscribeImageData(R.drawable.subscribe_main_viewpager_image_temp))
        imageData.add(SubscribeImageData(R.drawable.subscribe_main_viewpager_image_2_temp))
        imageData.add(SubscribeImageData(R.drawable.subscribe_main_viewpager_image_3_temp))
        imageData.add(SubscribeImageData(R.drawable.subscribe_main_viewpager_image_4_temp))
        imageData.add(SubscribeImageData(R.drawable.subscribe_main_viewpager_image_5_temp))
        imageData.add(SubscribeImageData(R.drawable.subscribe_main_viewpager_image_6_temp))

        return imageData
    }

}