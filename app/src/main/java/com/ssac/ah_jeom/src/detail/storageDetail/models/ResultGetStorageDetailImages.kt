package com.ssac.ah_jeom.src.detail.storageDetail.models

import com.google.gson.annotations.SerializedName

data class ResultGetStorageDetailImages(
    @SerializedName("artId") val artId: Int,
    @SerializedName("img") val img: String,
    @SerializedName("cs") val cs: String
)