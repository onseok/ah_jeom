package com.ssac.ah_jeom.src.search

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivitySearchBinding
import com.ssac.ah_jeom.src.search.adapter.RecommendedKeywordRecyclerAdapter
import com.ssac.ah_jeom.src.search.adapter.RelatedImageRecyclerAdapter
import com.ssac.ah_jeom.src.search.models.RecommendedKeywordRecyclerData
import com.ssac.ah_jeom.src.search.models.RelatedImageRecyclerData

class SearchActivity : BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate) {

    private val keywordData: MutableList<RecommendedKeywordRecyclerData> = mutableListOf()
    val data: MutableList<RelatedImageRecyclerData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 추천 키워드 설정
        setRecommendedKeywordView()
        
        // 관련 이미지 설정
        setRelatedImageView()
        
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    private fun setRecommendedKeywordView() {
        val data: MutableList<RecommendedKeywordRecyclerData> = keywordData
        var adapter = RecommendedKeywordRecyclerAdapter(this)
        adapter.listData = data
        binding.activitySearchRecommendedSearchKeywordRecyclerView.adapter = adapter
        binding.activitySearchRecommendedSearchKeywordRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapter.notifyDataSetChanged()

        setRecommendedKeywordData()
    }

    // 추후 api연동 예정
    private fun setRecommendedKeywordData() {
        keywordData.add(RecommendedKeywordRecyclerData("아 뭐였지"))
        keywordData.add(RecommendedKeywordRecyclerData("아티스트"))
        keywordData.add(RecommendedKeywordRecyclerData("빈센트 반 고흐"))
        keywordData.add(RecommendedKeywordRecyclerData("그린빈 삶의..."))
    }


    private fun setRelatedImageView() {
        val data: MutableList<RelatedImageRecyclerData> = data
        var adapter = RelatedImageRecyclerAdapter(this)
        adapter.listData = data
        binding.activitySearchRelatedImageRecyclerView.adapter = adapter
        binding.activitySearchRelatedImageRecyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()

        setRelatedImageData()
    }

    // 추후 api연동 예정
    private fun setRelatedImageData() {
        data.add(RelatedImageRecyclerData(R.drawable.search_image_1_temp))
        data.add(RelatedImageRecyclerData(R.drawable.search_image_2_temp))
        data.add(RelatedImageRecyclerData(R.drawable.search_image_3_temp))
        data.add(RelatedImageRecyclerData(R.drawable.search_image_4_temp))
        data.add(RelatedImageRecyclerData(R.drawable.search_image_5_temp))
        data.add(RelatedImageRecyclerData(R.drawable.search_image_6_temp))
        data.add(RelatedImageRecyclerData(R.drawable.search_image_7_temp))
        data.add(RelatedImageRecyclerData(R.drawable.search_image_8_temp))
    }
}