package com.ssac.ah_jeom.src.userInfo.confirm

import android.content.Intent
import android.os.Bundle
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityConfirmBinding
import com.ssac.ah_jeom.src.main.MainActivity

class ConfirmActivity : BaseActivity<ActivityConfirmBinding>(ActivityConfirmBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityConfirmNoButton.setOnClickListener {
            onBackPressed()
        }

        binding.activityConfirmYesButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

}