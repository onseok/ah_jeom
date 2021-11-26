package com.ssac.ah_jeom.src.main.peek.soaringStorage

import android.os.Bundle
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivitySoaringStorageBinding

class SoaringStorageActivity : BaseActivity<ActivitySoaringStorageBinding>(ActivitySoaringStorageBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.activitySoaringStorageBackButton.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }
}