package com.ssac.ah_jeom.src.profile.settings.changeImage

import com.ssac.ah_jeom.src.profile.settings.changeImage.models.GetImageResponse
import com.ssac.ah_jeom.src.profile.settings.changeImage.models.PatchImageResponse


interface ChangeImageActivityView {

    fun onGetImageSuccess(response: GetImageResponse)

    fun onGetImageFailure(message: String)

    fun onPatchImageSuccess(response: PatchImageResponse)

    fun onPatchImageFailure(message: String)

}