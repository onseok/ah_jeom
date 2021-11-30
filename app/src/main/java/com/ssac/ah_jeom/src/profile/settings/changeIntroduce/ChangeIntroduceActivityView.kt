package com.ssac.ah_jeom.src.profile.settings.changeIntroduce

import com.ssac.ah_jeom.src.profile.settings.changeIntroduce.models.GetIntroduceResponse
import com.ssac.ah_jeom.src.profile.settings.changeIntroduce.models.PatchIntroduceResponse

interface ChangeIntroduceActivityView {

    fun onGetIntroduceSuccess(response: GetIntroduceResponse)

    fun onGetIntroduceFailure(message: String)

    fun onPatchIntroduceSuccess(response: PatchIntroduceResponse)

    fun onPatchIntroduceFailure(message: String)

}