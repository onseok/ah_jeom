package com.ssac.ah_jeom.src.main.peek.bestStorage

import com.ssac.ah_jeom.src.main.peek.bestStorage.models.GetBestStorageResponse

interface BestStorageActivityView {

    fun onGetBestStorageSuccess(response: GetBestStorageResponse)

    fun onGetBestStorageFailure(message: String)

}