package com.ssac.ah_jeom.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.kakao.sdk.common.util.Utility
import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivitySplashBinding
import com.ssac.ah_jeom.src.login.LoginActivity
import com.ssac.ah_jeom.src.main.MainActivity
import com.ssac.ah_jeom.src.splash.models.IsValidateResponse

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate), SplashActivityView {

    private var isValidate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)

        Handler(Looper.getMainLooper()).postDelayed({
            initObserver()
        }, 2200)

        SplashService(this).tryGetIsValidate()
    }

    private fun goMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun goLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    // TODO JWT토큰을 이용한 (자동)로그인 구현
    // (1) jwt 토큰이 기기 내에 존재하고, 토큰이 유효한 값이고, 회원가입된 멤버라면, 바로 메인액티비티로 이동 (회원일 때 자동로그인)
    // (2) jwt 토큰이 기기 내에 존재하지만, 토큰이 유효하지 못한 값이고, 회원가입되지 않은 상태라면 회원가입 재진행 (회원탈퇴하고 재시작 했을 때)
    // (3) jwt 토큰이 기기내에 존재하지 않고(null), 당연히 토큰이 유효하지 못한 값이고, 회원가입도 되지 못한 상태라면 (비회원인 사용자가 제일 처음 앱을 설치하였을 때)
    // (4) jwt 토큰이 기기내에 존재하지 않고(null), 당연히 토큰이 유효하지 못한 값이나, 회원가입은 예전에 했던 상태라면 (회원인 사용자가 휴대폰을 바꾸었을 경우)

    // TODO 아마도 isMember는 키워드 액티비티에서 처리할 수 있을 것 같다..
    
    private fun initObserver() {
        val jwt = ApplicationClass.sSharedPreferences.getString("X-ACCESS-TOKEN", null)
        Log.d("X-ACCESS-TOKEN", jwt.toString())

        // (1) 케이스
        if (jwt != null && isValidate) {
            // 메인티비티 이동
            goMainActivity()
            Log.d("caseTest", "1case")
        }
        // (2) 케이스
        else if (jwt != null && !isValidate) {
            // 로그인액티비티 이동
            goLoginActivity()
            Log.d("caseTest", "2case")
        }
        else {
            // (3), (4) 케이스
            if (!isValidate) {
                goLoginActivity()
                Log.d("caseTest", "34case")
            }
        }
    }

    override fun onGetIsValidateSuccess(response: IsValidateResponse) {
        if(response.isSuccess || response.code == 1001) {
            isValidate = true
        }
        else if (!response.isSuccess || response.code == 3000) {
            isValidate = false
        }
    }

    override fun onGetIsValidateFailure(message: String) {
        showCustomToast(message)
        isValidate = false
    }
}