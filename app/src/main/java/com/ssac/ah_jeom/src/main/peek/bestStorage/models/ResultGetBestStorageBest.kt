package com.ssac.ah_jeom.src.main.peek.bestStorage.models

import com.google.gson.annotations.SerializedName

data class ResultGetBestStorageBest(
    @SerializedName("storageId") val storageId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("img") val img: String,
    @SerializedName("heart") val heart: Int,
    @SerializedName("save") val save: Int,
    @SerializedName("profile") val profile: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("cs") val cs: String
)