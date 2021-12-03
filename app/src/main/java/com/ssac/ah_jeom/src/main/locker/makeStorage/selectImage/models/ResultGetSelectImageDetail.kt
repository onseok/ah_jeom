package com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage.models

import com.google.gson.annotations.SerializedName

data class ResultGetSelectImageDetail(
    @SerializedName("img") val img: String,
    @SerializedName("artId") val artId: Int,
    @SerializedName("cs") val cs: String
)