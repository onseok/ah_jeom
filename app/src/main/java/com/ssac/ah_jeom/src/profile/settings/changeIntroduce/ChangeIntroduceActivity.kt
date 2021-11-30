package com.ssac.ah_jeom.src.profile.settings.changeIntroduce

import android.os.Bundle
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityChangeIntroduceBinding
import com.ssac.ah_jeom.src.profile.settings.changeIntroduce.models.GetIntroduceResponse
import com.ssac.ah_jeom.src.profile.settings.changeIntroduce.models.PatchIntroduceRequest
import com.ssac.ah_jeom.src.profile.settings.changeIntroduce.models.PatchIntroduceResponse

class ChangeIntroduceActivity : BaseActivity<ActivityChangeIntroduceBinding>(ActivityChangeIntroduceBinding::inflate), ChangeIntroduceActivityView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityChangeIntroduceBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.activityChangeIntroduceBottomSaveLayout.setOnClickListener {
            val summary = binding.activityChangeIntroduceEditText.text.toString()
            val patchIntroduceRequest = PatchIntroduceRequest(summary = summary)
            ChangeIntroduceService(this).tryPatchIntroduce(patchIntroduceRequest)
        }

        ChangeIntroduceService(this).tryGetIntroduce()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    override fun onGetIntroduceSuccess(response: GetIntroduceResponse) {
        if (response.isSuccess) {
            binding.activityChangeIntroduceEditText.setText(response.result?.get(0)?.summary)
        }
    }

    override fun onGetIntroduceFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onPatchIntroduceSuccess(response: PatchIntroduceResponse) {
        if(response.code == 2032) {
            showCustomToast("45자리 이하로 입력해주세요.")
        }
        else if (response.isSuccess) {
            showCustomToast("자기소개가 변경되었습니다.")
            onBackPressed()
        }
    }

    override fun onPatchIntroduceFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}