package com.ssac.ah_jeom.src.main.peek.models

import com.google.gson.annotations.SerializedName

data class ResultGetPeek(
    @SerializedName("title") val title: String,
    @SerializedName("img") val img: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("profile") val profile: String,
    @SerializedName("save") val save: Int,
    @SerializedName("rnk") val rnk: Int,
    @SerializedName("storageId") val storageId: Int
)