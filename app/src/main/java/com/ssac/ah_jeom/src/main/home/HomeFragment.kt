package com.ssac.ah_jeom.src.main.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseFragment
import com.ssac.ah_jeom.databinding.FragmentHomeBinding
import com.ssac.ah_jeom.src.main.home.adapter.HomeFragmentViewpagerAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home){

    private var numBanner = 3
    private var currentPosition = 2
    private var myHandler = MyHandler()
    private val intervalTime = 5000.toLong() // 몇초 간격으로 페이지를 넘길것인지 (5000 = 5.0초)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentHomeBannerViewpager.adapter = HomeFragmentViewpagerAdapter(getBannerList())
        binding.fragmentHomeBannerViewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.fragmentHomeBannerViewpager.setCurrentItem(currentPosition, false) // 현재 위치를 지정

        // 현재 몇 번째 배너인지 보여주는 숫자를 변경함
        val apply = binding.fragmentHomeBannerViewpager.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    when (state) {
                        // 뷰페이저에서 손 떼었을때 / 뷰페이저 멈춰있을 때
                        ViewPager2.SCROLL_STATE_IDLE -> autoScrollStart(intervalTime)

                        // 뷰페이저 움직이는 중
                        ViewPager2.SCROLL_STATE_DRAGGING -> autoScrollStop()
                    }
                }

            })
        }

        binding.fragmentHomeBannerViewpager.setCurrentItem(++currentPosition, true)

    }

    private fun getBannerList(): ArrayList<Int> {
        return arrayListOf(R.drawable.banner_1_temp, R.drawable.banner_2_temp, R.drawable.banner_3_temp)
    }

    private fun autoScrollStart(intervalTime: Long) {
        myHandler.removeMessages(0) // 이거 안하면 핸들러가 1개, 2개, 3개 ... n개 만큼 계속 늘어남
        myHandler.sendEmptyMessageDelayed(0, intervalTime) // intervalTime 만큼 반복해서 핸들러를 실행하게 함
    }

    private fun autoScrollStop() {
        myHandler.removeMessages(0) // 핸들러를 중지시킴
    }

    private inner class MyHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            if (msg.what == 0) {
                binding.fragmentHomeBannerViewpager.setCurrentItem(++currentPosition, true) // 다음 페이지로 이동
                autoScrollStart(intervalTime) // 스크롤을 계속 이어서 한다.
            }
        }
    }
}