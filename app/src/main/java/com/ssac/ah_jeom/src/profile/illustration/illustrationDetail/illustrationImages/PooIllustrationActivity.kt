package com.ssac.ah_jeom.src.profile.illustration.illustrationDetail.illustrationImages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityCopperIllustrationBinding
import com.ssac.ah_jeom.databinding.ActivityPooIllustrationBinding

class PooIllustrationActivity : BaseActivity<ActivityPooIllustrationBinding>(
    ActivityPooIllustrationBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityPooIllustrationBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }
}