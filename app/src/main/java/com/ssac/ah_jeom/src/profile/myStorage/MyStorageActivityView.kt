package com.ssac.ah_jeom.src.profile.myStorage

import com.ssac.ah_jeom.src.profile.myStorage.models.GetMyStorageResponse
import com.ssac.ah_jeom.src.profile.myStorage.models.PatchMyStorageResponse

interface MyStorageActivityView {

    fun onGetMyStorageSuccess(response: GetMyStorageResponse)

    fun onGetMyStorageFailure(message: String)


}