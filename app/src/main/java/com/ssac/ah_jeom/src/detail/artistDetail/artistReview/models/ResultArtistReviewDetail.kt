package com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models

import com.google.gson.annotations.SerializedName

data class ResultArtistReviewDetail(
    @SerializedName("reviewId") val reviewId: Int,
    @SerializedName("caption") val caption: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("profile") val profile: String,
    @SerializedName("userId") val userId: Int,
    @SerializedName("cs") val cs: String
)