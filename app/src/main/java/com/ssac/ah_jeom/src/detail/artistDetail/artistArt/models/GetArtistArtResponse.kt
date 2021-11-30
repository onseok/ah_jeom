package com.ssac.ah_jeom.src.detail.artistDetail.artistArt.models

import com.google.gson.annotations.SerializedName

data class GetArtistArtResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ResultGetArtistArt
)