package com.ssac.ah_jeom.src.detail.storageDetail.models

import com.google.gson.annotations.SerializedName

data class PostStorageLikeResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)