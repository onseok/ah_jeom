package com.ssac.ah_jeom.src.detail.artDetail.models

import com.google.gson.annotations.SerializedName

data class ResultArtDetailArt(
    @SerializedName("artId") val artId: Int,
    @SerializedName("img") val img: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Int,
    @SerializedName("link") val link: String
)