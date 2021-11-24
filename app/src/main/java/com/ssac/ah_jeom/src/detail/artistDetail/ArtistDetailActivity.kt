package com.ssac.ah_jeom.src.detail.artistDetail

import android.content.Intent
import android.os.Bundle
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityArtistDetailBinding
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.ArtistReviewActivity

class ArtistDetailActivity : BaseActivity<ActivityArtistDetailBinding>(ActivityArtistDetailBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityArtistDetailBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.activityArtistDetailReviewButton.setOnClickListener {
            startActivity(Intent(this, ArtistReviewActivity::class.java))
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

}