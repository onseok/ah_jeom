package com.ssac.ah_jeom.src.main.peek.newStorage

import com.ssac.ah_jeom.src.main.peek.newStorage.models.GetNewStorageResponse

interface NewStorageActivityView {

    fun onGetNewStorageSuccess(response: GetNewStorageResponse)

    fun onGetNewStorageFailure(message: String)

}