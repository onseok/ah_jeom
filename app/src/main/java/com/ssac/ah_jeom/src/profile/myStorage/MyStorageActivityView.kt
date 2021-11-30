package com.ssac.ah_jeom.src.profile.myStorage

import com.ssac.ah_jeom.src.profile.myStorage.models.GetMyStorageResponse

interface MyStorageActivityView {

    fun onGetMyStorageSuccess(response: GetMyStorageResponse)

    fun onGetMyStorageFailure(message: String)

}