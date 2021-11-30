package com.ssac.ah_jeom.src.detail.artistDetail.artistReview

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityArtistReviewBinding
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.adapter.ArtistReviewRecyclerAdapter
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.ArtistReviewRecyclerData
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.GetArtistReviewResponse

class ArtistReviewActivity : BaseActivity<ActivityArtistReviewBinding>(ActivityArtistReviewBinding::inflate), ArtistReviewActivityView {

    private val data: MutableList<ArtistReviewRecyclerData> = mutableListOf()

    private var cursor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityArtistReviewBackButton.setOnClickListener {
            onBackPressed()
        }

        val artistId = intent.getIntExtra("artistId", 0)

        ArtistReviewService(this).tryGetArtistReview(artistId, cursor)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    private fun setArtistReviewRecyclerView(response: GetArtistReviewResponse) {
        val data: MutableList<ArtistReviewRecyclerData> = data
        var adapter = ArtistReviewRecyclerAdapter(this, response)
        adapter.listData = data
        binding.activityArtistReviewRecyclerView.adapter = adapter
        binding.activityArtistReviewRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()
    }

    override fun onGetArtistReviewSuccess(response: GetArtistReviewResponse) {
        if (response.isSuccess) {
            response.result.review.forEach {
                data.add(ArtistReviewRecyclerData(it.profile, it.nickname, it.caption))
            }
            setArtistReviewRecyclerView(response)
        }

    }

    override fun onGetArtistReviewFailure(message: String) {
        showCustomToast("오류 : $message")
    }

}