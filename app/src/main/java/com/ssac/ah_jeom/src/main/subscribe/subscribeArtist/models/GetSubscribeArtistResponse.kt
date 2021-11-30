package com.ssac.ah_jeom.src.main.subscribe.subscribeArtist.models

import com.google.gson.annotations.SerializedName


data class GetSubscribeArtistResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ResultGetSubscribeArtist
)