package com.ssac.ah_jeom.src.profile.settings.changeName.models

import com.google.gson.annotations.SerializedName

data class PatchNameResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)