package com.ssac.ah_jeom.src.main.home.models

import com.google.gson.annotations.SerializedName

data class ResultNewArtist (
    @SerializedName("userId") val userId: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("profile") val profile: String
)