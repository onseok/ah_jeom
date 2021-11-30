package com.ssac.ah_jeom.src.detail.artistDetail.models

import com.google.gson.annotations.SerializedName

data class ArtistDetailBot(
    @SerializedName("userId") val userId: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("profile") val profile: String,
    @SerializedName("caption") val caption: String,
    @SerializedName("reviewId") val reviewId: Int
)