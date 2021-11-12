package com.ssac.ah_jeom.src.main.home.models

import com.google.gson.annotations.SerializedName

data class ResultField (
    @SerializedName("img") val img: String,
    @SerializedName("artId") val artId: Int
)