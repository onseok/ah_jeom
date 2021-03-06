package com.ssac.ah_jeom.src.main.subscribe.soaringArtist

import android.os.Bundle
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivitySoaringArtistBinding

class SoaringArtistActivity : BaseActivity<ActivitySoaringArtistBinding>(
    ActivitySoaringArtistBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activitySoaringBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

}