package com.ssac.ah_jeom.src.main.subscribe.bestArtist.models

import com.google.gson.annotations.SerializedName

data class GetBestArtistResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ResultGetBestArtist
)