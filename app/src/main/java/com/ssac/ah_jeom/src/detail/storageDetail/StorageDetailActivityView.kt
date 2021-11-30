package com.ssac.ah_jeom.src.detail.storageDetail

import com.ssac.ah_jeom.src.detail.storageDetail.models.GetStorageDetailResponse

interface StorageDetailActivityView {

    fun onGetStorageDetailSuccess(response: GetStorageDetailResponse)

    fun onGetStorageDetailFailure(message: String)

}