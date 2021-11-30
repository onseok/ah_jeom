package com.ssac.ah_jeom.src.detail.artDetail.models

import com.google.gson.annotations.SerializedName

data class ResultArtDetailReview(
    @SerializedName("reviewId") val reviewId: Int,
    @SerializedName("caption") val caption: String,
    @SerializedName("userId") val userId: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("profile") val profile: String
)