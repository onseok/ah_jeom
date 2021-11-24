package com.ssac.ah_jeom.src.detail.artistDetail.artistReview

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityArtistReviewBinding
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.adapter.ArtistReviewRecyclerAdapter
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.ArtistReviewRecyclerData

class ArtistReviewActivity : BaseActivity<ActivityArtistReviewBinding>(ActivityArtistReviewBinding::inflate) {

    private val data: MutableList<ArtistReviewRecyclerData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data: MutableList<ArtistReviewRecyclerData> = loadData()
        var adapter = ArtistReviewRecyclerAdapter(this)
        adapter.listData = data
        binding.activityArtistReviewRecyclerView.adapter = adapter
        binding.activityArtistReviewRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()

        binding.activityArtistReviewBackButton.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    // 추후 API 연동 예정
    private fun loadData(): MutableList<ArtistReviewRecyclerData> {

        data.add(ArtistReviewRecyclerData(R.drawable.review_profile_image_1_temp, "김원석", "구매했는데 실물도 그렇고 너무 만족스럽습니다. 다만 작가분이 포장해주실 때 조금 더 꼼꼼하게 포장해주셨으면 더 좋았을 것 같습니다 ㅎㅎ"))
        data.add(ArtistReviewRecyclerData(R.drawable.review_profile_image_2_temp, "김유영", "구매했는데 실물도 그렇고 너무 만족스럽습니다. 다만 작가분이 포장해주실 때 조금 더 꼼꼼하게 포장해주셨으면 더 좋았을 것 같습니다 ㅎㅎ"))
        data.add(ArtistReviewRecyclerData(R.drawable.review_profile_image_3_temp, "신지인", "구매했는데 실물도 그렇고 너무 만족스럽습니다. 다만 작가분이 포장해주실 때 조금 더 꼼꼼하게 포장해주셨으면 더 좋았을 것 같습니다 ㅎㅎ"))

        return data
    }

}