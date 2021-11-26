package com.ssac.ah_jeom.src.main.subscribe.bestArtist.models

import com.google.gson.annotations.SerializedName

data class ResultGetBestArtistDetail(
    @SerializedName("userId") val userId: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("profile") val profile: String,
    @SerializedName("subCount") val subCount: Int,
    @SerializedName("grade") val grade: Int,
    @SerializedName("gName") val gName: String,
    @SerializedName("cs") val cs: String
)