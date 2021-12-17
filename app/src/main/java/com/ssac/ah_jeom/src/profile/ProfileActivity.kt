package com.ssac.ah_jeom.src.profile

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityProfileBinding
import com.ssac.ah_jeom.src.main.locker.myImage.MyImageActivity
import com.ssac.ah_jeom.src.main.subscribe.subscribeArtist.SubscribeArtistActivity
import com.ssac.ah_jeom.src.profile.illustration.IllustrationActivity
import com.ssac.ah_jeom.src.profile.models.GetProfileResponse
import com.ssac.ah_jeom.src.profile.myArt.MyArtActivity
import com.ssac.ah_jeom.src.profile.myPeek.MyPeekActivity
import com.ssac.ah_jeom.src.profile.myStorage.MyStorageActivity
import com.ssac.ah_jeom.src.profile.settings.SettingsActivity

class ProfileActivity : BaseActivity<ActivityProfileBinding>(ActivityProfileBinding::inflate),
    ProfileActivityView {

    var nickname = ""
    var gName = ""
    var gradeImage = 0
    var profileImage = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 내 보관함
        binding.activityProfileMyStorageLayout.setOnClickListener {

            startActivity(Intent(this, MyStorageActivity::class.java))
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

        // 내 옅보기
        binding.activityProfileMySubscribeLayout.setOnClickListener {
            startActivity(Intent(this, MyPeekActivity::class.java))
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

        // 내 작품 리스트
        binding.activityProfileMyArtLayout.setOnClickListener {
            startActivity(Intent(this, MyArtActivity::class.java))
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

        binding.activityProfileBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.activityProfileSettingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))

            with(intent) {
                putExtra("nickname", nickname)
                putExtra("gName", gName)
                putExtra("gradeImage", gradeImage)
                putExtra("profileImage", profileImage)
            }

            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

        // 구독 작가
        binding.activityProfileImpressionLayout.setOnClickListener {
            startActivity(Intent(this, SubscribeArtistActivity::class.java))
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }
        
        // 이미지
        binding.activityProfileStorageLayout.setOnClickListener {
            startActivity(Intent(this, MyImageActivity::class.java))
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

        // 일러스트레이션
        binding.activityProfileStorageStageImage.setOnClickListener {
            startActivity(Intent(this, IllustrationActivity::class.java))
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

                nickname = response.result[0].nickname
                gName = response.result[0].gName
                gradeImage = response.result[0].grade
                profileImage = response.result[0].profile
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

    override fun onResume() {
        super.onResume()
        ProfileService(this).tryGetProfile()
    }

}