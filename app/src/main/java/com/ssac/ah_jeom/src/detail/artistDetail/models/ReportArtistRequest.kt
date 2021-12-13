package com.ssac.ah_jeom.src.detail.artistDetail.models

import com.google.gson.annotations.SerializedName

data class ReportArtistRequest(
    @SerializedName("number") val number: Int,
    @SerializedName("caption") val caption: String?
)