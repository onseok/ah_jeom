package com.ssac.ah_jeom.src.profile.settings.changeImage.models

import com.google.gson.annotations.SerializedName

data class GetImageResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ArrayList<ResultGetImage>
)