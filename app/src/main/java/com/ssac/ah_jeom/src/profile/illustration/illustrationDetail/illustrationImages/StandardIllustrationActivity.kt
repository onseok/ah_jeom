package com.ssac.ah_jeom.src.profile.illustration.illustrationDetail.illustrationImages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivitySilverIllustrationBinding
import com.ssac.ah_jeom.databinding.ActivityStandardIllustrationBinding

class StandardIllustrationActivity : BaseActivity<ActivityStandardIllustrationBinding>(
    ActivityStandardIllustrationBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityStandardIllustrationBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }
}