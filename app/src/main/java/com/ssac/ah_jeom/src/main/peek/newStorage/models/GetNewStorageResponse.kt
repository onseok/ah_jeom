package com.ssac.ah_jeom.src.main.peek.newStorage.models

import com.google.gson.annotations.SerializedName

data class GetNewStorageResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ResultGetNewStorage
)