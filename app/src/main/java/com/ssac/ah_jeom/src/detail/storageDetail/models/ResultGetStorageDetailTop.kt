package com.ssac.ah_jeom.src.detail.storageDetail.models

import com.google.gson.annotations.SerializedName

data class ResultGetStorageDetailTop(
    @SerializedName("storageId") val storageId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("caption") val caption: String,
    @SerializedName("img") val img: String,
    @SerializedName("heart") val heart: Int,
    @SerializedName("save") val save: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("cs") val cs: String
)