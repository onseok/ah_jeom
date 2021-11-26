package com.ssac.ah_jeom.src.profile

import com.ssac.ah_jeom.src.profile.models.GetProfileResponse

interface ProfileActivityView {

    fun onGetProfileSuccess(response: GetProfileResponse)

    fun onGetProfileFailure(message: String)

}