package com.ssac.ah_jeom.src.main.subscribe.models

import com.google.gson.annotations.SerializedName

data class ResultGetSubscribe(
    @SerializedName("img") val img: String,
    @SerializedName("artId") val artId: Int
)