package com.ssac.ah_jeom.src.profile.myPeek.models

import com.google.gson.annotations.SerializedName

data class ResultGetMyPeek(
    @SerializedName("save") val save: ArrayList<ResultGetMyPeekDetail>,
    @SerializedName("nextCs") val nextCs: String,
    @SerializedName("hasMore") val hasMore: Boolean
)