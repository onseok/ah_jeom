package com.ssac.ah_jeom.src.login

import android.content.Intent
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
import com.ssac.ah_jeom.src.userInfo.interests.InterestsActivity

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

    private val textDisappearDownside: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.text_disappear_downside
        )
    }

    private val loginButtonAppear: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.login_button_appear
        )
    }

    private val backgroundDisappear: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.background_disappear
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
        }, 1800)

        binding.activityLoginMainStartText.setOnClickListener{
            with(binding.activityLoginMainStartText){
                startAnimation(textDisappearDownside)
                visibility = View.GONE
                hideBackgroundGIF()
                showSocialLoginButton()
                // 1초 뒤에 시작하기 텍스트가 사라지는 애니메이션을 삭제하여 재터치 방지
                Handler(Looper.getMainLooper()).postDelayed({
                    clearAnimation()
                }, 1000)
            }
        }

        // 임시
        binding.activityLoginKakaoLayout.setOnClickListener {
            startActivity(Intent(this,InterestsActivity::class.java))
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

    }

    private fun showSocialLoginButton() {
        with(binding.activityLoginGoogleLayout) {
            visibility = View.VISIBLE
            startAnimation(loginButtonAppear)
        }
        with(binding.activityLoginKakaoLayout) {
            visibility = View.VISIBLE
            startAnimation(loginButtonAppear)
        }
        with(binding.activityLoginNaverLayout) {
            visibility = View.VISIBLE
            startAnimation(loginButtonAppear)
        }
        with(binding.activityLoginFacebookLayout) {
            visibility = View.VISIBLE
            startAnimation(loginButtonAppear)
        }
        with(binding.activityLoginSignUpLayout) {
            visibility = View.VISIBLE
            startAnimation(loginButtonAppear)
        }
    }

    private fun hideBackgroundGIF() {
        with(binding.activityLoginBackgroundGif) {
            startAnimation(backgroundDisappear)
            visibility = View.INVISIBLE
        }
        binding.activityLoginMainStartText.visibility = View.GONE
    }
}