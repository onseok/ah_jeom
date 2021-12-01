package com.ssac.ah_jeom.src.detail.reviewDetail.models

import com.google.gson.annotations.SerializedName

data class PostReviewResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)