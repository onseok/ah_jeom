package com.ssac.ah_jeom.src.profile.settings.changeImage.models

import com.google.gson.annotations.SerializedName

data class PatchImageResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)