package com.ssac.ah_jeom.src.profile.models

import com.google.gson.annotations.SerializedName

data class ResultGetProfile(
    @SerializedName("nickname") val nickname: String,
    @SerializedName("profile") val profile: String,
    @SerializedName("grade") val grade: Int,
    @SerializedName("gName") val gName: String,
    @SerializedName("icount") val icount: Int,
    @SerializedName("scount") val scount: Int
)