package com.ssac.ah_jeom.src.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private val textAppear: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.text_appear
        )
    }

    private val textAppearUpside: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.text_appear_upside
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Glide.with(this).load(R.drawable.login_temporary_gif)
            .into(binding.activityLoginBackgroundGif)

        binding.activityLoginMainText.startAnimation(textAppear)

        Handler(Looper.getMainLooper()).postDelayed({
            with(binding.activityLoginMainStartText) {
                runOnUiThread {
                    visibility = View.VISIBLE
                    startAnimation(textAppearUpside)
                }
            }
        }, 2200)
    }
}