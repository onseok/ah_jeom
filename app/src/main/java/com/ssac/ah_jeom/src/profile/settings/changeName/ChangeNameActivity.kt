package com.ssac.ah_jeom.src.profile.settings.changeName

import android.os.Bundle
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityChangeNameBinding
import com.ssac.ah_jeom.src.profile.settings.changeIntroduce.ChangeIntroduceService
import com.ssac.ah_jeom.src.profile.settings.changeIntroduce.models.PatchIntroduceRequest
import com.ssac.ah_jeom.src.profile.settings.changeName.models.GetNameResponse
import com.ssac.ah_jeom.src.profile.settings.changeName.models.PatchNameRequest
import com.ssac.ah_jeom.src.profile.settings.changeName.models.PatchNameResponse

class ChangeNameActivity : BaseActivity<ActivityChangeNameBinding>(ActivityChangeNameBinding::inflate), ChangeNameActivityView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityChangeNameBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.activityChangeNameBottomSaveLayout.setOnClickListener {
            val nickname = binding.activityChangeNameEditText.text.toString()
            val patchNameRequest = PatchNameRequest(nickname = nickname)
            ChangeNameService(this).tryPatchName(patchNameRequest)
        }

        ChangeNameService(this).tryGetName()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    override fun onGetNameSuccess(response: GetNameResponse) {
        if (response.isSuccess) {
            binding.activityChangeNameEditText.setText(response.result?.get(0)?.nickname)
        }
    }

    override fun onGetNameFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onPatchNameSuccess(response: PatchNameResponse) {
        when {
            response.code == 2026 -> {
                showCustomToast("닉네임은 20자리 이하로 입력해주세요.")
            }
            response.code == 3002 -> {
                showCustomToast("중복된 닉네임입니다.")
            }
            response.isSuccess -> {
                showCustomToast("닉네임이 변경되었습니다.")
                onBackPressed()
            }
        }
    }

    override fun onPatchNameFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}