package com.ssac.ah_jeom.src.profile.myStorage.models

import com.google.gson.annotations.SerializedName

data class GetMyStorageResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ResultGetMyStorage
)