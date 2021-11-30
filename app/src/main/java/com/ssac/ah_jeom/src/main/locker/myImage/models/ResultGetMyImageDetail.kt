package com.ssac.ah_jeom.src.main.locker.myImage.models

import com.google.gson.annotations.SerializedName

data class ResultGetMyImageDetail(
    @SerializedName("img") val img: String,
    @SerializedName("artId") val artId: Int,
    @SerializedName("cs") val cs: String
)