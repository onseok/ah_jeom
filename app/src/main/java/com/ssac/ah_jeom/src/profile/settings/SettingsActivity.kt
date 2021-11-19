package com.ssac.ah_jeom.src.profile.settings

import android.content.Intent
import android.os.Bundle
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivitySettingsBinding
import com.ssac.ah_jeom.src.profile.settings.changeImage.ChangeImageActivity
import com.ssac.ah_jeom.src.profile.settings.changeName.ChangeNameActivity
import com.ssac.ah_jeom.src.profile.settings.manual.ManualActivity

class SettingsActivity : BaseActivity<ActivitySettingsBinding>(ActivitySettingsBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activitySettingsExitButton.setOnClickListener {
            onBackPressed()
        }

        binding.activitySettingsProfileImageChangeLayout.setOnClickListener {
            startActivity(Intent(this, ChangeImageActivity::class.java))
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

        binding.activitySettingsProfileNameChangeLayout.setOnClickListener {
            startActivity(Intent(this, ChangeNameActivity::class.java))
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

        binding.activitySettingsHowToUseLayout.setOnClickListener {
            startActivity(Intent(this, ManualActivity::class.java))
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }
}