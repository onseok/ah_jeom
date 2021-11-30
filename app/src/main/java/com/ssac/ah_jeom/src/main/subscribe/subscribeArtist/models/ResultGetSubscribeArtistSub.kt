package com.ssac.ah_jeom.src.main.subscribe.subscribeArtist.models

import com.google.gson.annotations.SerializedName

data class ResultGetSubscribeArtistSub(
    @SerializedName("artistId") val artistId: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("profile") val profile: String,
    @SerializedName("gName") val gName: String,
    @SerializedName("subCount") val subCount: Int,
    @SerializedName("cs") val cs: String
)