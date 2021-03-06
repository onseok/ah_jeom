package com.ssac.ah_jeom.src.profile.myStorage.models

import com.google.gson.annotations.SerializedName

data class ResultGetMyStorageDetail(
    @SerializedName("storageId") val storageId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("caption") val caption: String,
    @SerializedName("img") val img: String,
    @SerializedName("cs") val cs: String
)