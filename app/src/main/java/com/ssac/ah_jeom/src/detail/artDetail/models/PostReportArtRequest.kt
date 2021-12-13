package com.ssac.ah_jeom.src.detail.artDetail.models

import com.google.gson.annotations.SerializedName

data class PostReportArtRequest(
    @SerializedName("number") val number: Int,
    @SerializedName("caption") val caption: String?
)