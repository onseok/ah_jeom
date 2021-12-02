package com.ssac.ah_jeom.src.detail.storageDetail.models

import com.google.gson.annotations.SerializedName

data class PatchStorageLikeResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)