package com.ssac.ah_jeom.src.detail.artistDetail.artistArt.models

import com.google.gson.annotations.SerializedName

data class ResultGetArtistArtDetail(
    @SerializedName("artId") val artId: Int,
    @SerializedName("img") val img: String,
    @SerializedName("cs") val cs: String
)