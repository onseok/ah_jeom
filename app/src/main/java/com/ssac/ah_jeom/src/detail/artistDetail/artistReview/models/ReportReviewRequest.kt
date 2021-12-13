package com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models

import com.google.gson.annotations.SerializedName

data class ReportReviewRequest(
    @SerializedName("number") val number: Int,
    @SerializedName("caption") val caption: String?
)