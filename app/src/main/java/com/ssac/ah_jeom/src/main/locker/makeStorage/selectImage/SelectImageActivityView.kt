package com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage

import com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage.models.GetSelectImageResponse

interface SelectImageActivityView {

    fun onGetSelectImageSuccess(response: GetSelectImageResponse)

    fun onGetSelectImageFailure(message: String)

}