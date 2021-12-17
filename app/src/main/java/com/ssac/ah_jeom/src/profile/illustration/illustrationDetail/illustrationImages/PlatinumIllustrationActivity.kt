package com.ssac.ah_jeom.src.profile.illustration.illustrationDetail.illustrationImages

import android.os.Bundle
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityPlatinumIllustrationBinding

class PlatinumIllustrationActivity : BaseActivity<ActivityPlatinumIllustrationBinding>(ActivityPlatinumIllustrationBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityPlatinumIllustrationBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }
}