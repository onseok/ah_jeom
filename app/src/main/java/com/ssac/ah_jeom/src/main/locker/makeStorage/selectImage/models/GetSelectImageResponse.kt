package com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage.models

import com.google.gson.annotations.SerializedName

data class GetSelectImageResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ResultGetSelectImage
)