package com.ssac.ah_jeom.src.detail.reviewDetail.models

import com.google.gson.annotations.SerializedName

data class ReviewRequest(
    @SerializedName("artistId") val artistId: Int,
    @SerializedName("caption") val caption: String
)