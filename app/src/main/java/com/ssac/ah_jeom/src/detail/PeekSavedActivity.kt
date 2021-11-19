package com.ssac.ah_jeom.src.detail

import android.os.Bundle
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityPeekSavedBinding
import com.ssac.ah_jeom.src.detail.recycler.PeekSavedRecyclerData

class PeekSavedActivity : BaseActivity<ActivityPeekSavedBinding>(ActivityPeekSavedBinding::inflate) {

    val data: MutableList<PeekSavedRecyclerData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityPeekDetailSavedBackButton.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

}