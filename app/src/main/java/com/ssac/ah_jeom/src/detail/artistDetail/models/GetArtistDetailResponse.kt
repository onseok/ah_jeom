package com.ssac.ah_jeom.src.detail.artistDetail.models

import com.google.gson.annotations.SerializedName

data class GetArtistDetailResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ResultArtistDetail
)