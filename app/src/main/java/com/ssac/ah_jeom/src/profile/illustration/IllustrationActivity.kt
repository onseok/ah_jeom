package com.ssac.ah_jeom.src.profile.illustration

import android.content.Intent
import android.os.Bundle
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityIllustrationBinding
import com.ssac.ah_jeom.src.profile.illustration.illustrationDetail.IllustrationDetailActivity


class IllustrationActivity : BaseActivity<ActivityIllustrationBinding>(ActivityIllustrationBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityIllustrationBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.activityIllustrationBottomClickLayout.setOnClickListener {
            startActivity(Intent(this, IllustrationDetailActivity::class.java))
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }
}