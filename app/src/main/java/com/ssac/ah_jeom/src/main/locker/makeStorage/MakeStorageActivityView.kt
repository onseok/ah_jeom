package com.ssac.ah_jeom.src.main.locker.makeStorage

import com.ssac.ah_jeom.src.main.locker.makeStorage.models.PostStorageResponse

interface MakeStorageActivityView {

    fun onPostMakeStorageSuccess(response: PostStorageResponse)

    fun onPostMakeStorageFailure(message: String)

}