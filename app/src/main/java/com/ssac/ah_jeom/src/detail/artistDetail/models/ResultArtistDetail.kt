package com.ssac.ah_jeom.src.detail.artistDetail.models

import com.google.gson.annotations.SerializedName

data class ResultArtistDetail(
    @SerializedName("top") val top: ArrayList<ArtistDetailTop>,
    @SerializedName("mid") val mid: ArrayList<ArtistDetailMid>,
    @SerializedName("bot") val bot: ArrayList<ArtistDetailBot>?
)