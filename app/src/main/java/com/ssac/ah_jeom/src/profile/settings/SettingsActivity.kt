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
import com.ssac.ah_jeom.src.profile.settings.personalPrivacy.PersonalPrivacyActivity
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

        binding.activitySettingsPersonalPrivacyLayout.setOnClickListener {
            startActivity(Intent(this, PersonalPrivacyActivity::class.java))
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

        binding.activitySettingsNoticeLayout.setOnClickListener {
            showCustomToast("?????? ????????? ??????????????????.")
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

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)   //???????????? ??????
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // ??????????????? ?????? ?????? (?????? ????????? background??? ???????????? ???????????? ??????)
        dialog.setContentView(R.layout.logout_dialog)
        dialog.setCancelable(true)    // ????????????????????? ?????? ????????? ????????? ??? ????????? ??? ??????

        var logoutYesButton : Button? = dialog.findViewById(R.id.logoutYesButton) // ????????? dialog??? ??????
        var logoutNoButton : Button? = dialog.findViewById(R.id.logoutNoButton)


        logoutYesButton?.setOnClickListener {
            dialog.dismiss()
            // ?????? ???????????? ?????? ???????????? ??????????????? ???????????? ??????
            val editor = ApplicationClass.sSharedPreferences.edit()
            editor.putString("X-ACCESS-TOKEN", "trash")
            editor.apply()
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
            showCustomToast("???????????? ???????????????")
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
        showCustomToast("?????? : $message")
    }

    override fun onResume() {
        super.onResume()
        SettingsService(this).tryGetSettings()
    }
}