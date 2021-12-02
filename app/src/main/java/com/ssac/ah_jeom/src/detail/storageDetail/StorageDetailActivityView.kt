package com.ssac.ah_jeom.src.detail.storageDetail

import com.ssac.ah_jeom.src.detail.storageDetail.models.*

interface StorageDetailActivityView {

    // 보관함 상세 조회
    fun onGetStorageDetailSuccess(response: GetStorageDetailResponse)

    fun onGetStorageDetailFailure(message: String)

    // 좋아요 
    fun onPostStorageLikeSuccess(response: PostStorageLikeResponse)

    fun onPostStorageLikeFailure(message: String)

    // 좋아요 취소
    fun onPatchStorageLikeSuccess(response: PatchStorageLikeResponse)

    fun onPatchStorageLikeFailure(message: String)
    
    // 보관함 저장
    fun onPostStorageSaveSuccess(response: PostStorageSaveResponse)

    fun onPostStorageSaveFailure(message: String)
    
    // 보관함 저장 취소
    fun onPatchStorageSaveSuccess(response: PatchStorageSaveResponse)

    fun onPatchStorageSaveFailure(message: String)

}