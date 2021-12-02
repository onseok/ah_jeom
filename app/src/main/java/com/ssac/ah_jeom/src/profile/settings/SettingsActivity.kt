package com.ssac.ah_jeom.src.profile.settings

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Button
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivitySettingsBinding
import com.ssac.ah_jeom.src.profile.models.GetProfileResponse
import com.ssac.ah_jeom.src.profile.settings.changeImage.ChangeImageActivity
import com.ssac.ah_jeom.src.profile.settings.changeIntroduce.ChangeIntroduceActivity
import com.ssac.ah_jeom.src.profile.settings.changeName.ChangeNameActivity
import com.ssac.ah_jeom.src.profile.settings.manual.ManualActivity
import com.ssac.ah_jeom.src.profile.settings.models.GetSettingsResponse
import com.ssac.ah_jeom.src.splash.SplashActivity

class SettingsActivity : BaseActivity<ActivitySettingsBinding>(ActivitySettingsBinding::inflate), SettingsActivityView {

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

        binding.activitySettingsProfileIntroduceChangeLayout.setOnClickListener {
            startActivity(Intent(this, ChangeIntroduceActivity::class.java))
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

        binding.activitySettingsHowToUseLayout.setOnClickListener {
            startActivity(Intent(this, ManualActivity::class.java))
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

        binding.activitySettingsNoticeLayout.setOnClickListener {
            showCustomToast("현재 서비스 준비중입니다.")
            return@setOnClickListener
        }

        binding.activitySettingsLogoutLayout.setOnClickListener {
            logoutDialog()
        }

        SettingsService(this).tryGetSettings()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    private fun detectIcon(number: Int) {
        when (number) {
            1 -> {
                Glide.with(this).load(R.drawable.ic_platinum_icon)
                    .into(binding.activitySettingsProfileStageImage)
            }
            2 -> {
                Glide.with(this).load(R.drawable.ic_gold_icon)
                    .into(binding.activitySettingsProfileStageImage)
            }
            3 -> {
                Glide.with(this).load(R.drawable.ic_silver_icon)
                    .into(binding.activitySettingsProfileStageImage)
            }
            4 -> {
                Glide.with(this).load(R.drawable.ic_standard_icon)
                    .into(binding.activitySettingsProfileStageImage)
            }
            5 -> {
                Glide.with(this).load(R.drawable.ic_copper_icon)
                    .into(binding.activitySettingsProfileStageImage)
            }
            6 -> {
                Glide.with(this).load(R.drawable.ic_poo_icon)
                    .into(binding.activitySettingsProfileStageImage)
            }
            7 -> {
                Glide.with(this).load(R.drawable.ic_stone_icon)
                    .into(binding.activitySettingsProfileStageImage)
            }
        }
    }

    private fun logoutDialog() {
        val dialog = Dialog(this)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 백그라운드 컬러 투명 (이걸 해줘야 background가 설정해준 모양으로 변함)
        dialog.setContentView(R.layout.logout_dialog)
        dialog.setCancelable(true)    // 다이얼로그외에 다른 화면을 눌렀을 때 나가는 것 허용

        var logoutYesButton : Button? = dialog.findViewById(R.id.logoutYesButton) // 버튼을 dialog에 연결
        var logoutNoButton : Button? = dialog.findViewById(R.id.logoutNoButton)


        logoutYesButton?.setOnClickListener {
            dialog.dismiss()
            // 토큰 폐기하고 다시 스플래쉬 액티비티로 시작하는 로직
            val editor = ApplicationClass.sSharedPreferences.edit()
            editor.putString("X-ACCESS-TOKEN", "trash")
            editor.apply()
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
            showCustomToast("로그아웃 되었습니다")
        }

        logoutNoButton?.setOnClickListener{
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onGetSettingsSuccess(response: GetSettingsResponse) {
       if(response.isSuccess) {
           Glide.with(this).load(response.result[0].profile).circleCrop().into(binding.activitySettingsProfileImage)
           binding.activitySettingsProfileName.text = response.result[0].nickname
           binding.activitySettingsStageText.text = response.result[0].gName
           detectIcon(response.result[0].grade)
       }
    }

    override fun onGetSettingsFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onResume() {
        super.onResume()
        SettingsService(this).tryGetSettings()
    }
}