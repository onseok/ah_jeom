package com.ssac.ah_jeom.src.profile.myArt.models

import com.google.gson.annotations.SerializedName

data class GetMyArtResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ResultGetMyArt
)