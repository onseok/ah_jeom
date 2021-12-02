package com.ssac.ah_jeom.src.profile.settings.models

import com.google.gson.annotations.SerializedName

data class ResultGetSettings(
    @SerializedName("userId") val userId: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("profile") val profile: String,
    @SerializedName("grade") val grade: Int,
    @SerializedName("gName") val gName: String
)