package com.ssac.ah_jeom.src.main.locker.myImage

import com.ssac.ah_jeom.src.main.locker.myImage.models.GetMyImageResponse

interface MyImageActivityView {

    fun onGetMyImageSuccess(response: GetMyImageResponse)

    fun onGetMyImageFailure(message: String)

}