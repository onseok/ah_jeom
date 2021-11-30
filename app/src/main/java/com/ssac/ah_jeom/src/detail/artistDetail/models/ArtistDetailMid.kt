package com.ssac.ah_jeom.src.detail.artistDetail.models

import com.google.gson.annotations.SerializedName

data class ArtistDetailMid(
    @SerializedName("artId") val artId: Int,
    @SerializedName("img") val img: String
)