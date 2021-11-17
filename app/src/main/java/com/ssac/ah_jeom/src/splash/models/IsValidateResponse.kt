package com.ssac.ah_jeom.src.splash.models

import com.google.gson.annotations.SerializedName

data class IsValidateResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)