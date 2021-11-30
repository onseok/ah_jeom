package com.ssac.ah_jeom.src.detail.artistDetail.models

import com.google.gson.annotations.SerializedName

data class SubscribeRequest(
    @SerializedName("artistId") val artistId: Int
)