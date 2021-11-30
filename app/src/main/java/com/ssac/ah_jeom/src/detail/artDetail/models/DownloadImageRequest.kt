package com.ssac.ah_jeom.src.detail.artDetail.models

import com.google.gson.annotations.SerializedName

data class DownloadImageRequest(
    @SerializedName("artId") val artId: Int
)