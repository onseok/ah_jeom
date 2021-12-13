package com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models

import com.google.gson.annotations.SerializedName

data class PostReportReviewResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)