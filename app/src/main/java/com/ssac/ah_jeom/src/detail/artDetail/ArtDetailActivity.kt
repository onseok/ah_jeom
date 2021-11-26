package com.ssac.ah_jeom.src.detail.artDetail

import android.os.Bundle
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityArtDetailBinding

class ArtDetailActivity : BaseActivity<ActivityArtDetailBinding>(ActivityArtDetailBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

}