package com.ssac.ah_jeom.src.profile.myArt.models

import com.google.gson.annotations.SerializedName

data class ResultGetMyArtDetail(
    @SerializedName("img") val img: String,
    @SerializedName("artId") val artId: Int,
    @SerializedName("cs") val cs: String
)