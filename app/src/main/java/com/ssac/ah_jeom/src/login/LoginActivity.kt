package com.ssac.ah_jeom.src.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.bumptech.glide.Glide
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.util.Utility
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityLoginBinding
import com.ssac.ah_jeom.src.login.models.PostLoginRequest
import com.ssac.ah_jeom.src.login.models.PostLoginResponse
import com.ssac.ah_jeom.src.userInfo.interests.InterestsActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate), LoginActivityView {

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

    private var kakaoAccessToken: String? = null

    private val kakao = "kakao"

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

        binding.activityLoginMainStartText.setOnClickListener {
            with(binding.activityLoginMainStartText) {
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


        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT)
                            .show()
                    }
                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT)
                            .show()
                    }
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
            } else if (token != null) {
                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                Log.d("token", token.accessToken)
                kakaoAccessToken = token.accessToken

                val params =  PostLoginRequest(access_token = token.accessToken)
                LoginService(this).tryPostLogin(kakao, params)

            }
        }

        binding.activityLoginKakaoLayout.setOnClickListener {
            if (LoginClient.instance.isKakaoTalkLoginAvailable(this)) {
                LoginClient.instance.loginWithKakaoTalk(this, callback = callback)
            } else {
                LoginClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
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

    override fun onPostLoginSuccess(response: PostLoginResponse) {
       if (response.result != null) {
           val editor = ApplicationClass.sSharedPreferences.edit()
           editor.putString("X-ACCESS-TOKEN", response.result.jwt)
           editor.putInt("userId", response.result.userId)
           editor.commit()

           val intent = Intent(this, InterestsActivity::class.java)
           intent.putExtra("kakaoAccessToken", kakaoAccessToken)
//           intent.putExtra("userId", response.result.userId)
           startActivity(intent)
           overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
       }
    }

    override fun onPostLoginFailure(message: String) {
        showCustomToast("오류 : $message")
    }


}