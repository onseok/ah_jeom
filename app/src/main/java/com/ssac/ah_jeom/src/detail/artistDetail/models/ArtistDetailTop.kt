package com.ssac.ah_jeom.src.detail.artistDetail.models

import com.google.gson.annotations.SerializedName

data class ArtistDetailTop(
    @SerializedName("userId") val userId: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("profile") val profile: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("grade") val grade: Int,
    @SerializedName("sub") val sub: Int   // 구독했으면 1, 구독안했으면 0
)