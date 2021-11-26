package com.ssac.ah_jeom.src.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityProfileBinding
import com.ssac.ah_jeom.src.main.home.HomeService
import com.ssac.ah_jeom.src.profile.models.GetProfileResponse
import com.ssac.ah_jeom.src.profile.myStorage.MyStorageActivity
import com.ssac.ah_jeom.src.profile.settings.SettingsActivity

class ProfileActivity : BaseActivity<ActivityProfileBinding>(ActivityProfileBinding::inflate),
    ProfileActivityView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityProfileMyStorageLayout.setOnClickListener {
            startActivity(Intent(this, MyStorageActivity::class.java))
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

        binding.activityProfileBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.activityProfileSettingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

        // 프로필 화면 API 연동
        ProfileService(this).tryGetProfile()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    override fun onGetProfileSuccess(response: GetProfileResponse) {
        if (response.isSuccess) {
            with(binding) {
                activityProfileName.text = response.result[0].nickname
                activityProfileStageText.text = response.result[0].gName
                activityProfileStorageNumber.text = response.result[0].icount.toString()
                activityProfileImpressionNumber.text = response.result[0].scount.toString()
            }
            Glide.with(this).load(response.result[0].profile).circleCrop()
                .into(binding.activityProfileImage)
            detectIcon(response)
        }
    }

    private fun detectIcon(response: GetProfileResponse) {
        when (response.result[0].grade) {
            1 -> {
                Glide.with(this).load(R.drawable.ic_platinum_icon)
                    .into(binding.activityProfileGradeImage)
            }
            2 -> {
                Glide.with(this).load(R.drawable.ic_gold_icon)
                    .into(binding.activityProfileGradeImage)
            }
            3 -> {
                Glide.with(this).load(R.drawable.ic_silver_icon)
                    .into(binding.activityProfileGradeImage)
            }
            4 -> {
                Glide.with(this).load(R.drawable.ic_standard_icon)
                    .into(binding.activityProfileGradeImage)
            }
            5 -> {
                Glide.with(this).load(R.drawable.ic_copper_icon)
                    .into(binding.activityProfileGradeImage)
            }
            6 -> {
                Glide.with(this).load(R.drawable.ic_poo_icon)
                    .into(binding.activityProfileGradeImage)
            }
            7 -> {
                Glide.with(this).load(R.drawable.ic_stone_icon)
                    .into(binding.activityProfileGradeImage)
            }
        }
    }

    override fun onGetProfileFailure(message: String) {
        showCustomToast("오류 : $message")
    }

}