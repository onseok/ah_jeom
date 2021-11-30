package com.ssac.ah_jeom.src.profile.myPeek.models

import com.google.gson.annotations.SerializedName

data class ResultGetMyPeekDetail(
    @SerializedName("storageId") val storageId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("save") val save: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("img") val img: String,
    @SerializedName("cs") val cs: String
)