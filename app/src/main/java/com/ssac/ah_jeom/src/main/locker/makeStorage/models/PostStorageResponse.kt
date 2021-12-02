package com.ssac.ah_jeom.src.main.locker.makeStorage.models

import com.google.gson.annotations.SerializedName

data class PostStorageResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ResultPostStorage
)