package com.ssac.ah_jeom.src.profile.settings.personalPrivacy

import android.os.Bundle
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityPersonalPrivacyBinding

class PersonalPrivacyActivity : BaseActivity<ActivityPersonalPrivacyBinding>(ActivityPersonalPrivacyBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityPersonalPrivacyBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }
}