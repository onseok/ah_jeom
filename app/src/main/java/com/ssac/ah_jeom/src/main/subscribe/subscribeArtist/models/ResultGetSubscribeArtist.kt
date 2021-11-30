package com.ssac.ah_jeom.src.main.subscribe.subscribeArtist.models

import com.google.gson.annotations.SerializedName

data class ResultGetSubscribeArtist(
    @SerializedName("sub") val sub: ArrayList<ResultGetSubscribeArtistSub>?,
    @SerializedName("nextCs") val nextCs: String,
    @SerializedName("hasMore") val hasMore: Boolean
)