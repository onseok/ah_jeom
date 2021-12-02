package com.ssac.ah_jeom.src.profile.myStorage

import com.ssac.ah_jeom.src.profile.myStorage.models.PatchMyStorageResponse

interface DeleteStorageActivityView {

    fun onPatchMyStorageSuccess(response: PatchMyStorageResponse)

    fun onPatchMyStorageFailure(message: String)

}


