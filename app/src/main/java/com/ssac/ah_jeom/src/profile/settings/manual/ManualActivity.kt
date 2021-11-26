package com.ssac.ah_jeom.src.profile.settings.manual

import android.os.Bundle
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityManualBinding
import com.ssac.ah_jeom.src.main.home.adapter.NewArtistViewpagerAdapter
import com.ssac.ah_jeom.src.main.home.models.BestArtist
import com.ssac.ah_jeom.src.main.home.models.GetHomeResponse
import com.ssac.ah_jeom.src.main.home.models.NewArtist
import com.ssac.ah_jeom.src.profile.settings.manual.adapter.ManualViewpagerAdapter
import com.ssac.ah_jeom.src.profile.settings.manual.models.ManualViewpagerData

class ManualActivity : BaseActivity<ActivityManualBinding>(ActivityManualBinding::inflate) {

    private val data: MutableList<ManualViewpagerData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityManualBackButton.setOnClickListener {
            onBackPressed()
        }

        setManualViewpager()
    }

    private fun setManualViewpager() {
        // 아제옴 이용법 뷰페이저
        val manualData: MutableList<ManualViewpagerData> = loadData()
        var manualAdapter = ManualViewpagerAdapter()
        manualAdapter.listData = manualData
        binding.activityManualViewPager.adapter = manualAdapter

        // 뷰페이저 인디케이터
        val dotsIndicator = binding.activityManualDotsIndicator
        val viewPager = binding.activityManualViewPager
        viewPager.adapter = manualAdapter
        dotsIndicator.setViewPager2(viewPager)
    }


    private fun loadData(): MutableList<ManualViewpagerData> {

        data.add(ManualViewpagerData("상업적 이용 불가", R.drawable.ic_manual_first, "아제옴의 모든 이미지 다운로드는\n무료이지만 상업적 이용이 불가합니다."))
        data.add(ManualViewpagerData("작품 게시 시 개인이용 동의", R.drawable.ic_manual_second, "모든 작업물 게시의 대표 이미지는 모든\n유저의 개인이용 동의로 간주합니다."))
        data.add(ManualViewpagerData("결제/작가 비동의시 법적 책임", R.drawable.ic_manual_third, "결제/작가 비동의 작품의 상업적 이용으로 인한\n 모든 법적 책임은 이용자 개인에게 있습니다."))

        return data

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }
}