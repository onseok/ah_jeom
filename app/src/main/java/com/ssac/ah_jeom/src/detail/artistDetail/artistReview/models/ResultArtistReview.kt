package com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models

import com.google.gson.annotations.SerializedName

data class ResultArtistReview(
    @SerializedName("review") val review: ArrayList<ResultArtistReviewDetail>,
    @SerializedName("nextCs") val nextCs: String,
    @SerializedName("hasMore") val hasMore: Boolean
)