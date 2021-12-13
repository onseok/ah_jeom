package com.ssac.ah_jeom.src.detail.artistDetail.artistReview

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityArtistReviewBinding
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.adapter.ArtistReviewRecyclerAdapter
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.ArtistReviewRecyclerData
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.GetArtistReviewResponse
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.PostReportReviewResponse
import com.ssac.ah_jeom.src.detail.reviewDetail.ReviewDetailActivity

class ArtistReviewActivity : BaseActivity<ActivityArtistReviewBinding>(ActivityArtistReviewBinding::inflate), ArtistReviewActivityView {

    private val data: MutableList<ArtistReviewRecyclerData> = mutableListOf()

    private var globalArtistId = 0

    private var cursor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val artistId = intent.getIntExtra("artistId", 0)

        globalArtistId = artistId

        binding.activityArtistReviewBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.activityArtistReviewFloatingButton.setOnClickListener {
            val intent = Intent(this, ReviewDetailActivity::class.java)
            intent.putExtra("artistId", artistId)
            startActivity(intent)
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

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
        if (!response.isSuccess) {
            binding.activityArtistReviewNoItemText.visibility = View.VISIBLE
            binding.activityArtistReviewRecyclerView.visibility = View.GONE
        }
        else {
            binding.activityArtistReviewNoItemText.visibility = View.INVISIBLE
            binding.activityArtistReviewRecyclerView.visibility = View.VISIBLE

            data.clear()
            response.result.review.forEach {
                data.add(ArtistReviewRecyclerData(it.profile, it.nickname, it.caption))
            }
            setArtistReviewRecyclerView(response)
        }

    }

    override fun onGetArtistReviewFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onResume() {
        super.onResume()
        ArtistReviewService(this).tryGetArtistReview(globalArtistId, cursor)
    }

}