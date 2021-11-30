package com.ssac.ah_jeom.src.profile.myArt.models

import com.google.gson.annotations.SerializedName

data class ResultGetMyArt(
    @SerializedName("artwork") val artwork: ArrayList<ResultGetMyArtDetail>,
    @SerializedName("nextCs") val nextCs: String,
    @SerializedName("hasMore") val hasMore: Boolean
)