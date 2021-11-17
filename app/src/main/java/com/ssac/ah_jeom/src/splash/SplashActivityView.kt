package com.ssac.ah_jeom.src.splash

import com.ssac.ah_jeom.src.splash.models.IsValidateResponse

interface SplashActivityView {

    fun onGetIsValidateSuccess(response: IsValidateResponse)

    fun onGetIsValidateFailure(message: String)

}