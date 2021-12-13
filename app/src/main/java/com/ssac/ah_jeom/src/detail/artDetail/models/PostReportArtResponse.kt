package com.ssac.ah_jeom.src.detail.artDetail.models

import com.google.gson.annotations.SerializedName

data class PostReportArtResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)