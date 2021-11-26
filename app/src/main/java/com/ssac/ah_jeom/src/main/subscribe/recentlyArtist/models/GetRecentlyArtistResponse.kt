package com.ssac.ah_jeom.src.main.subscribe.recentlyArtist.models

import com.google.gson.annotations.SerializedName

data class GetRecentlyArtistResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ResultGetRecentlyArtist
)